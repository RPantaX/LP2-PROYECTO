package com.panda.demo.conductores_camiones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.demo.conductores_camiones.interfaceService.IconductoresCamionService;
import com.panda.demo.conductores_camiones.interfaces.IconductoresCamion;
import com.panda.demo.conductores_camiones.modelos.conductoresCamion;

@Service
public class conductoresCamionService implements IconductoresCamionService {
    @Autowired
    private IconductoresCamion data;
    
    @Override
    public List<conductoresCamion> listar() {
        return (List<conductoresCamion>) data.findAll();
    }

    @Override
    public Optional<conductoresCamion> listarId(int id) {
        return data.findById(id);
    }

    @Override
    public int save(conductoresCamion cc) {
        int res = 0;
        conductoresCamion conductoresCamion = data.save(cc);
        if (cc != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
}
