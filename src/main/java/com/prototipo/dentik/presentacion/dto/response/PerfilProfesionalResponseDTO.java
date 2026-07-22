package com.prototipo.dentik.presentacion.dto.response;

import java.time.LocalDate;

public class PerfilProfesionalResponseDTO {

	private int idPerfil;
	private int idUsuario;
	private String especialidad;
	private String colegiatura;
	private LocalDate fechaIngreso;

	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getColegiatura() {
		return colegiatura;
	}

	public void setColegiatura(String colegiatura) {
		this.colegiatura = colegiatura;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

}
