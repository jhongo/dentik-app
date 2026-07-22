package com.prototipo.dentik.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prototipo.dentik.infraestructura.persistencia.jpa.SesionUsuarioEntity;

public interface ISesionUsuarioJpaRepositorio extends JpaRepository<SesionUsuarioEntity, Integer> {

	List<SesionUsuarioEntity> findByIdUsuario(int idUsuario);

}
