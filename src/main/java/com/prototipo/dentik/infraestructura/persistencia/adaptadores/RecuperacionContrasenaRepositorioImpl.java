package com.prototipo.dentik.infraestructura.persistencia.adaptadores;

import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.RecuperacionContrasena;
import com.prototipo.dentik.dominio.repositorios.IRecuperacionContrasenaRepositorio;
import com.prototipo.dentik.infraestructura.persistencia.jpa.RecuperacionContrasenaEntity;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IRecuperacionContrasenaJpaMapper;
import com.prototipo.dentik.infraestructura.repositorios.IRecuperacionContrasenaJpaRepositorio;

public class RecuperacionContrasenaRepositorioImpl implements IRecuperacionContrasenaRepositorio {

	private final IRecuperacionContrasenaJpaRepositorio jpaRepositorio;
	private final IRecuperacionContrasenaJpaMapper entityMapper;

	public RecuperacionContrasenaRepositorioImpl(IRecuperacionContrasenaJpaRepositorio jpaRepositorio,
			IRecuperacionContrasenaJpaMapper entityMapper) {
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}

	@Override
	public RecuperacionContrasena guardar(RecuperacionContrasena nuevaRecuperacion) {
		RecuperacionContrasenaEntity entity = entityMapper.toEntity(nuevaRecuperacion);
		RecuperacionContrasenaEntity guardado = jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);
	}

	@Override
	public Optional<RecuperacionContrasena> buscarPorToken(String token) {
		return jpaRepositorio.findByToken(token).map(entityMapper::toDomain);
	}

}
