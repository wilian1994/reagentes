package br.com.reagentes.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.reagentes.models.Reagente;
import br.com.reagentes.models.Status;

public interface ReagenteRepository extends  CrudRepository <Reagente, Long> {
	
	Iterable<Reagente> findByStatus(Status status);

}	
