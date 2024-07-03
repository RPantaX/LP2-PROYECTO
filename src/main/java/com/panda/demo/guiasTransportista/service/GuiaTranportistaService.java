package com.panda.demo.guiasTransportista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.demo.guiasTransportista.interfaceService.IGuiasTransportistaService;
import com.panda.demo.guiasTransportista.interfaces.IGuiasTransportista;
import com.panda.demo.guiasTransportista.modelos.GuiaTransportista;

@Service
public class GuiaTranportistaService implements IGuiasTransportistaService{

	@Autowired
	private IGuiasTransportista data;

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
	public int save(GuiaTransportista g) {
		// TODO Auto-generated method stub
		int res=0;
		//Optional<Trabajador> trabajador= trabajadorRepository.getTrabajadorByNumIdentidad(request.getNumDocChofer());
		//VALIDA RUC REMITENTE Y DESTINATARIO
		
		GuiaTransportista guiatransp = data.save(g);
		if(!guiatransp.equals(null)) {
			res=1;
		}
		return res;
	}
	
}
