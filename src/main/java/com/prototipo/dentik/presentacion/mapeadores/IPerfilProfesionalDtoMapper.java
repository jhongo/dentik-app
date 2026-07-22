package com.prototipo.dentik.presentacion.mapeadores;

import org.mapstruct.Mapper;

import com.prototipo.dentik.dominio.entidades.PerfilProfesional;
import com.prototipo.dentik.presentacion.dto.request.PerfilProfesionalRequestDTO;
import com.prototipo.dentik.presentacion.dto.response.PerfilProfesionalResponseDTO;

@Mapper(componentModel = "spring")
public interface IPerfilProfesionalDtoMapper {

	PerfilProfesional toDomain(PerfilProfesionalRequestDTO dto);
	PerfilProfesionalResponseDTO toResponseDto(PerfilProfesional perfil);

}
