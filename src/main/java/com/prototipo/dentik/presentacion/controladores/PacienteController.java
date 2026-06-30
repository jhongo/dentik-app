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

import com.prototipo.dentik.aplicacion.casosuso.entrada.IPacienteUseCase;
import com.prototipo.dentik.presentacion.dto.request.PacienteRequestDTO;
import com.prototipo.dentik.presentacion.dto.response.PacienteResponseDTO;
import com.prototipo.dentik.presentacion.mapeadores.IPacienteDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	private final IPacienteUseCase pacienteUseCase;
	private final IPacienteDtoMapper mapper;

	public PacienteController(IPacienteUseCase pacienteUseCase, IPacienteDtoMapper mapper) {
		this.pacienteUseCase = pacienteUseCase;
		this.mapper = mapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PacienteResponseDTO guardar(@Valid @RequestBody PacienteRequestDTO pacienteRequestDTO) {
		return mapper.toResponseDto(pacienteUseCase.guardar(mapper.toDomain(pacienteRequestDTO)));
	}

	@GetMapping
	public List<PacienteResponseDTO> listarTodo() {
		return pacienteUseCase.listarTodos().stream().map(mapper::toResponseDto).toList();
	}

	@DeleteMapping("/{idPaciente}")
	public ResponseEntity<Void> eliminar(@PathVariable int idPaciente) {
		pacienteUseCase.eliminar(idPaciente);
		return ResponseEntity.noContent().build();
	}

}
