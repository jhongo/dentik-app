package com.prototipo.dentik.aplicacion.casosuso.entrada;

import java.util.List;

import com.prototipo.dentik.dominio.entidades.Usuario;

public interface IUsuarioUseCase {

	Usuario guardar(Usuario nuevoUsuario);
	Usuario buscarPorId(int idUsuario);
	List<Usuario> listarTodos();
	void eliminar(int idUsuario);

}
