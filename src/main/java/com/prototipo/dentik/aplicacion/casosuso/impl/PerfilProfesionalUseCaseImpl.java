package com.prototipo.dentik.aplicacion.casosuso.impl;

import java.time.LocalDate;
import java.util.List;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IPerfilProfesionalUseCase;
import com.prototipo.dentik.dominio.entidades.PerfilProfesional;
import com.prototipo.dentik.dominio.repositorios.IPerfilProfesionalRepositorio;

public class PerfilProfesionalUseCaseImpl implements IPerfilProfesionalUseCase {

	private final IPerfilProfesionalRepositorio repositorio;

	public PerfilProfesionalUseCaseImpl(IPerfilProfesionalRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public PerfilProfesional guardar(PerfilProfesional nuevoPerfil) {
		if (nuevoPerfil.getFechaIngreso() == null) {
			nuevoPerfil.setFechaIngreso(LocalDate.now());
		}
		return repositorio.guardar(nuevoPerfil);
	}

	@Override
	public PerfilProfesional buscarPorId(int idPerfil) {
		return repositorio.buscarPorId(idPerfil).orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
	}

	@Override
	public PerfilProfesional buscarPorUsuario(int idUsuario) {
		return repositorio.buscarPorUsuario(idUsuario)
				.orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
	}

	@Override
	public List<PerfilProfesional> listarTodos() {
		return repositorio.listarTodos();
	}

	@Override
	public void eliminar(int idPerfil) {
		repositorio.eliminar(idPerfil);
	}

}
