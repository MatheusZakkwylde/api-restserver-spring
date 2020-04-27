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
	/**
	 * Método post
	 * @param clientDTO - Objeto dto a ser processado.
	 * @param result - Retorna os erros dos models.
	 * @return se tudo ok retorna o status 201, se não retorna o erro 422 com os erros.
	 */
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create (@Valid @RequestBody ClientDTO clientDTO,BindingResult result) {
		if(result.hasErrors()) return error(result.getFieldErrors());
		clientDAO.createOrUpdate(clientDTO.createObjet(0));
		return null;
	}
	
	/**
	 * Método put
	 * @param id - indentificador do objeto.
	 * @param clientDTO - Objeto a ser tratado.
	 * @param result -validação.
	 * @return Se tudo certo retorna o status 201, se não o 422 com os erros de validação
	 */
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody ClientDTO clientDTO,BindingResult result ){
		
		if(result.hasErrors())return error(result.getFieldErrors());
        if(clientDAO.elementExist(id)) clientDAO.createOrUpdate(clientDTO.createObjet(id));
      
		return null;
	}
  
   /**
   * Método Get
   * @return retorna a lista de objetos cadastrados ou uma lista vazia
   */
   @GetMapping("/read")
   public ResponseEntity<List<Client>> read () {
	   return  ResponseEntity.ok(clientDAO.read()); 
   }
	
   /**
    * Método Delete
    * @param id - indentificador
    * @return retorna o status 401 se tudo der certo, se não 500
    */
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Void> delete(@PathVariable("id") long id){
	   return (clientDAO.delete(id))? ResponseEntity.noContent().build(): ResponseEntity.notFound().build();
   }	
   
   /**
    * Método de verificação de erros
    * @param list a lista de erros
    * @return
    */
   public ResponseEntity<?> error(List<FieldError> list){
	   Map<String,String> erros = new HashMap<>();
	   
	   for(FieldError error : list) {
		  erros.put(error.getField(),error.getDefaultMessage());
	   }
	   
	   return ResponseEntity.unprocessableEntity().body(erros);
   }
}
