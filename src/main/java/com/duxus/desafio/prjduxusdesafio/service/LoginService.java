package com.duxus.desafio.prjduxusdesafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duxus.desafio.prjduxusdesafio.model.Usuario;
import com.duxus.desafio.prjduxusdesafio.repository.UsuarioRepository;

@Service
public class LoginService {
	
	@Autowired
	UsuarioRepository repo;
	
	public Usuario autenticar(String usuario, String senha) {

		return repo.checkUsuario(usuario, senha);
	}
	
}
