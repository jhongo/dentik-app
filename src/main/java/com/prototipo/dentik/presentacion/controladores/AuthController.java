package com.prototipo.dentik.presentacion.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IAutenticacionUseCase;
import com.prototipo.dentik.dominio.entidades.Usuario;
import com.prototipo.dentik.presentacion.dto.request.LoginRequestDTO;
import com.prototipo.dentik.presentacion.dto.request.RecuperarContrasenaRequestDTO;
import com.prototipo.dentik.presentacion.dto.request.RestablecerContrasenaRequestDTO;
import com.prototipo.dentik.presentacion.dto.response.RecuperacionResponseDTO;
import com.prototipo.dentik.presentacion.dto.response.UsuarioResponseDTO;
import com.prototipo.dentik.presentacion.mapeadores.IUsuarioDtoMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final IAutenticacionUseCase autenticacionUseCase;
	private final IUsuarioDtoMapper usuarioMapper;

	public AuthController(IAutenticacionUseCase autenticacionUseCase, IUsuarioDtoMapper usuarioMapper) {
		this.autenticacionUseCase = autenticacionUseCase;
		this.usuarioMapper = usuarioMapper;
	}

	// HU-SGCE-0002: iniciar sesion.
	@PostMapping("/login")
	public UsuarioResponseDTO login(@Valid @RequestBody LoginRequestDTO dto, HttpServletRequest request) {
		Usuario usuario = autenticacionUseCase.login(dto.getCorreo(), dto.getContrasena(),
				request.getRemoteAddr(), request.getHeader("User-Agent"));
		return usuarioMapper.toResponseDto(usuario);
	}

	// HU-SGCE-0003: solicitar recuperacion de contrasena.
	@PostMapping("/recuperar")
	public RecuperacionResponseDTO recuperar(@Valid @RequestBody RecuperarContrasenaRequestDTO dto) {
		String token = autenticacionUseCase.solicitarRecuperacion(dto.getCorreo());
		return new RecuperacionResponseDTO(token, "Se genero un enlace temporal para restablecer la contrasena");
	}

	// HU-SGCE-0003: restablecer contrasena con el token.
	@PostMapping("/restablecer")
	public ResponseEntity<Void> restablecer(@Valid @RequestBody RestablecerContrasenaRequestDTO dto) {
		autenticacionUseCase.restablecerContrasena(dto.getToken(), dto.getNuevaContrasena());
		return ResponseEntity.noContent().build();
	}

}
