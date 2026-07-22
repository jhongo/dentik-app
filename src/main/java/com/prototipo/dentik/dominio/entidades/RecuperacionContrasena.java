package com.prototipo.dentik.dominio.entidades;

import java.time.LocalDateTime;

public class RecuperacionContrasena {

	private int idRecuperacion;
	private int idUsuario;
	private String token;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaExpiracion;
	private boolean utilizado;

	public int getIdRecuperacion() {
		return idRecuperacion;
	}

	public void setIdRecuperacion(int idRecuperacion) {
		this.idRecuperacion = idRecuperacion;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public boolean isUtilizado() {
		return utilizado;
	}

	public void setUtilizado(boolean utilizado) {
		this.utilizado = utilizado;
	}

}
