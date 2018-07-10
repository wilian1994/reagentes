package br.com.reagentes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reagentes.models.Reagente;
import br.com.reagentes.models.Status;
import br.com.reagentes.repository.ReagenteRepository;

@Service
public class ReagenteService {

	@Autowired 
	ReagenteRepository repository;
	
	public Iterable<Reagente> findAll(){
		return repository.findAll();
	}
	
	public void save(Reagente reagente) {
		repository.save(reagente);
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public Optional<Reagente> findOne(Long id) {
		return repository.findById(id);
	}
	
	public Iterable<Reagente> findByStatus(Status status){
		return repository.findByStatus(status);
	}

}