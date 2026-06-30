package com.prototipo.dentik.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.Usuario;

public interface IUsuarioRepositorio {

	Usuario guardar(Usuario nuevoUsuario);
	Optional<Usuario> buscarPorId(int idUsuario);
	List<Usuario> listarTodos();
	void eliminar(int idUsuario);

}
