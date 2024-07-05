package com.panda.demo.trabajador.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panda.demo.trabajador.modelos.Cargos;

@Repository
public interface ICargos extends CrudRepository<Cargos, Integer>{

}
