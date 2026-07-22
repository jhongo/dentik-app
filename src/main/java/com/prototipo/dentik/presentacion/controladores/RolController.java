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

import com.prototipo.dentik.aplicacion.casosuso.entrada.IRolUseCase;
import com.prototipo.dentik.presentacion.dto.request.RolRequestDTO;
import com.prototipo.dentik.presentacion.dto.response.RolResponseDTO;
import com.prototipo.dentik.presentacion.mapeadores.IRolDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RolController {

	private final IRolUseCase rolUseCase;
	private final IRolDtoMapper mapper;

	public RolController(IRolUseCase rolUseCase, IRolDtoMapper mapper) {
		this.rolUseCase = rolUseCase;
		this.mapper = mapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RolResponseDTO guardar(@Valid @RequestBody RolRequestDTO rolRequestDTO) {
		return mapper.toResponseDto(rolUseCase.guardar(mapper.toDomain(rolRequestDTO)));
	}

	@GetMapping
	public List<RolResponseDTO> listarTodo() {
		return rolUseCase.listarTodos().stream().map(mapper::toResponseDto).toList();
	}

	@GetMapping("/{id}")
	public RolResponseDTO buscarPorId(@PathVariable("id") int idRol) {
		return mapper.toResponseDto(rolUseCase.buscarPorId(idRol));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") int idRol) {
		rolUseCase.eliminar(idRol);
		return ResponseEntity.noContent().build();
	}

}
