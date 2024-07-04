package com.panda.demo.guiasTransportista.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panda.demo.guiasTransportista.modelos.Destinatarios;


@Repository
public interface IDestinatarios extends CrudRepository<Destinatarios, String>{

}
