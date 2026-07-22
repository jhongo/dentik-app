package com.prototipo.dentik.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.prototipo.dentik.dominio.entidades.Rol;
import com.prototipo.dentik.infraestructura.persistencia.jpa.RolEntity;

@Mapper(componentModel = "spring")
public interface IRolJpaMapper {

	Rol toDomain(RolEntity entity);

	RolEntity toEntity(Rol rolPojo);

}
