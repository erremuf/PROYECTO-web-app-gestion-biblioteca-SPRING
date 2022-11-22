package com.rmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rmf.entity.Socio;

@Repository
public interface ISocioRepository extends JpaRepository<Socio, Integer>{
	
	

}
