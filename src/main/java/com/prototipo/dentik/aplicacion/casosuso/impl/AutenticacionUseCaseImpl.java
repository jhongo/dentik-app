package com.prototipo.dentik.aplicacion.casosuso.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IAutenticacionUseCase;
import com.prototipo.dentik.aplicacion.casosuso.entrada.ISesionUsuarioUseCase;
import com.prototipo.dentik.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.prototipo.dentik.dominio.entidades.RecuperacionContrasena;
import com.prototipo.dentik.dominio.entidades.Usuario;
import com.prototipo.dentik.dominio.repositorios.IRecuperacionContrasenaRepositorio;

public class AutenticacionUseCaseImpl implements IAutenticacionUseCase {

	private static final int HORAS_VIGENCIA_TOKEN = 24;

	private final IUsuarioUseCase usuarioUseCase;
	private final ISesionUsuarioUseCase sesionUseCase;
	private final IRecuperacionContrasenaRepositorio recuperacionRepositorio;

	public AutenticacionUseCaseImpl(IUsuarioUseCase usuarioUseCase, ISesionUsuarioUseCase sesionUseCase,
			IRecuperacionContrasenaRepositorio recuperacionRepositorio) {
		this.usuarioUseCase = usuarioUseCase;
		this.sesionUseCase = sesionUseCase;
		this.recuperacionRepositorio = recuperacionRepositorio;
	}

	@Override
	public Usuario login(String correo, String contrasena, String direccionIp, String navegador) {

		Usuario usuario = usuarioUseCase.autenticar(correo, contrasena);
		sesionUseCase.iniciarSesion(usuario.getIdUsuario(), direccionIp, navegador);
		return usuario;
	}

	@Override
	public String solicitarRecuperacion(String correo) {

		Usuario usuario = usuarioUseCase.buscarPorCorreo(correo);

		RecuperacionContrasena recuperacion = new RecuperacionContrasena();
		recuperacion.setIdUsuario(usuario.getIdUsuario());
		recuperacion.setToken(UUID.randomUUID().toString());
		recuperacion.setFechaExpiracion(LocalDateTime.now().plusHours(HORAS_VIGENCIA_TOKEN));
		recuperacion.setUtilizado(false);

		// En un entorno real este token se enviaria por correo electronico.
		return recuperacionRepositorio.guardar(recuperacion).getToken();
	}

	@Override
	public void restablecerContrasena(String token, String nuevaContrasena) {

		RecuperacionContrasena recuperacion = recuperacionRepositorio.buscarPorToken(token)
				.orElseThrow(() -> new RuntimeException("Token invalido"));

		if (recuperacion.isUtilizado()) {
			throw new RuntimeException("El token ya fue utilizado");
		}
		if (recuperacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("El token ha expirado");
		}

		Usuario usuario = usuarioUseCase.buscarPorId(recuperacion.getIdUsuario());
		usuario.setContrasena(nuevaContrasena);
		usuarioUseCase.guardar(usuario);

		recuperacion.setUtilizado(true);
		recuperacionRepositorio.guardar(recuperacion);
	}

}
