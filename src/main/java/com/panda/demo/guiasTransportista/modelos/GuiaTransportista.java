package com.panda.demo.guiasTransportista.modelos;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.panda.demo.facturas.modelos.Factura;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "guias_transportista")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuiaTransportista {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_guia", nullable = false)
	private Integer numeroGuia;

    @Column(name = "serie_guia", nullable = false)
    private String serieGuia;

    @Column(name = "partida", nullable = false)
    private String partida;

    @Column(name = "llegada", nullable = false)
    private String llegada;

    @Column(name = "fecha_emision")
    private Timestamp fechaEmision;
    @PrePersist
    protected void onCreate() {
        // Verificar si la fecha de ingreso ya está establecida
        if (this.fechaEmision == null) {
            // Si la fecha de ingreso no está establecida, establecerla como la fecha y hora
            // actual
            this.fechaEmision = new Timestamp(System.currentTimeMillis());
        }
    }
    @Column(name = "fecha_traslado", nullable = false)
    private Timestamp fechaTraslado;

    @Column(name = "peso_carga", nullable = false)
    private BigDecimal pesoCarga;

    @Column(name = "num_doc_chofer", nullable = false)
    private String numDocConductor;;

    @Column(name = "nombre_chofer", nullable = false)
    private String nombreConductor;

    @Column(name = "placa_vehiculo", nullable = false)
    private String placaVehiculo;

    @Column(name = "ruc_pagador_del_flete", nullable = false)
    
    private String rucPagadorDelFlete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruc", nullable = false)
    private Remitentes remitente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruc", nullable = false)
    private Destinatarios destinatario;
}
