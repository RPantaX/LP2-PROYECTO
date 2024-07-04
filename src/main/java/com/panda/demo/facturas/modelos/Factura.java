package com.panda.demo.facturas.modelos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "facturas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_factura")
    private Integer numeroFactura;;

    @Column(name = "serie_factura", nullable = false)
    private String serieFactura;

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

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    @Column(name = "igv", nullable = false)
    private BigDecimal igv;

    @Column(name = "monto_total")
    private BigDecimal montoTotal;
    
    @Column(name = "OBSERVACION", nullable = false, length = 255)
    private String observacion;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "factura")
    private Set<Item> items;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruc", nullable = false)
    private Cliente cliente;
}
