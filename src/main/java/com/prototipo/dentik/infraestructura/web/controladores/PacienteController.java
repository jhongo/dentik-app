package com.prototipo.dentik.infraestructura.web.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IPacienteUseCase;

@Controller("pacienteViewController")
@RequestMapping("/pacientes")
public class PacienteController {

    private final IPacienteUseCase pacienteUseCase;

    public PacienteController(IPacienteUseCase pacienteUseCase) {
        this.pacienteUseCase = pacienteUseCase;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pacientes", pacienteUseCase.listarTodos());
        model.addAttribute("pageTitle", "Pacientes");
        return "pacientes/lista";
    }
}
