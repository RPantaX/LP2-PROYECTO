package com.panda.demo.trabajador.modelos;

import java.sql.Timestamp;

import com.panda.demo.conductores_camiones.modelos.conductoresCamion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "trabajadores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trabajador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private int idtrab;
	
	@Column(name="nombres")
	private String nombretrab;
	
	@Column(name="apellidos")
	private String apetrab;
	
    @Column(name="num_identidad")
    private String numident;
    
    @Column(name="fecha_nacimiento")
    private String fechanacido;
    
    @Column (name="genero")
    private String genero;
    
    @Column (name="estado_civil")
    private String estadoCivil;
    
    @Column (name="nacionalidad")
    private String nacionalidad;
    
    @Column (name="direccion_residencia", length = 255)
    private String direcres;
    
    @Column (name="telefono")
    private String cel;
    
    @Column (name="email")
    private String correo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargos cargo;
    
    @Column (name="fecha_ingreso")
    private Timestamp fecingre;
    @PrePersist
    protected void onCreate() {
        // Verificar si la fecha de ingreso ya está establecida
        if (this.fecingre == null) {
            // Si la fecha de ingreso no está establecida, establecerla como la fecha y hora
            // actual
            this.fecingre = new Timestamp(System.currentTimeMillis());
        }
    }
    @Column (name="num_cuenta_bancaria")
    private String numbanco;
    
    @Column (name="estado")
    private String est;
    
    @OneToOne
    private conductoresCamion conductoresCam;
}
