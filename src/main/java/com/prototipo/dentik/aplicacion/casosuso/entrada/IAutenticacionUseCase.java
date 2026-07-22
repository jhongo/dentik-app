package com.prototipo.dentik.aplicacion.casosuso.entrada;

import com.prototipo.dentik.dominio.entidades.Usuario;

public interface IAutenticacionUseCase {

	// HU-SGCE-0002: valida credenciales y registra la sesion.
	Usuario login(String correo, String contrasena, String direccionIp, String navegador);

	// HU-SGCE-0003 Escenario 1: genera un token temporal de recuperacion y lo devuelve.
	String solicitarRecuperacion(String correo);

	// HU-SGCE-0003 Escenario 2: valida el token y actualiza la contrasena.
	void restablecerContrasena(String token, String nuevaContrasena);

}
