package com.prototipo.dentik.presentacion.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PerfilProfesionalRequestDTO {

	@NotNull
	private Integer idUsuario;
	private String especialidad;
	private String colegiatura;
	private LocalDate fechaIngreso;

}
