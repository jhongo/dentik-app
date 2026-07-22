package com.prototipo.dentik.infraestructura.persistencia.jpa;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// Entidades y Data limpia (Clean Code)

@Data
@Entity
@Table(name = "sesionesusuario")
public class SesionUsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sesion")
	private int idSesion;
	@Column(name = "id_usuario")
	private int idUsuario;
	@CreationTimestamp
	@Column(name = "fecha_inicio", updatable = false)
	private LocalDateTime fechaInicio;
	@Column(name = "fecha_fin")
	private LocalDateTime fechaFin;
	@Column(name = "direccion_ip", length = 45)
	private String direccionIp;
	@Column(name = "navegador", length = 255)
	private String navegador;
	@Column(name = "estado", length = 20)
	private String estado;

}
