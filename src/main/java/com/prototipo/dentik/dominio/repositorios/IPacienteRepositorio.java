package com.prototipo.dentik.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.Paciente;

public interface IPacienteRepositorio {

	Paciente guardar(Paciente nuevoPaciente);
	Optional<Paciente> buscarPorId(int idPaciente);
	List<Paciente> listarTodos();
	void eliminar(int idPaciente);

}
