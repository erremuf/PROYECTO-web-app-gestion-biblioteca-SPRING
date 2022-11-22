package com.rmf.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rmf.entity.Libro;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Integer>{
	
	@Query("SELECT l FROM com.rmf.entity.Libro l WHERE l.expurgado=false ORDER By l.fechaAdquisicion DESC")
	public Page<Libro> findFirst2ByFechaAdquisicionOrderDesc(Pageable pageable, boolean expurgado);
	
	@Query("SELECT l FROM com.rmf.entity.Libro l JOIN l.socio")
	public List<Libro> getAllTieneSocios();

}
