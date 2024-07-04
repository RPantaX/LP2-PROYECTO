package com.panda.demo.facturas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panda.demo.errors.CustomErrors;
import com.panda.demo.facturas.interfaceService.IFacturaService;
import com.panda.demo.facturas.interfaces.ICliente;
import com.panda.demo.facturas.interfaces.IFactura;
import com.panda.demo.facturas.interfaces.IItem;
import com.panda.demo.facturas.modelos.Cliente;
import com.panda.demo.facturas.modelos.Factura;
import com.panda.demo.facturas.modelos.Item;
import com.panda.demo.facturas.request.FacturaRequest;
import com.panda.demo.feignClient.SunatClient;
import com.panda.demo.feignClient.response.SunatResponse;
import com.panda.demo.guiasTransportista.interfaces.IGuiasTransportista;
import com.panda.demo.guiasTransportista.modelos.GuiaTransportista;
import com.panda.demo.utils.GlobalData;

@Service
public class FacturaService implements IFacturaService{
	private final BigDecimal IGV = BigDecimal.valueOf(0.18);
	@Autowired
	private IFactura data;
	
	@Autowired
	private IItem itemData;
	
	@Autowired
	private ICliente clientData;
	
	@Autowired
	private IGuiasTransportista guiaTrapData;
	private final SunatClient sunatClient=null;
    @Value("${token.api}")
    private String tokenApi;
    
	@Override
	public List<Factura> listar() {
		
		return (List<Factura>) data.findAll();
	}

	@Override
	public Optional<Factura> listarId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}
	
	@Override
	@Transactional
	public int save(FacturaRequest request) {
		// TODO Auto-generated method stub
		int res=0;
		 // Obtener información del remitente desde Sunat
        SunatResponse responseCliente = getExecution(request.getClienteRuc());
        int responseValid = validarCliente(responseCliente);
        if(responseValid !=0) {
        	return responseValid;
        }
        
        // Obtener la guía transportista
        Optional<GuiaTransportista> guia = guiaTrapData.findById(request.getNumeroGuia());
        
        int respuestaValidacionGT= validarGuiaTransportista(guia);
        if ( respuestaValidacionGT!= 0) {
        	return respuestaValidacionGT;
        };
     // Guardar los ítems de la factura
        List<Item> itemsSave = (List<Item>) itemData.saveAll(request.getItems());
     // Calcular subtotal y total
        BigDecimal subTotal = calcularSubtotal(itemsSave);
        BigDecimal total = subTotal.add(subTotal.multiply(IGV));
     // Construir la factura
        Factura factura = construirFactura(responseCliente, guia.get(), subTotal, total, itemsSave, request);
		data.save(factura);
		if(!factura.equals(null)) {
			res=1;
		}
		return res;
	}
	// Funciones auxiliares
	 private int validarCliente(SunatResponse responseCliente) {
	        if (responseCliente == null) {
	            return CustomErrors.valorNoEncontradoEnSunat;
	        }
	        return 0;
	    }

    private int validarGuiaTransportista(Optional<GuiaTransportista> guia) {
        if (guia.isEmpty()) {
            return CustomErrors.valorExistenteEnBaseDeDatos;
        }
        if(guia.get().getFactura() != null) {
        	return CustomErrors.guiaTransptAsociadaAFactura;
        }
        return 0;
    }

    private BigDecimal calcularSubtotal(List<Item> itemsSave) {
        BigDecimal subTotal = BigDecimal.ZERO;
        for (Item i : itemsSave) {
            BigDecimal cantidad = BigDecimal.valueOf(i.getCantidad());
            BigDecimal subtotalItem = cantidad.multiply(i.getPrecioUnitario());
            subTotal = subTotal.add(subtotalItem);
        }
        return subTotal;
    }
    private Factura construirFactura(SunatResponse responseRemitente, GuiaTransportista guia, BigDecimal subTotal, BigDecimal total, List<Item> itemsSave, FacturaRequest request) {
        Factura fact = new Factura();
        
        // Obtener información del remitente desde Sunat
        Optional<Cliente> cliente = clientData.findById(request.getClienteRuc());
        
        //SI EL RUC QUE INGRESA ES NUEVO CREA AUTOMÁTICAMENTE UNO NUEVO REGISTRO EN BASE A LA INFORMACIÓN OBTENIDA POR SUNAT
        Cliente clienteNuevo = new Cliente();
        if(cliente.isEmpty()) {
        	clienteNuevo.setRuc(request.getClienteRuc());
        	clienteNuevo.setDireccion(responseRemitente.getDireccion());
        	clienteNuevo.setRazon_social(responseRemitente.getRazonSocial());
        }
        fact.setSerieFactura(GlobalData.serieFactura);
        fact.setSubTotal(subTotal);
        fact.setIgv(IGV);
        fact.setMontoTotal(total);
        fact.setObservacion(request.getObservacion());
        fact.setItems((Set<Item>) itemsSave);
        fact.setCliente(clienteNuevo);
        return fact;
    }
    public SunatResponse getExecution(String numero){
        String authorization = "Bearer " + tokenApi;
        return sunatClient.getInfo(numero, authorization);
    }

}
