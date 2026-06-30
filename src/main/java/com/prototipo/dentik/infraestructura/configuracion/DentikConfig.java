package com.prototipo.dentik.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IPacienteUseCase;
import com.prototipo.dentik.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.prototipo.dentik.aplicacion.casosuso.impl.PacienteUseCaseImpl;
import com.prototipo.dentik.aplicacion.casosuso.impl.UsuarioUseCaseImpl;
import com.prototipo.dentik.dominio.repositorios.IPacienteRepositorio;
import com.prototipo.dentik.dominio.repositorios.IUsuarioRepositorio;
import com.prototipo.dentik.infraestructura.persistencia.adaptadores.PacienteRepositorioImpl;
import com.prototipo.dentik.infraestructura.persistencia.adaptadores.UsuarioRepositorioImpl;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IPacienteJpaMapper;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IUsuarioJpaMapper;
import com.prototipo.dentik.infraestructura.repositorios.IPacienteJpaRepositorio;
import com.prototipo.dentik.infraestructura.repositorios.IUsuarioJpaRepositorio;

@Configuration
public class DentikConfig {

	@Bean
	IPacienteRepositorio pacienteRepositorio(IPacienteJpaRepositorio jpaRepositorio, IPacienteJpaMapper mapper) {
		return new PacienteRepositorioImpl(jpaRepositorio, mapper);
	}

	@Bean
	IPacienteUseCase pacienteUseCase(IPacienteRepositorio repoUseCase) {
		return new PacienteUseCaseImpl(repoUseCase);
	}

	@Bean
	IUsuarioRepositorio usuarioRepositorio(IUsuarioJpaRepositorio jpaRepositorio, IUsuarioJpaMapper mapper) {
		return new UsuarioRepositorioImpl(jpaRepositorio, mapper);
	}

	@Bean
	IUsuarioUseCase usuarioUseCase(IUsuarioRepositorio repoUseCase) {
		return new UsuarioUseCaseImpl(repoUseCase);
	}

}
