package com.prototipo.dentik.presentacion.mapeadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prototipo.dentik.dominio.entidades.Usuario;
import com.prototipo.dentik.presentacion.dto.request.UsuarioRequestDTO;
import com.prototipo.dentik.presentacion.dto.response.UsuarioResponseDTO;

@Mapper(componentModel = "spring")
public interface IUsuarioDtoMapper {

	// El rol viaja como campo suelto en el request y se asigna en el caso de uso.
	@Mapping(target = "roles", ignore = true)
	Usuario toDomain(UsuarioRequestDTO dto);

	UsuarioResponseDTO toResponseDto(Usuario usuario);

}
