package com.duxus.desafio.prjduxusdesafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.duxus.desafio.prjduxusdesafio.model.Usuario;
import com.duxus.desafio.prjduxusdesafio.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService service;
	
	@RequestMapping(value="/autenticar", method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestHeader String usuario,@RequestHeader String senha){
		Usuario user = service.autenticar(usuario, senha);
		
		if(user != null) {
			return ResponseEntity.ok().build();
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
}
