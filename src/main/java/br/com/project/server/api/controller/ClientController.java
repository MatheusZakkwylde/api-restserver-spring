package br.com.project.server.api.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.server.domain.model.Client;
import br.com.project.server.dto.ClientDTO;
import br.com.project.server.service.ClientService;

@RestController
@RequestMapping("/project")
public class ClientController {

	@Autowired private ClientService clientDAO;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create (@Valid @RequestBody ClientDTO clientDTO,BindingResult result) {

		if(result.hasErrors()) return error(result.getFieldErrors());
		clientDAO.createOrUpdate(clientDTO.createObjet(0));
		return null;
	}
	
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody ClientDTO clientDTO,BindingResult result ){
		if(result.hasErrors()) {return error(result.getFieldErrors());}
		
        if(clientDAO.elementExist(id)) 
           clientDAO.createOrUpdate(clientDTO.createObjet(id));
      
		return null;
	}
		
	@GetMapping("/read")
	public ResponseEntity<List<Client>> read () {
	   return  ResponseEntity.ok(clientDAO.read()); 
	}
	
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Void> delete(@PathVariable("id") long id){
	   return (clientDAO.delete(id))? ResponseEntity.noContent().build(): ResponseEntity.notFound().build();
   }	
   
   public ResponseEntity<?> error(List<FieldError> list){
	   Map<String,String> erros = new HashMap<>();
	   
	   for(FieldError error : list) {
		  erros.put(error.getField(),error.getDefaultMessage());
	   }
	   
	   return ResponseEntity.unprocessableEntity().body(erros);
   }
}
