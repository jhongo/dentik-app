package com.prototipo.dentik.aplicacion.casosuso.entrada;

import java.util.List;

import com.prototipo.dentik.dominio.entidades.PerfilProfesional;

public interface IPerfilProfesionalUseCase {

	PerfilProfesional guardar(PerfilProfesional nuevoPerfil);
	PerfilProfesional buscarPorId(int idPerfil);
	PerfilProfesional buscarPorUsuario(int idUsuario);
	List<PerfilProfesional> listarTodos();
	void eliminar(int idPerfil);

}
