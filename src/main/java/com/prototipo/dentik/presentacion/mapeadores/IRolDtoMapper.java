package com.prototipo.dentik.presentacion.mapeadores;

import org.mapstruct.Mapper;

import com.prototipo.dentik.dominio.entidades.Rol;
import com.prototipo.dentik.presentacion.dto.request.RolRequestDTO;
import com.prototipo.dentik.presentacion.dto.response.RolResponseDTO;

@Mapper(componentModel = "spring")
public interface IRolDtoMapper {

	Rol toDomain(RolRequestDTO dto);
	RolResponseDTO toResponseDto(Rol rol);

}
