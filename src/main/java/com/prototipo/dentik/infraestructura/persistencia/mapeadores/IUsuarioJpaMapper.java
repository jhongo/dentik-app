package com.prototipo.dentik.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.prototipo.dentik.dominio.entidades.Usuario;
import com.prototipo.dentik.infraestructura.persistencia.jpa.UsuarioEntity;

@Mapper(componentModel = "spring")
public interface IUsuarioJpaMapper {

	Usuario toDomain(UsuarioEntity entity);

	UsuarioEntity toEntity(Usuario usuarioPojo);

}
