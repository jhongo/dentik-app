package com.prototipo.dentik.aplicacion.casosuso.impl;

import java.util.List;

import com.prototipo.dentik.aplicacion.casosuso.entrada.ISesionUsuarioUseCase;
import com.prototipo.dentik.dominio.entidades.SesionUsuario;
import com.prototipo.dentik.dominio.repositorios.ISesionUsuarioRepositorio;

public class SesionUsuarioUseCaseImpl implements ISesionUsuarioUseCase {

	private final ISesionUsuarioRepositorio repositorio;

	public SesionUsuarioUseCaseImpl(ISesionUsuarioRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public SesionUsuario iniciarSesion(int idUsuario, String direccionIp, String navegador) {
		SesionUsuario sesion = new SesionUsuario();
		sesion.setIdUsuario(idUsuario);
		sesion.setDireccionIp(direccionIp);
		sesion.setNavegador(navegador);
		sesion.setEstado("Activa");
		return repositorio.guardar(sesion);
	}

	@Override
	public SesionUsuario buscarPorId(int idSesion) {
		return repositorio.buscarPorId(idSesion).orElseThrow(() -> new RuntimeException("Sesion no encontrada"));
	}

	@Override
	public List<SesionUsuario> buscarPorUsuario(int idUsuario) {
		return repositorio.buscarPorUsuario(idUsuario);
	}

	@Override
	public List<SesionUsuario> listarTodos() {
		return repositorio.listarTodos();
	}

}
