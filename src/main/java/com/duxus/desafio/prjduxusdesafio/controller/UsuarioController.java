package com.duxus.desafio.prjduxusdesafio.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.duxus.desafio.prjduxusdesafio.model.Usuario;
import com.duxus.desafio.prjduxusdesafio.service.UsuarioService;

@Controller
@RequestMapping(value="/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService service;
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Usuario obj){
		Usuario user = service.insert(obj);
		if(user != null) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			
			return ResponseEntity.created(uri).build();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
