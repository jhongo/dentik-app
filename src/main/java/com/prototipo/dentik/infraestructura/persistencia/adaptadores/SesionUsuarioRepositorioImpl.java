package com.prototipo.dentik.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.SesionUsuario;
import com.prototipo.dentik.dominio.repositorios.ISesionUsuarioRepositorio;
import com.prototipo.dentik.infraestructura.persistencia.jpa.SesionUsuarioEntity;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.ISesionUsuarioJpaMapper;
import com.prototipo.dentik.infraestructura.repositorios.ISesionUsuarioJpaRepositorio;

public class SesionUsuarioRepositorioImpl implements ISesionUsuarioRepositorio {

	private final ISesionUsuarioJpaRepositorio jpaRepositorio;
	private final ISesionUsuarioJpaMapper entityMapper;

	public SesionUsuarioRepositorioImpl(ISesionUsuarioJpaRepositorio jpaRepositorio,
			ISesionUsuarioJpaMapper entityMapper) {
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}

	@Override
	public SesionUsuario guardar(SesionUsuario nuevaSesion) {
		SesionUsuarioEntity entity = entityMapper.toEntity(nuevaSesion);
		SesionUsuarioEntity guardado = jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);
	}

	@Override
	public Optional<SesionUsuario> buscarPorId(int idSesion) {
		return jpaRepositorio.findById(idSesion).map(entityMapper::toDomain);
	}

	@Override
	public List<SesionUsuario> buscarPorUsuario(int idUsuario) {
		return jpaRepositorio.findByIdUsuario(idUsuario).stream().map(entityMapper::toDomain).toList();
	}

	@Override
	public List<SesionUsuario> listarTodos() {
		return jpaRepositorio.findAll().stream().map(entityMapper::toDomain).toList();
	}

}
