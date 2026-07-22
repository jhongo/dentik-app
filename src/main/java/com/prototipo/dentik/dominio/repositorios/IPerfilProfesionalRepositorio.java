package com.prototipo.dentik.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.PerfilProfesional;

public interface IPerfilProfesionalRepositorio {

	PerfilProfesional guardar(PerfilProfesional nuevoPerfil);
	Optional<PerfilProfesional> buscarPorId(int idPerfil);
	Optional<PerfilProfesional> buscarPorUsuario(int idUsuario);
	List<PerfilProfesional> listarTodos();
	void eliminar(int idPerfil);

}
