package com.prototipo.dentik.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prototipo.dentik.infraestructura.persistencia.jpa.PacienteEntity;

public interface IPacienteJpaRepositorio extends JpaRepository<PacienteEntity, Integer> {

}
