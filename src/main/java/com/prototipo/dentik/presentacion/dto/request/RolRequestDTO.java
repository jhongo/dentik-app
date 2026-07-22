package com.prototipo.dentik.presentacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RolRequestDTO {

	@NotBlank
	private String nombre;
	private String descripcion;

}
