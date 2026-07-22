package com.prototipo.dentik.presentacion.mapeadores;

import org.mapstruct.Mapper;

import com.prototipo.dentik.dominio.entidades.SesionUsuario;
import com.prototipo.dentik.presentacion.dto.response.SesionUsuarioResponseDTO;

@Mapper(componentModel = "spring")
public interface ISesionUsuarioDtoMapper {

	SesionUsuarioResponseDTO toResponseDto(SesionUsuario sesion);

}
