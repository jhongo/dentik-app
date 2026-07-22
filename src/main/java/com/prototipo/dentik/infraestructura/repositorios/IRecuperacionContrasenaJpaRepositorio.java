package com.prototipo.dentik.infraestructura.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prototipo.dentik.infraestructura.persistencia.jpa.RecuperacionContrasenaEntity;

public interface IRecuperacionContrasenaJpaRepositorio
		extends JpaRepository<RecuperacionContrasenaEntity, Integer> {

	Optional<RecuperacionContrasenaEntity> findByToken(String token);

}
