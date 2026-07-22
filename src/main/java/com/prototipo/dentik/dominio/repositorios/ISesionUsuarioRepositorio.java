package com.prototipo.dentik.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.SesionUsuario;

public interface ISesionUsuarioRepositorio {

	SesionUsuario guardar(SesionUsuario nuevaSesion);
	Optional<SesionUsuario> buscarPorId(int idSesion);
	List<SesionUsuario> buscarPorUsuario(int idUsuario);
	List<SesionUsuario> listarTodos();

}
