package com.duxus.desafio.prjduxusdesafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.duxus.desafio.prjduxusdesafio.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query(value="SELECT * FROM usuario u WHERE u.login = :login and u.senha = :senha", nativeQuery = true)
	Usuario checkUsuario(@Param("login") String usuario, @Param("senha") String senha);
	
}
