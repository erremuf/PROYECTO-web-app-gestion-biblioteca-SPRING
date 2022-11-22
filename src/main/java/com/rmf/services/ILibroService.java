package com.rmf.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmf.entity.Libro;

@Service
public interface ILibroService {
	
	public Libro guardar(Libro libro);
	
	public List<Libro> lista();
	
	public Libro editar(Libro libro);
	
	public Libro encontrarPorId(Integer referencia);
	
	public Libro prestar(Libro libro);
	
	public Libro devolver(Libro libro);
	
	public List<Libro> librosPrestados();

}
