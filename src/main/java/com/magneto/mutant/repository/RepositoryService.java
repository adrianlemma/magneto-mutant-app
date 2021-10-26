package com.magneto.mutant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magneto.mutant.entity.Registro;

@Repository
public interface RepositoryService extends JpaRepository<Registro, Integer> {
    
    boolean existsByDna(String dna);
    
    List<Registro> findByType(String type);
    
}