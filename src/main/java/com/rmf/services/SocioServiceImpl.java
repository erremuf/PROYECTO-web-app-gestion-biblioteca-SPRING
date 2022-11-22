package com.rmf.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmf.entity.Libro;
import com.rmf.entity.Socio;
import com.rmf.repository.ILibroRepository;
import com.rmf.repository.ISocioRepository;

@Service
public class SocioServiceImpl implements ISocioService{
	
	@Autowired
	ISocioRepository repo;
	
	@Autowired
	ILibroRepository libroRepo;

	
	@Override
	public Socio guardar(Socio socio) {
		socio = repo.save(socio);
		return socio;		
	}

	@Override
	public List<Socio> lista() {

		return repo.findAll();
	}

	@Override
	public Socio editar(Socio socio) {
		
		return repo.save(socio);
	}

	@Override
	public Object borrar(Socio socio) {
		repo.delete(socio);
		return socio;
	}

	@Override
	public Socio encontrarPorId(Integer id) {		
		return repo.findById(id).get();
	}

	@Override
	public List<Integer> sociosConLibro() {
		List<Integer> sociosConLibro = new ArrayList<>();
		
		List<Libro> librosConSocios = libroRepo.getAllTieneSocios();
		for(Libro librosConSocio : librosConSocios) {
			sociosConLibro.add(librosConSocio.getSocio().getId()); 		
		}
		
		return sociosConLibro;
	}

}
