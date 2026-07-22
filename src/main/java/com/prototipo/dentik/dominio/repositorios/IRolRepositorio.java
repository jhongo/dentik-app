package com.prototipo.dentik.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.Rol;

public interface IRolRepositorio {

	Rol guardar(Rol nuevoRol);
	Optional<Rol> buscarPorId(int idRol);
	Optional<Rol> buscarPorNombre(String nombre);
	List<Rol> listarTodos();
	void eliminar(int idRol);

}
