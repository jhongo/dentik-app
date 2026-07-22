package com.prototipo.dentik.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IAutenticacionUseCase;
import com.prototipo.dentik.aplicacion.casosuso.entrada.IPerfilProfesionalUseCase;
import com.prototipo.dentik.aplicacion.casosuso.entrada.IRolUseCase;
import com.prototipo.dentik.aplicacion.casosuso.entrada.ISesionUsuarioUseCase;
import com.prototipo.dentik.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.prototipo.dentik.aplicacion.casosuso.impl.AutenticacionUseCaseImpl;
import com.prototipo.dentik.aplicacion.casosuso.impl.PerfilProfesionalUseCaseImpl;
import com.prototipo.dentik.aplicacion.casosuso.impl.RolUseCaseImpl;
import com.prototipo.dentik.aplicacion.casosuso.impl.SesionUsuarioUseCaseImpl;
import com.prototipo.dentik.aplicacion.casosuso.impl.UsuarioUseCaseImpl;
import com.prototipo.dentik.dominio.repositorios.IPerfilProfesionalRepositorio;
import com.prototipo.dentik.dominio.repositorios.IRecuperacionContrasenaRepositorio;
import com.prototipo.dentik.dominio.repositorios.IRolRepositorio;
import com.prototipo.dentik.dominio.repositorios.ISesionUsuarioRepositorio;
import com.prototipo.dentik.dominio.repositorios.IUsuarioRepositorio;
import com.prototipo.dentik.infraestructura.persistencia.adaptadores.PerfilProfesionalRepositorioImpl;
import com.prototipo.dentik.infraestructura.persistencia.adaptadores.RecuperacionContrasenaRepositorioImpl;
import com.prototipo.dentik.infraestructura.persistencia.adaptadores.RolRepositorioImpl;
import com.prototipo.dentik.infraestructura.persistencia.adaptadores.SesionUsuarioRepositorioImpl;
import com.prototipo.dentik.infraestructura.persistencia.adaptadores.UsuarioRepositorioImpl;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IPerfilProfesionalJpaMapper;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IRecuperacionContrasenaJpaMapper;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IRolJpaMapper;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.ISesionUsuarioJpaMapper;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IUsuarioJpaMapper;
import com.prototipo.dentik.infraestructura.repositorios.IPerfilProfesionalJpaRepositorio;
import com.prototipo.dentik.infraestructura.repositorios.IRecuperacionContrasenaJpaRepositorio;
import com.prototipo.dentik.infraestructura.repositorios.IRolJpaRepositorio;
import com.prototipo.dentik.infraestructura.repositorios.ISesionUsuarioJpaRepositorio;
import com.prototipo.dentik.infraestructura.repositorios.IUsuarioJpaRepositorio;

@Configuration
public class DentikConfig {

	// ============================ Roles ============================

	@Bean
	IRolRepositorio rolRepositorio(IRolJpaRepositorio jpaRepositorio, IRolJpaMapper mapper) {
		return new RolRepositorioImpl(jpaRepositorio, mapper);
	}

	@Bean
	IRolUseCase rolUseCase(IRolRepositorio repoUseCase) {
		return new RolUseCaseImpl(repoUseCase);
	}

	// ============================ Usuarios ============================

	@Bean
	IUsuarioRepositorio usuarioRepositorio(IUsuarioJpaRepositorio jpaRepositorio,
			IRolJpaRepositorio rolJpaRepositorio, IUsuarioJpaMapper mapper) {
		return new UsuarioRepositorioImpl(jpaRepositorio, rolJpaRepositorio, mapper);
	}

	@Bean
	IUsuarioUseCase usuarioUseCase(IUsuarioRepositorio repoUseCase, IRolRepositorio rolRepositorio) {
		return new UsuarioUseCaseImpl(repoUseCase, rolRepositorio);
	}

	// ===================== Perfiles Profesionales =====================

	@Bean
	IPerfilProfesionalRepositorio perfilProfesionalRepositorio(IPerfilProfesionalJpaRepositorio jpaRepositorio,
			IPerfilProfesionalJpaMapper mapper) {
		return new PerfilProfesionalRepositorioImpl(jpaRepositorio, mapper);
	}

	@Bean
	IPerfilProfesionalUseCase perfilProfesionalUseCase(IPerfilProfesionalRepositorio repoUseCase) {
		return new PerfilProfesionalUseCaseImpl(repoUseCase);
	}

	// ========================= Sesiones =========================

	@Bean
	ISesionUsuarioRepositorio sesionUsuarioRepositorio(ISesionUsuarioJpaRepositorio jpaRepositorio,
			ISesionUsuarioJpaMapper mapper) {
		return new SesionUsuarioRepositorioImpl(jpaRepositorio, mapper);
	}

	@Bean
	ISesionUsuarioUseCase sesionUsuarioUseCase(ISesionUsuarioRepositorio repoUseCase) {
		return new SesionUsuarioUseCaseImpl(repoUseCase);
	}

	// ==================== Recuperacion de contrasena ====================

	@Bean
	IRecuperacionContrasenaRepositorio recuperacionContrasenaRepositorio(
			IRecuperacionContrasenaJpaRepositorio jpaRepositorio, IRecuperacionContrasenaJpaMapper mapper) {
		return new RecuperacionContrasenaRepositorioImpl(jpaRepositorio, mapper);
	}

	// ========================= Autenticacion =========================

	@Bean
	IAutenticacionUseCase autenticacionUseCase(IUsuarioUseCase usuarioUseCase,
			ISesionUsuarioUseCase sesionUseCase, IRecuperacionContrasenaRepositorio recuperacionRepositorio) {
		return new AutenticacionUseCaseImpl(usuarioUseCase, sesionUseCase, recuperacionRepositorio);
	}

}
