package com.panda.demo.conductores_camiones.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panda.demo.conductores_camiones.modelos.camion;

@Repository
public interface ICamion extends CrudRepository<camion, Integer>{

}
