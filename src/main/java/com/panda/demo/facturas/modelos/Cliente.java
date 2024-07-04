package com.panda.demo.facturas.modelos;

import com.panda.demo.guiasTransportista.modelos.Remitentes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	
	@Id
    @Column(name = "ruc", nullable = false)
    private String ruc;
	
	@Column(name = "razon_social", nullable = false)
	private String razon_social;
	
	@Column(name = "direccion", nullable = false)
	private String direccion;

}
