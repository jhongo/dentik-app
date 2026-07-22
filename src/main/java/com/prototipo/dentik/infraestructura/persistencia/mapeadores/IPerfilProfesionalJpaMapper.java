package com.prototipo.dentik.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.prototipo.dentik.dominio.entidades.PerfilProfesional;
import com.prototipo.dentik.infraestructura.persistencia.jpa.PerfilProfesionalEntity;

@Mapper(componentModel = "spring")
public interface IPerfilProfesionalJpaMapper {

	PerfilProfesional toDomain(PerfilProfesionalEntity entity);

	PerfilProfesionalEntity toEntity(PerfilProfesional perfilPojo);

}
