package com.prototipo.dentik.presentacion.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

	@NotBlank
	private String nombre;
	@NotBlank
	private String apellido;
	@NotBlank
	private String documento;
	@NotBlank
	private String correo;
	@NotBlank
	private String contrasena;
	@NotBlank
	private String rol;
	private String estado;

}
