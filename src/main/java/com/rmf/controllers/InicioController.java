package com.rmf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rmf.entity.Libro;
import com.rmf.repository.ILibroRepository;
import com.rmf.services.ILibroService;

@Controller
public class InicioController {
	
	@Autowired
	ILibroRepository libroRepo;
	
	@Autowired
	ILibroService libroService;
	
		
	@GetMapping({"/", "/inicio"})
	public String inicio(@RequestParam(name = "page", defaultValue = "0") int page, Model model, Libro libro) {
		
		Pageable pageRequest = PageRequest.of(page, 2);

		Page<Libro> novedades = libroRepo.findFirst2ByFechaAdquisicionOrderDesc(pageRequest, false);
		
		model.addAttribute("titulo", "Inicio | library.app");
		model.addAttribute("h2", "Últimas novedades");
		model.addAttribute("novedades", novedades);
		
		return "inicio";
	}
	
}
