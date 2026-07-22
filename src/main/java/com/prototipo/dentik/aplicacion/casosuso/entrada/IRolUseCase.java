package com.prototipo.dentik.aplicacion.casosuso.entrada;

import java.util.List;

import com.prototipo.dentik.dominio.entidades.Rol;

public interface IRolUseCase {

	Rol guardar(Rol nuevoRol);
	Rol buscarPorId(int idRol);
	Rol buscarPorNombre(String nombre);
	List<Rol> listarTodos();
	void eliminar(int idRol);

}
