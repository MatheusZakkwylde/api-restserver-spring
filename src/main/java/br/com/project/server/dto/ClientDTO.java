package br.com.project.server.dto;

import javax.validation.constraints.NotBlank;

import br.com.project.server.domain.model.Client;
import lombok.Getter;
import lombok.Setter;

public class ClientDTO {
	 @NotBlank(message="O nome não pode ser nulo")
	 @Getter @Setter private String name; 
	 
	 public Client createObjet(long id) {
		 return new Client(id,this.name);
	 }
}
