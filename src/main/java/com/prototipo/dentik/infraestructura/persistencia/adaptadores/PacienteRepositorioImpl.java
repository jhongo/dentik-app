package com.prototipo.dentik.infraestructura.persistencia.adaptadores;

import java.util.List;
import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.Paciente;
import com.prototipo.dentik.dominio.repositorios.IPacienteRepositorio;
import com.prototipo.dentik.infraestructura.persistencia.jpa.PacienteEntity;
import com.prototipo.dentik.infraestructura.persistencia.mapeadores.IPacienteJpaMapper;
import com.prototipo.dentik.infraestructura.repositorios.IPacienteJpaRepositorio;

public class PacienteRepositorioImpl implements IPacienteRepositorio {

	private final IPacienteJpaRepositorio jpaRepositorio;
	private final IPacienteJpaMapper entityMapper;

	public PacienteRepositorioImpl(IPacienteJpaRepositorio jpaRepositorio, IPacienteJpaMapper entityMapper) {

		this.jpaRepositorio = jpaRepositorio;
		this.entityMapper = entityMapper;
	}

	@Override
	public Paciente guardar(Paciente nuevoPaciente) {

		PacienteEntity entity = entityMapper.toEntity(nuevoPaciente);
		PacienteEntity guardado = jpaRepositorio.save(entity);

		return entityMapper.toDomain(guardado);
	}

	@Override
	public Optional<Paciente> buscarPorId(int idPaciente) {

		return jpaRepositorio.findById(idPaciente).map(entityMapper::toDomain);
	}

	@Override
	public List<Paciente> listarTodos() {

		return jpaRepositorio.findAll().stream().map(entityMapper::toDomain).toList();
	}

	@Override
	public void eliminar(int idPaciente) {

		// Eliminacion fisica (no se debe hacer)
		jpaRepositorio.deleteById(idPaciente);
	}

}
