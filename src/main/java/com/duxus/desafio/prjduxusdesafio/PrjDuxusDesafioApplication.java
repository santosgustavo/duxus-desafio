package com.duxus.desafio.prjduxusdesafio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.duxus.desafio.prjduxusdesafio.model.Projeto;
import com.duxus.desafio.prjduxusdesafio.service.ProjetoService;

@SpringBootApplication
public class PrjDuxusDesafioApplication extends SpringBootServletInitializer{
	
	@Autowired
	ProjetoService ser;
	
	public static void main(String[] args) {
		SpringApplication.run(PrjDuxusDesafioApplication.class, args);
		
	}
	
	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
		return builder.sources(PrjDuxusDesafioApplication.class);
	}

}
