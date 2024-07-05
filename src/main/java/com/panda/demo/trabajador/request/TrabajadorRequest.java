package com.panda.demo.trabajador.request;

import java.sql.Timestamp;

import com.panda.demo.trabajador.modelos.Cargos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrabajadorRequest {
	private String numIdentidad;
	private Timestamp fechaNacimiento;
	private String genero;
	private String estadoCivil;
	private String telefono;
	private String email;
	private Cargos cargo;
	private String numCuentaBancaria;
	private String estado;
}
