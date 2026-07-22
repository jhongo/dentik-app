package com.prototipo.dentik.infraestructura.web.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IUsuarioUseCase;

@Controller
@RequestMapping("/pacientes")
public class PacienteViewController {

	private final IUsuarioUseCase usuarioUseCase;

	public PacienteViewController(IUsuarioUseCase usuarioUseCase) {
		this.usuarioUseCase = usuarioUseCase;
	}

	@GetMapping
	public String listar(Model model) {
		// Los pacientes son usuarios con el rol 'Paciente'.
		model.addAttribute("pacientes", usuarioUseCase.buscarPorRol("Paciente"));
		model.addAttribute("pageTitle", "Pacientes");
		return "pacientes/lista";
	}
}
