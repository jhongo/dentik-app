package com.prototipo.dentik.aplicacion.casosuso.impl;

import java.util.List;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.prototipo.dentik.dominio.entidades.Usuario;
import com.prototipo.dentik.dominio.repositorios.IUsuarioRepositorio;

public class UsuarioUseCaseImpl implements IUsuarioUseCase {

	private final IUsuarioRepositorio repositorio;

	public UsuarioUseCaseImpl(IUsuarioRepositorio repositorio) {

		this.repositorio = repositorio;
	}

	@Override
	public Usuario guardar(Usuario nuevoUsuario) {

		// Regla de negocio: un usuario nuevo se registra como 'Activo' por defecto
		if (nuevoUsuario.getEstado() == null || nuevoUsuario.getEstado().isBlank()) {
			nuevoUsuario.setEstado("Activo");
		}

		return repositorio.guardar(nuevoUsuario);
	}

	@Override
	public Usuario buscarPorId(int idUsuario) {

		return repositorio.buscarPorId(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	}

	@Override
	public List<Usuario> listarTodos() {

		return repositorio.listarTodos();
	}

	@Override
	public void eliminar(int idUsuario) {

		repositorio.eliminar(idUsuario);
	}

}
