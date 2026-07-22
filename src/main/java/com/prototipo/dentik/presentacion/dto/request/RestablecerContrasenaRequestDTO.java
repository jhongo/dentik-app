package com.prototipo.dentik.presentacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RestablecerContrasenaRequestDTO {

	@NotBlank
	private String token;
	@NotBlank
	private String nuevaContrasena;

}
