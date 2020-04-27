package br.com.project.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.server.domain.model.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {}
