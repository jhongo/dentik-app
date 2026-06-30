package com.prototipo.dentik.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.prototipo.dentik.dominio.entidades.Paciente;
import com.prototipo.dentik.infraestructura.persistencia.jpa.PacienteEntity;

@Mapper(componentModel = "spring")
public interface IPacienteJpaMapper {

	Paciente toDomain(PacienteEntity entity);

	PacienteEntity toEntity(Paciente pacientePojo);

}
