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
@Table(name = "recuperacioncontrasena")
public class RecuperacionContrasenaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_recuperacion")
	private int idRecuperacion;
	@Column(name = "id_usuario")
	private int idUsuario;
	@Column(name = "token", length = 255, unique = true)
	private String token;
	@CreationTimestamp
	@Column(name = "fecha_creacion", updatable = false)
	private LocalDateTime fechaCreacion;
	@Column(name = "fecha_expiracion")
	private LocalDateTime fechaExpiracion;
	@Column(name = "utilizado")
	private boolean utilizado;

}
