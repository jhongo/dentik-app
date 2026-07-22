package com.prototipo.dentik.aplicacion.casosuso.entrada;

import java.util.List;

import com.prototipo.dentik.dominio.entidades.Usuario;

public interface IUsuarioUseCase {

	Usuario guardar(Usuario nuevoUsuario);
	Usuario buscarPorId(int idUsuario);
	List<Usuario> listarTodos();
	void eliminar(int idUsuario);

	// Registra un usuario validando duplicados y le asigna un rol por su nombre.
	Usuario registrar(Usuario nuevoUsuario, String nombreRol);
	Usuario actualizar(Usuario usuario);
	Usuario buscarPorCorreo(String correo);
	List<Usuario> buscarPorRol(String nombreRol);
	Usuario asignarRol(int idUsuario, int idRol);
	// Valida credenciales (correo + contrasena). Lanza excepcion si no coinciden.
	Usuario autenticar(String correo, String contrasena);

}
