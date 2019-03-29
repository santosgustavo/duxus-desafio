package com.duxus.desafio.prjduxusdesafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duxus.desafio.prjduxusdesafio.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer>{

}
