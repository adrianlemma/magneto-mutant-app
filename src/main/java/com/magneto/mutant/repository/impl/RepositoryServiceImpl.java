package com.magneto.mutant.repository.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magneto.mutant.entity.Registro;
import com.magneto.mutant.repository.RepositoryService;

@Service
@Transactional
public class RepositoryServiceImpl {

	@Autowired
	RepositoryService repositoryService;
	
    public Registro save(Registro registro){
    	return repositoryService.save(registro);
    }
    
    @Transactional(readOnly = true)
    public List<Registro> findAll(){
        return repositoryService.findAll();
    }
    
    
    @Transactional(readOnly = true)
    public boolean yaRegistrado(String dna){
        return repositoryService.existsByDna(dna);
    }
    
    @Transactional(readOnly = true)
    public int countMutants(){
    	List<Registro> list = repositoryService.findByType("mutant");
    	return list.size();
    }
    
    @Transactional(readOnly = true)
    public int countHumans(){
    	List<Registro> list = repositoryService.findByType("no mutant");
    	return list.size();
    }
    
}
