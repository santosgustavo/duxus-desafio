package com.duxus.desafio.prjduxusdesafio;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.duxus.desafio.prjduxusdesafio.model.Projeto;
import com.duxus.desafio.prjduxusdesafio.repository.ProjetoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjetoRepositoryTest {
	
	@Autowired
	private ProjetoRepository repo;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void gravarUmProjeto() {
		Projeto projeto = new Projeto(null, "Robo Mobile", "Multi Portal", "API para App", "htt://github.com", "Concluído");
		this.repo.save(projeto);
		Assertions.assertThat(projeto.getId()).isNotNull();
		Assertions.assertThat(projeto.getNomeCliente()).isEqualTo("Multi Portal");
		Assertions.assertThat(projeto.getApelido()).isEqualTo("Robo Mobile");
		Assertions.assertThat(projeto.getDescricao()).isEqualTo("API para App");
		Assertions.assertThat(projeto.getStatus()).isEqualTo("Concluído");
		Assertions.assertThat(projeto.getScreenshot()).isEqualTo("htt://github.com");
	}
	
	@Test
	public void deletaUmProjeto() {
		Projeto projeto = new Projeto(null, "Robo Mobile", "Multi Portal", "API para App", "htt://github.com", "Concluído");
		this.repo.save(projeto);
		repo.delete(projeto);
		
		Assertions.assertThat(repo.findOne(projeto.getId())).isNull();
	}
	
	@Test
	public void atualizaUmProjeto() {
		Projeto projeto = new Projeto(null, "Robo Mobile", "Multi Portal", "API para App", "htt://github.com", "Concluído");
		this.repo.save(projeto);
		projeto.setNomeCliente("Arcade Beauty");
		projeto.setStatus("Desenvolvimento");
		this.repo.save(projeto);
		
		Assertions.assertThat(projeto.getNomeCliente()).isEqualTo("Arcade Beauty");
		Assertions.assertThat(projeto.getStatus()).isEqualTo("Desenvolvimento");
	}
	
	@Test
	public void buscaUmProjeto() {
		Projeto projeto = new Projeto(null, "Robo Mobile", "Multi Portal", "API para App", "htt://github.com", "Concluído");
		Projeto portifolio2 = new Projeto(null, "TCP Log", "Multi Portal", "Formata Log iptables", "htt://github.com", "Desenvolvimento");
		this.repo.save(projeto);
		this.repo.save(portifolio2);
		
		Projeto result = repo.findOne(portifolio2.getId());
		
		Assertions.assertThat(result.getId()).isEqualTo(portifolio2.getId());
		Assertions.assertThat(result.getNomeCliente()).isEqualTo("Multi Portal");
		Assertions.assertThat(result.getApelido()).isEqualTo("TCP Log");
		Assertions.assertThat(result.getDescricao()).isEqualTo("Formata Log iptables");
		Assertions.assertThat(result.getStatus()).isEqualTo("Desenvolvimento");
		Assertions.assertThat(result.getScreenshot()).isEqualTo("htt://github.com");
	}
}
