package com.panda.demo.conductores_camiones.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panda.demo.conductores_camiones.modelos.conductoresCamion;

@Repository
public interface IconductoresCamion extends CrudRepository<conductoresCamion , Integer> {

}
