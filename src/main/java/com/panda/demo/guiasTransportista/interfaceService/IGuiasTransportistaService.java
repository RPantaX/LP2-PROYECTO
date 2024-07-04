package com.panda.demo.guiasTransportista.interfaceService;

import java.util.List;
import java.util.Optional;

import com.panda.demo.guiasTransportista.modelos.GuiaTransportista;
import com.panda.demo.guiasTransportista.request.GuiaTransportistaRequest;

public interface IGuiasTransportistaService {
	public List<GuiaTransportista> listar();
	public Optional<GuiaTransportista> listarId(int id);
	public int save(GuiaTransportistaRequest request);
}