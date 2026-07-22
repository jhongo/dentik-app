package com.prototipo.dentik.presentacion.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prototipo.dentik.aplicacion.casosuso.entrada.ISesionUsuarioUseCase;
import com.prototipo.dentik.presentacion.dto.response.SesionUsuarioResponseDTO;
import com.prototipo.dentik.presentacion.mapeadores.ISesionUsuarioDtoMapper;

@RestController
@RequestMapping("/api/sesiones")
public class SesionUsuarioController {

	private final ISesionUsuarioUseCase sesionUseCase;
	private final ISesionUsuarioDtoMapper mapper;

	public SesionUsuarioController(ISesionUsuarioUseCase sesionUseCase, ISesionUsuarioDtoMapper mapper) {
		this.sesionUseCase = sesionUseCase;
		this.mapper = mapper;
	}

	@GetMapping
	public List<SesionUsuarioResponseDTO> listarTodo() {
		return sesionUseCase.listarTodos().stream().map(mapper::toResponseDto).toList();
	}

	@GetMapping("/usuario/{idUsuario}")
	public List<SesionUsuarioResponseDTO> buscarPorUsuario(@PathVariable("idUsuario") int idUsuario) {
		return sesionUseCase.buscarPorUsuario(idUsuario).stream().map(mapper::toResponseDto).toList();
	}

}
