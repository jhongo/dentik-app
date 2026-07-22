package com.prototipo.dentik.infraestructura.persistencia.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	@Column(name = "documento", length = 20, unique = true)
	private String documento;
	@Column(name = "correo", length = 100, unique = true)
	private String correo;
	@Column(name = "contrasena", length = 255)
	private String contrasena;
	@Column(name = "estado", length = 20)
	private String estado;
	@CreationTimestamp
	@Column(name = "fecha_registro", updatable = false)
	private LocalDateTime fechaRegistro;
	@Column(name = "ultima_sesion")
	private LocalDateTime ultimaSesion;
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	@Column(name = "telefono", length = 20)
	private String telefono;
	@Column(name = "direccion", columnDefinition = "TEXT")
	private String direccion;
	@Column(name = "ciudad", length = 100)
	private String ciudad;
	@Column(name = "estado_provincia", length = 100)
	private String estadoProvincia;
	@Column(name = "codigo_postal", length = 20)
	private String codigoPostal;

	// Relacion muchos a muchos con Roles (tabla intermedia usuarioroles).
	// La columna fecha_asignacion la completa el DEFAULT de la base de datos.
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarioroles",
			joinColumns = @JoinColumn(name = "id_usuario"),
			inverseJoinColumns = @JoinColumn(name = "id_rol"))
	private List<RolEntity> roles = new ArrayList<>();

}
