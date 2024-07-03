package com.panda.demo.facturas.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panda.demo.facturas.modelos.Factura;

@Repository
public interface IFactura extends CrudRepository<Factura, Integer>{

}
