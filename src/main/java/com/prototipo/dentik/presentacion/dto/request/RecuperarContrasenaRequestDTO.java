package com.prototipo.dentik.presentacion.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecuperarContrasenaRequestDTO {

	@NotBlank
	@Email
	private String correo;

}
