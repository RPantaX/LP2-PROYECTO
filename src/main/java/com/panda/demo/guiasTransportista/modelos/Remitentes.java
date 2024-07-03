package com.panda.demo.guiasTransportista.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "remitentes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Remitentes {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ruc", nullable = false)
    private String ruc;
	
	@Column(name = "razon_social", nullable = false)
	private String razon_social;
	
	@Column(name = "direccion", nullable = false)
	private String direccion;
}
