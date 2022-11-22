package com.rmf.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rmf.entity.Libro;
import com.rmf.services.ILibroService;
import com.rmf.services.ISocioService;

@Controller
@SessionAttributes("libro")
public class CatalogoController {

	@Autowired
	ILibroService libroService;
	
	@Autowired
	ISocioService socioService;
	
	
	
/////////////////////////// CATALOGO INICIO
///////////////////////////	

	@GetMapping("/catalogo")
	public String prestamos(Model model) {
		
		//titulos
		model.addAttribute("titulo", "Catálogo | library.app");
		model.addAttribute("h2", "Crear nuevo libro");
		model.addAttribute("h2lista", "Catálogo de libros");
		
		
		//List
		model.addAttribute("listaLibros", libroService.lista());
		
		return "catalogo";
	}
	
	
	
/////////////////////////// REGISTRO Y EDICIÓN NUEVO LIBRO
///////////////////////////
	
// REGISTRO	
	
	@GetMapping("/nuevo-libro")
	public String catalogo(Model model) {
		
		//titulos
		model.addAttribute("titulo", "Catálogo | library.app");
		model.addAttribute("h2", "Crear nuevo libro");
		model.addAttribute("h2lista", "Catálogo de libros");
		
		//form
		model.addAttribute("libro", new Libro());
		
		//List
		model.addAttribute("listaLibros", libroService.lista());
		
		return "catalogo";
	}

// EDICION
	
	@GetMapping("/editar/libro/{referencia}")
	public String crearLibro(@PathVariable(value = "referencia") Integer referencia, Model model, RedirectAttributes redirect) {		
		
		//titulos
		model.addAttribute("titulo", "Catálogo | library.app");
		model.addAttribute("h2", "Editar libro en base de datos");
		model.addAttribute("h2lista", "Catálogo de libros");
				
		Libro libro = libroService.encontrarPorId(referencia);
		
		//Form
		model.addAttribute("libro", libro);
		
		//List
		model.addAttribute("listaLibros", libroService.lista());
		
		//status.setComplete();
		
		return "catalogo";
	}
	
// POST REGISTRO Y EDICIÓN	
	
	@PostMapping("/nuevo-libro")
	public String guardarLibro(@Valid Libro libro, BindingResult result, Model model,
			RedirectAttributes redirect, @RequestParam(name ="file", required=false) MultipartFile fotoPortada,
			SessionStatus status){
		
		//Errors
		if (result.hasErrors()) {
			model.addAttribute("fallo", "OUCH! Algo ha ido mal, comprueba los errores");		
			return "catalogo";
		}
		
		//FotoPortada Libro
		if (!fotoPortada.isEmpty()) {
			
			try {	
				
				libro.setFotoPortada(fotoPortada);	
				
			} catch (IOException e) {	
					
				e.printStackTrace();
				
			}
			
		} 																
						
		// Mesaje POPUP si edita o crea socio
		String mensaje = (libro.getReferencia() != null) ? "Ole Ole y Ole! Libro editado correctamente en la base de datos." : "Vamoooos! Otro libro creado con exito en la base de datos.";
		redirect.addFlashAttribute("success", mensaje);
		
		libro = libroService.guardar(libro);
		
		status.setComplete();
		
		return "redirect:/catalogo";		
	}
	

/////////////////////////// EXPURGAR
///////////////////////////
	
	@GetMapping("/libro/expurgar/{referencia}")
	public String cambioExpurgar(@PathVariable(value = "referencia") Integer refencia, RedirectAttributes redirect, Model model) {
		
		Libro libro = libroService.encontrarPorId(refencia);
		
		if(libro.getExpurgado() == true) {
			libro.setExpurgado(false);
			
			//Mensaje
			redirect.addFlashAttribute("success", "Libro activado correctamente... ¡A JUGAR!");
			
		}
		else {
			libro.setExpurgado(true);
			//Mensaje
			redirect.addFlashAttribute("warning", "Ojo! El libro ha sido marcado como expurgado. Ahora debes activarlo para poder prestarlo.");
		}
		
		libroService.guardar(libro);
		
		return "redirect:/catalogo";	
	}
	

}
