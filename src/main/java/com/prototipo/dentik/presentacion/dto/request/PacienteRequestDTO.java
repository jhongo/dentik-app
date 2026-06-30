package com.prototipo.dentik.presentacion.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PacienteRequestDTO {

	@NotNull
	private Integer idUsuario;
	private LocalDate fechaNacimiento;
	private String telefono;
	private String direccion;
	private String ciudad;
	private String estadoProvincia;
	private String codigoPostal;

}
