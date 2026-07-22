package com.prototipo.dentik.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.PerfilProfesional;
import com.prototipo.dentik.dominio.repositorios.IPerfilProfesionalRepositorio;
import com.prototipo.dentik.infraestructura.persistencia.jpa.PerfilProfesionalEntity;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IPerfilProfesionalJpaMapper;
import com.prototipo.dentik.infraestructura.repositorios.IPerfilProfesionalJpaRepositorio;

public class PerfilProfesionalRepositorioImpl implements IPerfilProfesionalRepositorio {

	private final IPerfilProfesionalJpaRepositorio jpaRepositorio;
	private final IPerfilProfesionalJpaMapper entityMapper;

	public PerfilProfesionalRepositorioImpl(IPerfilProfesionalJpaRepositorio jpaRepositorio,
			IPerfilProfesionalJpaMapper entityMapper) {
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}

	@Override
	public PerfilProfesional guardar(PerfilProfesional nuevoPerfil) {
		PerfilProfesionalEntity entity = entityMapper.toEntity(nuevoPerfil);
		PerfilProfesionalEntity guardado = jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);
	}

	@Override
	public Optional<PerfilProfesional> buscarPorId(int idPerfil) {
		return jpaRepositorio.findById(idPerfil).map(entityMapper::toDomain);
	}

	@Override
	public Optional<PerfilProfesional> buscarPorUsuario(int idUsuario) {
		return jpaRepositorio.findByIdUsuario(idUsuario).map(entityMapper::toDomain);
	}

	@Override
	public List<PerfilProfesional> listarTodos() {
		return jpaRepositorio.findAll().stream().map(entityMapper::toDomain).toList();
	}

	@Override
	public void eliminar(int idPerfil) {
		jpaRepositorio.deleteById(idPerfil);
	}

}
