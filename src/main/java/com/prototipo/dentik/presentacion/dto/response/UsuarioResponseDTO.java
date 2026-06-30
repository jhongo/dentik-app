package com.prototipo.dentik.presentacion.dto.response;

import java.time.LocalDateTime;

public class UsuarioResponseDTO {

	private int idUsuario;
	private String nombre;
	private String apellido;
	private String documento;
	private String correo;
	private String rol;
	private String estado;
	private LocalDateTime fechaRegistro;
	private LocalDateTime ultimaSesion;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public LocalDateTime getUltimaSesion() {
		return ultimaSesion;
	}

	public void setUltimaSesion(LocalDateTime ultimaSesion) {
		this.ultimaSesion = ultimaSesion;
	}

}
