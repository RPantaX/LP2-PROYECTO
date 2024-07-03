package com.panda.demo.conductores_camiones.interfaceService;

import com.panda.demo.conductores_camiones.modelos.conductoresCamion;
import java.util.List;
import java.util.Optional;

public interface IconductoresCamionService {
    public List<conductoresCamion> listar();
    public Optional<conductoresCamion> listarId(int id);
    public int save(conductoresCamion conductorCamion);
    public void delete(int id);
}