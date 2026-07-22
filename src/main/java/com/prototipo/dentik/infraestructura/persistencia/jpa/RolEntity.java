package com.prototipo.dentik.infraestructura.persistencia.jpa;

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
@Table(name = "roles")
public class RolEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private int idRol;
	@Column(name = "nombre", length = 50, unique = true)
	private String nombre;
	@Column(name = "descripcion", columnDefinition = "TEXT")
	private String descripcion;

}
