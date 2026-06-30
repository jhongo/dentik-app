package com.prototipo.dentik.presentacion.mapeadores;

import org.mapstruct.Mapper;

import com.prototipo.dentik.dominio.entidades.Usuario;
import com.prototipo.dentik.presentacion.dto.request.UsuarioRequestDTO;
import com.prototipo.dentik.presentacion.dto.response.UsuarioResponseDTO;

@Mapper(componentModel = "spring")
public interface IUsuarioDtoMapper {

	Usuario toDomain(UsuarioRequestDTO dto);
	UsuarioResponseDTO toResponseDto(Usuario usuario);

}
