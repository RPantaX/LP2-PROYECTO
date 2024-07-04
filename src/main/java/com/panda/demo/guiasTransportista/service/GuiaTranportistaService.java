package com.panda.demo.guiasTransportista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panda.demo.errors.CustomErrors;
import com.panda.demo.feignClient.SunatClient;
import com.panda.demo.feignClient.response.SunatResponse;
import com.panda.demo.guiasTransportista.interfaceService.IGuiasTransportistaService;
import com.panda.demo.guiasTransportista.interfaces.IDestinatarios;
import com.panda.demo.guiasTransportista.interfaces.IGuiasTransportista;
import com.panda.demo.guiasTransportista.interfaces.IRemitentes;
import com.panda.demo.guiasTransportista.modelos.Destinatarios;
import com.panda.demo.guiasTransportista.modelos.GuiaTransportista;
import com.panda.demo.guiasTransportista.modelos.Remitentes;
import com.panda.demo.guiasTransportista.request.GuiaTransportistaRequest;
import com.panda.demo.utils.GlobalData;

@Service
public class GuiaTranportistaService implements IGuiasTransportistaService{

	@Autowired
	private IGuiasTransportista data;
	@Autowired
	private IRemitentes remitentesData;
	@Autowired
	private IDestinatarios destinatariosData;
	
	private final SunatClient sunatClient =null;;
    @Value("${token.api}")
    private String tokenApi;
	@Override
	public List<GuiaTransportista> listar() {
		// TODO Auto-generated method stub
		return (List<GuiaTransportista>) data.findAll();
	}

	@Override
	public Optional<GuiaTransportista> listarId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}

	@Override
	@Transactional
	public int save(GuiaTransportistaRequest request) {
		// TODO Auto-generated method stub
		int res=0;
		
		//Optional<Trabajador> trabajador= trabajadorRepository.getTrabajadorByNumIdentidad(request.getNumDocChofer());
		//VALIDA RUC REMITENTE Y DESTINATARIO
		SunatResponse responseRemitente = getExecution(request.getRemitenteRuc());
		if(responseRemitente ==null) {
			return CustomErrors.valorNoEncontradoEnSunat;
		}
		SunatResponse responseDestinatario= getExecution(request.getDestinatarioRuc());
		if(responseDestinatario == null) {
			return CustomErrors.valorNoEncontradoEnSunat;
		}
		//VALIDAMOS SI EXISTE EN LA BASE DE DATOS
		Optional<Remitentes> remitente = remitentesData.findById(request.getRemitenteRuc());
		Optional<Destinatarios> destinatario = destinatariosData.findById(request.getDestinatarioRuc());
		
		//SI EL RUC QUE INGRESA ES NUEVO CREA AUTOMÁTICAMENTE UNO NUEVO REGISTRO EN BASE A LA INFORMACIÓN OBTENIDA POR SUNAT
		if(remitente.isEmpty()) {
			Remitentes remitenteNuevo = new Remitentes();
			remitenteNuevo.setRazon_social(responseRemitente.getRazonSocial());
			remitenteNuevo.setRuc(request.getRemitenteRuc());
			remitenteNuevo.setDireccion(responseRemitente.getDireccion());
		};
		if(destinatario.isEmpty()) {
			Destinatarios destinatarioNuevo = new Destinatarios();
			destinatarioNuevo.setRuc(request.getDestinatarioRuc());
			destinatarioNuevo.setDireccion(responseDestinatario.getDireccion());
			destinatarioNuevo.setRazon_social(responseDestinatario.getRazonSocial());
		};
		//Optional<Trabajador> trabajador= trabajadorRepository.getTrabajadorByNumIdentidad(request.getNumDocChofer());
		GuiaTransportista guiaTransportistaGuardar = new GuiaTransportista();
		guiaTransportistaGuardar.setSerieGuia(GlobalData.serieGuia);
		guiaTransportistaGuardar.setPartida(request.getPartida());
		guiaTransportistaGuardar.setLlegada(request.getLlegada());
		guiaTransportistaGuardar.setFechaTraslado(request.getFechaTraslado());
		guiaTransportistaGuardar.setPesoCarga(request.getPesoCarga());
		guiaTransportistaGuardar.setNumDocConductor(request.getNumDocChofer());
		//guiaTransportistaGuardar.setNombreConductor(trabajador.getNombreConductor);
		guiaTransportistaGuardar.setPlacaVehiculo(request.getPlacaVehiculo());
		guiaTransportistaGuardar.setRucPagadorDelFlete(request.getRucPagadorDelFlete());
		guiaTransportistaGuardar.setRemitente(remitente.get());
		guiaTransportistaGuardar.setDestinatario(destinatario.get());
		GuiaTransportista guiatransp = data.save(guiaTransportistaGuardar);
		
		if(!guiatransp.equals(null)) {
			res=1;
		}
		return res;
	}
	public SunatResponse getExecution(String numero){
        String authorization = "Bearer " + tokenApi;
        return sunatClient.getInfo(numero, authorization);
    }
	
}
