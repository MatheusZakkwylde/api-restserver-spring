package br.com.project.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.server.domain.model.Client;
import br.com.project.server.repository.ClientRepository;

@Service
public class ClientService {
	
    @Autowired private ClientRepository repository;
    
    public boolean createOrUpdate(Client object) {
    	return (repository.saveAndFlush(object) != null)? true:false;
    }
    
    public List<Client> read(){
    	return repository.findAll();
    }
    
    public boolean delete(long id) {
    	if(repository.existsById(id)) {
    		repository.delete(repository.getOne(id));
    		return true;
    	}
    	return false;
    }
    
    public boolean elementExist(long id) {
    	return repository.existsById(id);
    }
}
