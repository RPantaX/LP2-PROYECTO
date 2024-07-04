package com.panda.demo.facturas.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panda.demo.facturas.modelos.Item;
@Repository
public interface IItem extends CrudRepository<Item, Integer>{

}
