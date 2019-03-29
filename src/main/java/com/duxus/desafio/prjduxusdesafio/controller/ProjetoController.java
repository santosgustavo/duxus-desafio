package com.duxus.desafio.prjduxusdesafio.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.duxus.desafio.prjduxusdesafio.model.Projeto;
import com.duxus.desafio.prjduxusdesafio.service.ProjetoService;


@Controller
@RequestMapping("/projetos")
public class ProjetoController {

	@Autowired
	ProjetoService service;
	
	@GetMapping
	public ModelAndView listaProjetos(){
		ModelAndView mv = new ModelAndView("listaProjeto");
		Iterable<Projeto> listaProjetos = service.findAll();
		mv.addObject("listaProjeto", listaProjetos);
		
		return mv;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Projeto obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Projeto obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Projeto obj , @PathVariable Integer id){
		obj = service.update(obj,id);
		if(obj == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		if(!service.delete(id))
		return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/findAll",method=RequestMethod.GET)
	public ResponseEntity<List<Projeto>> findAll(){
		List<Projeto> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/cadastrarProjeto",method=RequestMethod.POST)
	public String cadastrarPortifolio(Projeto projeto) {
		if(projeto.getId() != null) {
			service.update(projeto, projeto.getId());
		}else {
			service.insert(projeto);
		}

		return "redirect:/projetos/novo";
	}
	
	@RequestMapping(value="/deletar/{id}")
	public String deletarPortifolio(@PathVariable Integer id) {
		service.delete(id);
		
		return "redirect:/projetos";
	}
	
	@RequestMapping(value="/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable Integer id) {
		Projeto projeto = service.find(id);
		ModelAndView mv = new ModelAndView("formProjeto");
		mv.addObject(projeto);
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Projeto projeto) {
		ModelAndView mv = new ModelAndView("formProjeto");
		
		return mv;
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadFile() {
    	
        try {
			return service.fazerDownload();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }

}
