package com.prototipo.dentik.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prototipo.dentik.dominio.entidades.Usuario;
import com.prototipo.dentik.infraestructura.persistencia.jpa.RolEntity;
import com.prototipo.dentik.infraestructura.persistencia.jpa.UsuarioEntity;

@Mapper(componentModel = "spring")
public interface IUsuarioJpaMapper {

	Usuario toDomain(UsuarioEntity entity);

	// La asignacion de roles se gestiona aparte (asignarRol), por eso se ignora al persistir.
	@Mapping(target = "roles", ignore = true)
	UsuarioEntity toEntity(Usuario usuarioPojo);

	// Convierte cada RolEntity en su nombre (para la lista de roles del dominio).
	default String map(RolEntity rol) {
		return rol == null ? null : rol.getNombre();
	}

}
