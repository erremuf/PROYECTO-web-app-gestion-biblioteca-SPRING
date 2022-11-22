package com.rmf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmf.entity.Libro;
import com.rmf.entity.Socio;
import com.rmf.repository.ILibroRepository;
import com.rmf.repository.ISocioRepository;

@Service
public class LibroServiceImpl implements ILibroService{
	
	@Autowired
	ILibroRepository libroRepo;
	
	@Autowired
	ISocioRepository socioRepo;


	@Override
	public Libro guardar(Libro libro) {
		libro = libroRepo.save(libro);
		return libro;
	}

	@Override
	public Libro editar(Libro libro) {
		libro = libroRepo.save(libro);
		return libro;
	}

	@Override
	public Libro encontrarPorId(Integer referencia) {
		return libroRepo.findById(referencia).get();
	}

	@Override
	public Libro prestar(Libro libro) {
		Socio socio = socioRepo.getReferenceById(libro.getSocio().getId());
		libro.setSocio(socio);
		libro = libroRepo.save(libro);
		return libro;
	}

	@Override
	public List<Libro> lista() {	
		return libroRepo.findAll();
	}

	@Override
	public Libro devolver(Libro libro) {
		libro.setSocio(null);
		libro = libroRepo.save(libro);
		return libro;
	}

	@Override
	public List<Libro> librosPrestados() {
		List<Libro> librosPrestados = libroRepo.getAllTieneSocios();
		return librosPrestados;
	}

}
