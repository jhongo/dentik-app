package com.prototipo.dentik.infraestructura.persistencia.jpa;

import java.time.LocalDate;

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
@Table(name = "perfilesprofesionales")
public class PerfilProfesionalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
	private int idPerfil;
	@Column(name = "id_usuario", unique = true)
	private int idUsuario;
	@Column(name = "especialidad", length = 100)
	private String especialidad;
	@Column(name = "colegiatura", length = 50, unique = true)
	private String colegiatura;
	@Column(name = "fecha_ingreso")
	private LocalDate fechaIngreso;

}
