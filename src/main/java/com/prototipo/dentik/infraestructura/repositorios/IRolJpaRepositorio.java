package com.prototipo.dentik.infraestructura.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prototipo.dentik.infraestructura.persistencia.jpa.RolEntity;

public interface IRolJpaRepositorio extends JpaRepository<RolEntity, Integer> {

	Optional<RolEntity> findByNombre(String nombre);

}
