package com.panda.demo.conductores_camiones.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conductores_camion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class conductoresCamion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //cambiar por ManyToOne (tabla trabajador)
    @Column(name = "trabajador_id", nullable = false, unique = true)
    private Integer trabajadorId;

    @ManyToOne
    @JoinColumn(name = "tipo_licencia_id")
    private tipoLicencia tipoLicencia;

    @ManyToOne
    @JoinColumn(name = "camion_id")
    private camion camion;

    @Column(name = "cert_conducir_camion")
    private Boolean certConducirCamion;

    @Column(name = "cert_psicofisico")
    private Boolean certPsicofisico;

    @Column(name = "cert_mecanica_basica")
    private Boolean certMecanicaBasica;

    @Column(name = "cert_primeros_auxilios")
    private Boolean certPrimerosAuxilios;

    @Column(name = "cert_seguridad_vial")
    private Boolean certSeguridadVial;
}


