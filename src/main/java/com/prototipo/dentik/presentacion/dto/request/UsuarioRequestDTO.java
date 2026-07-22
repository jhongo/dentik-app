package com.prototipo.dentik.presentacion.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
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
	@Email
	private String correo;
	@NotBlank
	private String contrasena;
	// Rol a asignar (Paciente, Odontologo, Recepcionista, Administrador).
	// Opcional: en el registro publico se asume 'Paciente'.
	private String rol;
	private String estado;
	private LocalDate fechaNacimiento;
	private String telefono;
	private String direccion;
	private String ciudad;
	private String estadoProvincia;
	private String codigoPostal;

}
