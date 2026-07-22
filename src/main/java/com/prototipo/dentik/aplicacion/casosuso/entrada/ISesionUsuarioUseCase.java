package com.prototipo.dentik.aplicacion.casosuso.entrada;

import java.util.List;

import com.prototipo.dentik.dominio.entidades.SesionUsuario;

public interface ISesionUsuarioUseCase {

	// Registra el inicio de una sesion para un usuario.
	SesionUsuario iniciarSesion(int idUsuario, String direccionIp, String navegador);
	SesionUsuario buscarPorId(int idSesion);
	List<SesionUsuario> buscarPorUsuario(int idUsuario);
	List<SesionUsuario> listarTodos();

}
