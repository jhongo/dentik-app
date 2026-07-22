package com.prototipo.dentik.infraestructura.web.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IUsuarioUseCase;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

	private final IUsuarioUseCase usuarioUseCase;

	public UsuarioViewController(IUsuarioUseCase usuarioUseCase) {
		this.usuarioUseCase = usuarioUseCase;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("usuarios", usuarioUseCase.listarTodos());
		model.addAttribute("pageTitle", "Usuarios");
		return "usuarios/lista";
	}
}
