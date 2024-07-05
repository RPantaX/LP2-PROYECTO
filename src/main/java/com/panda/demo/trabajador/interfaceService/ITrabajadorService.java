package com.panda.demo.trabajador.interfaceService;

import java.util.List;
import java.util.Optional;

import com.panda.demo.trabajador.modelos.Trabajador;
import com.panda.demo.trabajador.request.TrabajadorRequest;

public interface ITrabajadorService {
	public List<Trabajador> listar();
	public Optional<Trabajador> listarId(int id);
	public int save(TrabajadorRequest trabajador);
	public void delete(int id);
}
