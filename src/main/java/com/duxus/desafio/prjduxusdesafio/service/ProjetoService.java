package com.duxus.desafio.prjduxusdesafio.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.duxus.desafio.prjduxusdesafio.model.Projeto;
import com.duxus.desafio.prjduxusdesafio.repository.ProjetoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProjetoService {

	@Autowired
	ProjetoRepository repo;
	
	@Value("${path.diretorio.download}")
	private String pathDiretorioDownload;

	public Projeto insert(Projeto obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Projeto update(Projeto obj, Integer id) {
		Projeto newObj = repo.findOne(id);
		if(newObj == null) {
			return null;
		}
		updateData(newObj, obj);

		return repo.save(newObj);
	}

	public boolean delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			return false;
		}
		return true;
	}

	public List<Projeto> findAll() {
		return repo.findAll();
	}

	public Projeto find(Integer id) {
		Projeto obj = repo.findOne(id);
		if (obj == null) {
			return null;
		}

		return obj;
	}
	

    public HttpEntity<byte[]> fazerDownload() throws IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	List<Projeto> listProjetos = repo.findAll();
    	FileWriter arq = new FileWriter(pathDiretorioDownload);
    	
        PrintWriter escrever = new PrintWriter(arq);
    	String string = mapper.writeValueAsString(listProjetos);
        escrever.print(mapper.writeValueAsString(listProjetos));
        arq.close();
        
    	 byte[] arquivo = Files.readAllBytes( Paths.get(pathDiretorioDownload) );

         HttpHeaders httpHeaders = new HttpHeaders();
         httpHeaders.add("Content-Disposition", "attachment;filename=\"list-json.json\"");
         HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);

         return entity;
       
    }

	public void updateData(Projeto newObj, Projeto obj) {
		newObj.setApelido(obj.getApelido());
		newObj.setDescricao(obj.getDescricao());
		newObj.setNomeCliente(obj.getNomeCliente());
		newObj.setScreenshot(obj.getScreenshot());
		newObj.setStatus(obj.getStatus());
	}

}
