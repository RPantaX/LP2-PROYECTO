package com.panda.demo.trabajador.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panda.demo.trabajador.modelos.Trabajador;

@Repository
public interface ITrabajador extends CrudRepository<Trabajador, Integer>{
	@Query(value = "SELECT *FROM trabajadores WHERE num_identidad = :numident", nativeQuery = true)
    Optional<Trabajador> getTrabajadorByNumident(String numident);
	
	@Query(value = "SELECT *FROM trabajadores WHERE email = :correo", nativeQuery = true)
    Optional<Trabajador> getTrabajadorByCorreo(String correo);
}
