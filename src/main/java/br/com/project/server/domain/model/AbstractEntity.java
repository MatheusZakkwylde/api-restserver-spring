package br.com.project.server.domain.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@MappedSuperclass
@EqualsAndHashCode
public class AbstractEntity implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private long id;
}
