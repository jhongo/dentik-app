package com.prototipo.dentik.presentacion.mapeadores;

import org.mapstruct.Mapper;

import com.prototipo.dentik.dominio.entidades.Paciente;
import com.prototipo.dentik.presentacion.dto.request.PacienteRequestDTO;
import com.prototipo.dentik.presentacion.dto.response.PacienteResponseDTO;

@Mapper(componentModel = "spring")
public interface IPacienteDtoMapper {

	Paciente toDomain(PacienteRequestDTO dto);
	PacienteResponseDTO toResponseDto(Paciente paciente);

}
