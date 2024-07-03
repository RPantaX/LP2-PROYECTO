package com.panda.demo.guiasTransportista.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panda.demo.guiasTransportista.modelos.GuiaTransportista;

@Repository
public interface IGuiasTransportista extends CrudRepository<GuiaTransportista, Integer>{

}
