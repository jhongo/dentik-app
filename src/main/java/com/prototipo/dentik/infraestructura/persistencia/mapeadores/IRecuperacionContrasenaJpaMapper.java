package com.prototipo.dentik.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.prototipo.dentik.dominio.entidades.RecuperacionContrasena;
import com.prototipo.dentik.infraestructura.persistencia.jpa.RecuperacionContrasenaEntity;

@Mapper(componentModel = "spring")
public interface IRecuperacionContrasenaJpaMapper {

	RecuperacionContrasena toDomain(RecuperacionContrasenaEntity entity);

	RecuperacionContrasenaEntity toEntity(RecuperacionContrasena recuperacionPojo);

}
