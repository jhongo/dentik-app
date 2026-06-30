package com.prototipo.dentik.aplicacion.casosuso.impl;

import java.util.List;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IPacienteUseCase;
import com.prototipo.dentik.dominio.entidades.Paciente;
import com.prototipo.dentik.dominio.repositorios.IPacienteRepositorio;

public class PacienteUseCaseImpl implements IPacienteUseCase {

	private final IPacienteRepositorio repositorio;

	public PacienteUseCaseImpl(IPacienteRepositorio repositorio) {

		this.repositorio = repositorio;
	}

	@Override
	public Paciente guardar(Paciente nuevoPaciente) {

		return repositorio.guardar(nuevoPaciente);
	}

	@Override
	public Paciente buscarPorId(int idPaciente) {

		return repositorio.buscarPorId(idPaciente).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
	}

	@Override
	public List<Paciente> listarTodos() {

		return repositorio.listarTodos();
	}

	@Override
	public void eliminar(int idPaciente) {

		repositorio.eliminar(idPaciente);
	}

}
