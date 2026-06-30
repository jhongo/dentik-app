package com.prototipo.dentik.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.Usuario;
import com.prototipo.dentik.dominio.repositorios.IUsuarioRepositorio;
import com.prototipo.dentik.infraestructura.persistencia.jpa.UsuarioEntity;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IUsuarioJpaMapper;
import com.prototipo.dentik.infraestructura.repositorios.IUsuarioJpaRepositorio;

public class UsuarioRepositorioImpl implements IUsuarioRepositorio {

	private final IUsuarioJpaRepositorio jpaRepositorio;
	private final IUsuarioJpaMapper entityMapper;

	public UsuarioRepositorioImpl(IUsuarioJpaRepositorio jpaRepositorio, IUsuarioJpaMapper entityMapper) {

		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}

	@Override
	public Usuario guardar(Usuario nuevoUsuario) {

		UsuarioEntity entity = entityMapper.toEntity(nuevoUsuario);
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

}
