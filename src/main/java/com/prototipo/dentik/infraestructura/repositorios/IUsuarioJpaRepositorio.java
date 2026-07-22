package com.prototipo.dentik.infraestructura.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prototipo.dentik.infraestructura.persistencia.jpa.UsuarioEntity;

public interface IUsuarioJpaRepositorio extends JpaRepository<UsuarioEntity, Integer> {

	Optional<UsuarioEntity> findByCorreo(String correo);

	Optional<UsuarioEntity> findByDocumento(String documento);

	boolean existsByCorreo(String correo);

	boolean existsByDocumento(String documento);

	@Query("select u from UsuarioEntity u join u.roles r where r.nombre = ?1")
	List<UsuarioEntity> buscarPorRol(String nombreRol);

}
