package com.rmf.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmf.entity.Socio;

@Service
public interface ISocioService {
	
	public Socio guardar(Socio socio);
	
	public List<Socio> lista();
	
	public Socio editar(Socio socio);
	
	public Object borrar(Socio socio);
	
	public Socio encontrarPorId(Integer id);
	
	public List<Integer> sociosConLibro();

}
