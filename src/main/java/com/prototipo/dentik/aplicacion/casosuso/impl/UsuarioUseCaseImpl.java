package com.prototipo.dentik.aplicacion.casosuso.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.prototipo.dentik.aplicacion.casosuso.entrada.IUsuarioUseCase;
import com.prototipo.dentik.dominio.entidades.Rol;
import com.prototipo.dentik.dominio.entidades.Usuario;
import com.prototipo.dentik.dominio.repositorios.IRolRepositorio;
import com.prototipo.dentik.dominio.repositorios.IUsuarioRepositorio;

public class UsuarioUseCaseImpl implements IUsuarioUseCase {

	private final IUsuarioRepositorio repositorio;
	private final IRolRepositorio rolRepositorio;

	public UsuarioUseCaseImpl(IUsuarioRepositorio repositorio, IRolRepositorio rolRepositorio) {

		this.repositorio = repositorio;
		this.rolRepositorio = rolRepositorio;
	}

	@Override
	public Usuario guardar(Usuario nuevoUsuario) {

		// Regla de negocio: un usuario nuevo se registra como 'Activo' por defecto
		if (nuevoUsuario.getEstado() == null || nuevoUsuario.getEstado().isBlank()) {
			nuevoUsuario.setEstado("Activo");
		}

		return repositorio.guardar(nuevoUsuario);
	}

	@Override
	public Usuario buscarPorId(int idUsuario) {

		return repositorio.buscarPorId(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	}

	@Override
	public List<Usuario> listarTodos() {

		return repositorio.listarTodos();
	}

	@Override
	public void eliminar(int idUsuario) {

		repositorio.eliminar(idUsuario);
	}

	@Override
	public Usuario registrar(Usuario nuevoUsuario, String nombreRol) {

		// HU-SGCE-0001 Escenario 2: rechazar documento o correo ya registrados
		if (repositorio.existePorDocumento(nuevoUsuario.getDocumento())) {
			throw new RuntimeException("El documento ya se encuentra registrado");
		}
		if (repositorio.existePorCorreo(nuevoUsuario.getCorreo())) {
			throw new RuntimeException("El correo ya se encuentra registrado");
		}

		Usuario guardado = guardar(nuevoUsuario);

		// Asigna el rol solicitado (por defecto 'Paciente' en el registro publico).
		String rolSolicitado = (nombreRol == null || nombreRol.isBlank()) ? "Paciente" : nombreRol;
		Rol rol = rolRepositorio.buscarPorNombre(rolSolicitado)
				.orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rolSolicitado));

		return repositorio.asignarRol(guardado.getIdUsuario(), rol.getIdRol());
	}

	@Override
	public Usuario actualizar(Usuario usuario) {

		Usuario existente = buscarPorId(usuario.getIdUsuario());
		if (usuario.getEstado() == null || usuario.getEstado().isBlank()) {
			usuario.setEstado(existente.getEstado());
		}
		return repositorio.guardar(usuario);
	}

	@Override
	public Usuario buscarPorCorreo(String correo) {

		return repositorio.buscarPorCorreo(correo)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	}

	@Override
	public List<Usuario> buscarPorRol(String nombreRol) {

		return repositorio.buscarPorRol(nombreRol);
	}

	@Override
	public Usuario asignarRol(int idUsuario, int idRol) {

		return repositorio.asignarRol(idUsuario, idRol);
	}

	@Override
	public Usuario autenticar(String correo, String contrasena) {

		// HU-SGCE-0002: valida credenciales
		Usuario usuario = repositorio.buscarPorCorreo(correo)
				.orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

		if (!usuario.getContrasena().equals(contrasena)) {
			throw new RuntimeException("Credenciales incorrectas");
		}
		if (!"Activo".equalsIgnoreCase(usuario.getEstado())) {
			throw new RuntimeException("La cuenta no se encuentra activa");
		}

		usuario.setUltimaSesion(LocalDateTime.now());
		return repositorio.guardar(usuario);
	}

}
