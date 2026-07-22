package com.prototipo.dentik.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.Rol;
import com.prototipo.dentik.dominio.repositorios.IRolRepositorio;
import com.prototipo.dentik.infraestructura.persistencia.jpa.RolEntity;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IRolJpaMapper;
import com.prototipo.dentik.infraestructura.repositorios.IRolJpaRepositorio;

public class RolRepositorioImpl implements IRolRepositorio {

	private final IRolJpaRepositorio jpaRepositorio;
	private final IRolJpaMapper entityMapper;

	public RolRepositorioImpl(IRolJpaRepositorio jpaRepositorio, IRolJpaMapper entityMapper) {
		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}

	@Override
	public Rol guardar(Rol nuevoRol) {
		RolEntity entity = entityMapper.toEntity(nuevoRol);
		RolEntity guardado = jpaRepositorio.save(entity);
		return entityMapper.toDomain(guardado);
	}

	@Override
	public Optional<Rol> buscarPorId(int idRol) {
		return jpaRepositorio.findById(idRol).map(entityMapper::toDomain);
	}

	@Override
	public Optional<Rol> buscarPorNombre(String nombre) {
		return jpaRepositorio.findByNombre(nombre).map(entityMapper::toDomain);
	}

	@Override
	public List<Rol> listarTodos() {
		return jpaRepositorio.findAll().stream().map(entityMapper::toDomain).toList();
	}

	@Override
	public void eliminar(int idRol) {
		jpaRepositorio.deleteById(idRol);
	}

}
