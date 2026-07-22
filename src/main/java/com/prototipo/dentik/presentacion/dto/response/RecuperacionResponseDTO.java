package com.prototipo.dentik.presentacion.dto.response;

public class RecuperacionResponseDTO {

	private String token;
	private String mensaje;

	public RecuperacionResponseDTO() {
	}

	public RecuperacionResponseDTO(String token, String mensaje) {
		this.token = token;
		this.mensaje = mensaje;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
