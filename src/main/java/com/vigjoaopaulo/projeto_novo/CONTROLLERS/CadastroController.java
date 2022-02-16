package com.vigjoaopaulo.projeto_novo.CONTROLLERS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vigjoaopaulo.projeto_novo.MODEL.Cadastro;
import com.vigjoaopaulo.projeto_novo.REPOSITORYS.CadastroRepository;

@RestController
@RequestMapping("/cad")
public class CadastroController {

	@Autowired
	private CadastroRepository repository;

	public boolean verificarPorId(Long id) {
		return repository.existsById(id);
	}

//	public boolean verica(String apelido) {
//		return repository.existsByApelido(apelido);
//	}

	@GetMapping("/listar")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Cadastro> getCad() {
		return repository.findAll();
	}


	@PostMapping("/inserir")
	@ResponseStatus(HttpStatus.CREATED)
	public String add(@RequestBody Cadastro cad) {			
		//por default todos usuarios serão usuario
		
		repository.save(cad);

		return "Inserido com sucesso id: " + cad.getApelido();	
		
	}

	@GetMapping("/buscarId/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public String getId(@PathVariable Long id) {

		if (verificarPorId(id) != true) {
			return "Registro não encontrado...";
	 	} else {

			Cadastro p = repository.findById(id).get();

			return " "+ p;
		}
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public String deletePerfil(@PathVariable Long id) {

		if (verificarPorId(id) != true) {
			return "Registro não encontrado";
		} else {
			repository.deleteById(id);
			return "Deletado com sucesso id: " + id;
		}

	}

	@PutMapping("/atualizar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public String edit(@PathVariable Long id, @RequestBody Cadastro cad) {

		if (verificarPorId(id) != true) {
			return "Registro não encontrado";
		} else {
			Cadastro newObj = repository.findById(id).get();
			newObj.setApelido(cad.getApelido());
			newObj.setEmail(cad.getEmail());
			newObj.setNome(cad.getNome());
			newObj.setSenha(cad.getSenha());
			
			repository.save(newObj);
			return "Atualizado com sucesso id: " + id;
		}
	}

}
