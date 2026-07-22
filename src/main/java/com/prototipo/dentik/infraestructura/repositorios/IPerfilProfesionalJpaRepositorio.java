package com.prototipo.dentik.infraestructura.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prototipo.dentik.infraestructura.persistencia.jpa.PerfilProfesionalEntity;

public interface IPerfilProfesionalJpaRepositorio extends JpaRepository<PerfilProfesionalEntity, Integer> {

	Optional<PerfilProfesionalEntity> findByIdUsuario(int idUsuario);

}
