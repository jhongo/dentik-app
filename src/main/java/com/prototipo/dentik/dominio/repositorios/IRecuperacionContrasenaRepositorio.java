package com.prototipo.dentik.dominio.repositorios;

import java.util.Optional;

import com.prototipo.dentik.dominio.entidades.RecuperacionContrasena;

public interface IRecuperacionContrasenaRepositorio {

	RecuperacionContrasena guardar(RecuperacionContrasena nuevaRecuperacion);
	Optional<RecuperacionContrasena> buscarPorToken(String token);

}
