package com.panda.demo.facturas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.demo.facturas.interfaceService.IFacturaService;
import com.panda.demo.facturas.interfaces.IFactura;
import com.panda.demo.facturas.modelos.Factura;

@Service
public class FacturaService implements IFacturaService{
	@Autowired
	private IFactura data;
	
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
	public int save(Factura f) {
		// TODO Auto-generated method stub
		int res=0;
		Factura factura = data.save(f);
		if(!factura.equals(null)) {
			res=1;
		}
		return res;
	}


}
