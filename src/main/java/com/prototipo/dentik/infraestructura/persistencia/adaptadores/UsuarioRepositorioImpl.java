package com.prototipo.dentik.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.Usuario;
import com.prototipo.dentik.dominio.repositorios.IUsuarioRepositorio;
import com.prototipo.dentik.infraestructura.persistencia.jpa.RolEntity;
import com.prototipo.dentik.infraestructura.persistencia.jpa.UsuarioEntity;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IUsuarioJpaMapper;
import com.prototipo.dentik.infraestructura.repositorios.IRolJpaRepositorio;
import com.prototipo.dentik.infraestructura.repositorios.IUsuarioJpaRepositorio;

public class UsuarioRepositorioImpl implements IUsuarioRepositorio {

	private final IUsuarioJpaRepositorio jpaRepositorio;
	private final IRolJpaRepositorio rolJpaRepositorio;
	private final IUsuarioJpaMapper entityMapper;

	public UsuarioRepositorioImpl(IUsuarioJpaRepositorio jpaRepositorio, IRolJpaRepositorio rolJpaRepositorio,
			IUsuarioJpaMapper entityMapper) {

		this.jpaRepositorio = jpaRepositorio;
		this.rolJpaRepositorio = rolJpaRepositorio;
		this.entityMapper = entityMapper;
	}

	@Override
	public Usuario guardar(Usuario nuevoUsuario) {

		UsuarioEntity entity = entityMapper.toEntity(nuevoUsuario);

		// En una actualizacion se preservan los roles ya asignados (la relacion
		// muchos a muchos se gestiona aparte en asignarRol), evitando que un save
		// con la lista vacia borre las filas de la tabla usuarioroles.
		if (nuevoUsuario.getIdUsuario() != 0) {
			jpaRepositorio.findById(nuevoUsuario.getIdUsuario())
					.ifPresent(existente -> entity.setRoles(existente.getRoles()));
		}

		UsuarioEntity guardado = jpaRepositorio.save(entity);

		return entityMapper.toDomain(guardado);
	}

	@Override
	public Optional<Usuario> buscarPorId(int idUsuario) {

		return jpaRepositorio.findById(idUsuario).map(entityMapper::toDomain);
	}

	@Override
	public List<Usuario> listarTodos() {

		return jpaRepositorio.findAll().stream().map(entityMapper::toDomain).toList();
	}

	@Override
	public void eliminar(int idUsuario) {

		// Eliminacion fisica (no se debe hacer)
		jpaRepositorio.deleteById(idUsuario);
	}

	@Override
	public Optional<Usuario> buscarPorCorreo(String correo) {

		return jpaRepositorio.findByCorreo(correo).map(entityMapper::toDomain);
	}

	@Override
	public Optional<Usuario> buscarPorDocumento(String documento) {

		return jpaRepositorio.findByDocumento(documento).map(entityMapper::toDomain);
	}

	@Override
	public boolean existePorCorreo(String correo) {

		return jpaRepositorio.existsByCorreo(correo);
	}

	@Override
	public boolean existePorDocumento(String documento) {

		return jpaRepositorio.existsByDocumento(documento);
	}

	@Override
	public List<Usuario> buscarPorRol(String nombreRol) {

		return jpaRepositorio.buscarPorRol(nombreRol).stream().map(entityMapper::toDomain).toList();
	}

	@Override
	public Usuario asignarRol(int idUsuario, int idRol) {

		UsuarioEntity usuario = jpaRepositorio.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		RolEntity rol = rolJpaRepositorio.findById(idRol)
				.orElseThrow(() -> new RuntimeException("Rol no encontrado"));

		boolean yaAsignado = usuario.getRoles().stream().anyMatch(r -> r.getIdRol() == rol.getIdRol());
		if (!yaAsignado) {
			usuario.getRoles().add(rol);
		}

		return entityMapper.toDomain(jpaRepositorio.save(usuario));
	}

}
