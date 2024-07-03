package com.panda.demo.facturas.interfaceService;

import java.util.List;
import java.util.Optional;

import com.panda.demo.facturas.modelos.Factura;

public interface IFacturaService {
	public List<Factura> listar();
	public Optional<Factura> listarId(int id);
	public int save(Factura f);
}