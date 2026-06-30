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
@Table(name = "usuarios")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int idUsuario;
	@Column(name = "nombre", length = 100)
	private String nombre;
	@Column(name = "apellido", length = 100)
	private String apellido;
	@Column(name = "documento", length = 20)
	private String documento;
	@Column(name = "correo", length = 100)
	private String correo;
	@Column(name = "contrasena", length = 255)
	private String contrasena;
	@Column(name = "rol", length = 50)
	private String rol;
	@Column(name = "estado", length = 20)
	private String estado;
	@CreationTimestamp
	@Column(name = "fecha_registro", updatable = false)
	private LocalDateTime fechaRegistro;
	@Column(name = "ultima_sesion")
	private LocalDateTime ultimaSesion;

}
