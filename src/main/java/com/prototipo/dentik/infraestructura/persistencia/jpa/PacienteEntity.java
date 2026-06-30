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
@Table(name = "pacientes")
public class PacienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paciente")
	private int idPaciente;
	@Column(name = "id_usuario")
	private int idUsuario;
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	@Column(name = "telefono", length = 20)
	private String telefono;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "ciudad", length = 100)
	private String ciudad;
	@Column(name = "estado_provincia", length = 100)
	private String estadoProvincia;
	@Column(name = "codigo_postal", length = 20)
	private String codigoPostal;

}
