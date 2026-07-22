package com.prototipo.dentik.presentacion.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.prototipo.dentik.dominio.entidades.Usuario;
import com.prototipo.dentik.presentacion.dto.request.UsuarioRequestDTO;
import com.prototipo.dentik.presentacion.dto.response.UsuarioResponseDTO;
import com.prototipo.dentik.presentacion.mapeadores.IUsuarioDtoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final IUsuarioUseCase usuarioUseCase;
	private final IUsuarioDtoMapper mapper;

	public UsuarioController(IUsuarioUseCase usuarioUseCase, IUsuarioDtoMapper mapper) {
		this.usuarioUseCase = usuarioUseCase;
		this.mapper = mapper;
	}

	// HU-SGCE-0001: registro publico de paciente (rol por defecto 'Paciente').
	@PostMapping("/registro")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioResponseDTO registrar(@Valid @RequestBody UsuarioRequestDTO dto) {
		Usuario usuario = usuarioUseCase.registrar(mapper.toDomain(dto), "Paciente");
		return mapper.toResponseDto(usuario);
	}

	// HU-SGCE-0014: creacion de usuarios por el administrador (rol indicado en el body).
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioResponseDTO guardar(@Valid @RequestBody UsuarioRequestDTO dto) {
		Usuario usuario = usuarioUseCase.registrar(mapper.toDomain(dto), dto.getRol());
		return mapper.toResponseDto(usuario);
	}

	@GetMapping
	public List<UsuarioResponseDTO> listarTodo() {
		return usuarioUseCase.listarTodos().stream().map(mapper::toResponseDto).toList();
	}

	@GetMapping("/{id}")
	public UsuarioResponseDTO buscarPorId(@PathVariable("id") int idUsuario) {
		return mapper.toResponseDto(usuarioUseCase.buscarPorId(idUsuario));
	}

	@GetMapping("/rol/{nombre}")
	public List<UsuarioResponseDTO> buscarPorRol(@PathVariable("nombre") String nombreRol) {
		return usuarioUseCase.buscarPorRol(nombreRol).stream().map(mapper::toResponseDto).toList();
	}

	// HU-SGCE-0014: edicion de datos de usuario.
	@PutMapping("/{id}")
	public UsuarioResponseDTO actualizar(@PathVariable("id") int idUsuario,
			@Valid @RequestBody UsuarioRequestDTO dto) {
		Usuario usuario = mapper.toDomain(dto);
		usuario.setIdUsuario(idUsuario);
		return mapper.toResponseDto(usuarioUseCase.actualizar(usuario));
	}

	// Asigna un rol adicional a un usuario existente.
	@PostMapping("/{id}/roles/{idRol}")
	public UsuarioResponseDTO asignarRol(@PathVariable("id") int idUsuario, @PathVariable("idRol") int idRol) {
		return mapper.toResponseDto(usuarioUseCase.asignarRol(idUsuario, idRol));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") int idUsuario) {
		usuarioUseCase.eliminar(idUsuario);
		return ResponseEntity.noContent().build();
	}

}
