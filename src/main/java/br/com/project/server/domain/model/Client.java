package br.com.project.server.domain.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client extends AbstractEntity {
   @NotBlank(message="O nome não pode ser nulo")
   @Getter @Setter private String name;
   
   public Client (long id, String name) {
	   this.name = name;
	   this.setId(id);
   }
}
