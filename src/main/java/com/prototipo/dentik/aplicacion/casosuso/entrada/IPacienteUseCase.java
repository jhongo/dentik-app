package com.prototipo.dentik.aplicacion.casosuso.entrada;

import java.util.List;

import com.prototipo.dentik.dominio.entidades.Paciente;

public interface IPacienteUseCase {

	Paciente guardar(Paciente nuevoPaciente);
	Paciente buscarPorId(int idPaciente);
	List<Paciente> listarTodos();
	void eliminar(int idPaciente);

}
