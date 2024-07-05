package com.panda.demo.trabajador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.panda.demo.errors.CustomErrors;
import com.panda.demo.feignClient.ReniecClient;
import com.panda.demo.feignClient.response.ReniecResponse;
import com.panda.demo.trabajador.interfaceService.ITrabajadorService;
import com.panda.demo.trabajador.interfaces.ITrabajador;
import com.panda.demo.trabajador.modelos.Trabajador;
import com.panda.demo.trabajador.request.TrabajadorRequest;

public class TrabajadorService implements ITrabajadorService{
	@Autowired
	ITrabajador data;
	@Autowired
	ReniecClient reniecClient;
	@Value("${token.api}")
    private String tokenApi;
	@Override
	public List<com.panda.demo.trabajador.modelos.Trabajador> listar() {
		// TODO Auto-generated method stub
		return (List<Trabajador>) data.findAll();
	}

	@Override
	public Optional<com.panda.demo.trabajador.modelos.Trabajador> listarId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}

	@Override
	public int save(TrabajadorRequest trabajador) {
		int res=0;
		// TODO Auto-generated method stub
		ReniecResponse reniecResponse = getExecution(trabajador.getNumIdentidad());
		if(reniecResponse ==null) {
			return CustomErrors.valorNoEncontradoEnReniec;
		}
		res=validateUniqueValues(trabajador);
		if( res!=0) {
			return res;
		}
		Trabajador trabajadorGuardar = new Trabajador();
		trabajadorGuardar.setNombretrab(reniecResponse.getNombres());
		trabajadorGuardar.setApetrab(reniecResponse.getApellidoPaterno()+reniecResponse.getApellidoMaterno());
		trabajadorGuardar.setNumident(trabajador.getNumIdentidad());
		trabajadorGuardar.setGenero(trabajador.getGenero());
		trabajadorGuardar.setEstadoCivil(reniecResponse.);
		return 0;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	public int validateUniqueValues(TrabajadorRequest trabajador) {
		
		
		Optional<Trabajador> trabPorCorreo = data.getTrabajadorByCorreo(trabajador.getEmail());
		if(trabPorCorreo.isPresent()) {
			return CustomErrors.valorExistenteEnBaseDeDatos;
		}
		Optional<Trabajador> trabPorNumIdent = data.getTrabajadorByNumident(trabajador.getEmail());

		if(trabPorNumIdent.isPresent()) {
			return CustomErrors.valorExistenteEnBaseDeDatos;
		}
		return 0;
	}
	public ReniecResponse getExecution(String numero){
        String authorization = "Bearer " + tokenApi;
        return reniecClient.getInfo(numero, authorization);
    }

}
