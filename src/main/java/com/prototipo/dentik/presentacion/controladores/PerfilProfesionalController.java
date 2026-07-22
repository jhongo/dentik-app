package com.prototipo.dentik.presentacion.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IPerfilProfesionalUseCase;
import com.prototipo.dentik.presentacion.dto.request.PerfilProfesionalRequestDTO;
import com.prototipo.dentik.presentacion.dto.response.PerfilProfesionalResponseDTO;
import com.prototipo.dentik.presentacion.mapeadores.IPerfilProfesionalDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/perfiles-profesionales")
public class PerfilProfesionalController {

	private final IPerfilProfesionalUseCase perfilUseCase;
	private final IPerfilProfesionalDtoMapper mapper;

	public PerfilProfesionalController(IPerfilProfesionalUseCase perfilUseCase, IPerfilProfesionalDtoMapper mapper) {
		this.perfilUseCase = perfilUseCase;
		this.mapper = mapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PerfilProfesionalResponseDTO guardar(@Valid @RequestBody PerfilProfesionalRequestDTO dto) {
		return mapper.toResponseDto(perfilUseCase.guardar(mapper.toDomain(dto)));
	}

	@GetMapping
	public List<PerfilProfesionalResponseDTO> listarTodo() {
		return perfilUseCase.listarTodos().stream().map(mapper::toResponseDto).toList();
	}

	@GetMapping("/{id}")
	public PerfilProfesionalResponseDTO buscarPorId(@PathVariable("id") int idPerfil) {
		return mapper.toResponseDto(perfilUseCase.buscarPorId(idPerfil));
	}

	@GetMapping("/usuario/{idUsuario}")
	public PerfilProfesionalResponseDTO buscarPorUsuario(@PathVariable("idUsuario") int idUsuario) {
		return mapper.toResponseDto(perfilUseCase.buscarPorUsuario(idUsuario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") int idPerfil) {
		perfilUseCase.eliminar(idPerfil);
		return ResponseEntity.noContent().build();
	}

}
