package com.prototipo.dentik.dominio.repositorios;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.Usuario;

public interface IUsuarioRepositorio {

	Usuario guardar(Usuario nuevoUsuario);
	Optional<Usuario> buscarPorId(int idUsuario);
	List<Usuario> listarTodos();
	void eliminar(int idUsuario);

	Optional<Usuario> buscarPorCorreo(String correo);
	Optional<Usuario> buscarPorDocumento(String documento);
	boolean existePorCorreo(String correo);
	boolean existePorDocumento(String documento);
	List<Usuario> buscarPorRol(String nombreRol);
	Usuario asignarRol(int idUsuario, int idRol);

}
