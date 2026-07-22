package com.prototipo.dentik.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.prototipo.dentik.dominio.entidades.SesionUsuario;
import com.prototipo.dentik.infraestructura.persistencia.jpa.SesionUsuarioEntity;

@Mapper(componentModel = "spring")
public interface ISesionUsuarioJpaMapper {

	SesionUsuario toDomain(SesionUsuarioEntity entity);

	SesionUsuarioEntity toEntity(SesionUsuario sesionPojo);

}
