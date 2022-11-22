package com.rmf.controllers;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class PrestamosController {

	@Autowired
	ILibroService libroService;
	
	@Autowired
	ISocioService socioService;
	
	
	
/////////////////////////// PRÉSTAMOS INICIO
///////////////////////////	

	@GetMapping("/prestamos")
	public String prestamos(Model model) {
		
		//titulos
		model.addAttribute("titulo", "Préstamos | library.app");
		model.addAttribute("h2", "Prestar libro");
		model.addAttribute("h2lista", "Lista libros prestados");
		
		//List
		List<Libro> librosPrestados = libroService.librosPrestados();
		model.addAttribute("listaPrestados", librosPrestados);
		
		return "prestamos";
	}
	
	
	
/////////////////////////// PRESTAR LIBRO
///////////////////////////
	
// FORM
	
	@GetMapping("/prestar/libro/{referencia}")
	public String catalogo(@PathVariable(value = "referencia") Integer referencia, Model model, RedirectAttributes redirect) {
		
		//titulos
		model.addAttribute("titulo", "Préstamos | library.app");
		model.addAttribute("h2", "Prestar libro");
		model.addAttribute("h2lista", "Lista libros prestados");
		
		Libro libro = libroService.encontrarPorId(referencia);
		
		//form
		model.addAttribute("libro", libro);
		model.addAttribute("listaSocios", socioService.lista());
		
		//List
		List<Libro> librosPrestados = libroService.librosPrestados();
		model.addAttribute("listaPrestados", librosPrestados);
		
		return "prestamos";
	}

	
// POST	
	
	@PostMapping("/prestar/libro")
	public String prestarLibro(Libro libro, RedirectAttributes redirect, @RequestParam(name ="file", required=false) MultipartFile fotoPortada, SessionStatus status){
		
	
		//Socios Libro
		if(libro.getSocio() == null) {			
			String mensaje = "Es necesario elegir un socio para prestar un libro, lumbreras!";
			redirect.addFlashAttribute("error", mensaje);	
			status.setComplete();
			return "redirect:/catalogo";	
		}
		
		//FotoPortada Libro
		if (!fotoPortada.isEmpty()) {					
			try {						
				libro.setFotoPortada(fotoPortada);							
			} catch (IOException e) {								
				e.printStackTrace();						
			}					
		} 
			
		// Mesaje POPUP
		String mensaje = "Puesyassstaría!! El libro esta colocado.";
		redirect.addFlashAttribute("success", mensaje);
		
		libro = libroService.prestar(libro);
		
		status.setComplete();
		
		return "redirect:/catalogo";		
	}
	
	

///////////////////////////// DEVOLVER
/////////////////////////////
	
	@GetMapping("/libro/devolver/{referencia}")
	public String devolverLibro(@PathVariable(value = "referencia") Integer refencia, RedirectAttributes redirect, Model model, SessionStatus status) {
		
		Libro libro = libroService.encontrarPorId(refencia);
		
		if(libro.getSocio() != null) {
			libro = libroService.devolver(libro);		
			//Mensaje
			redirect.addFlashAttribute("success", "El libro vuelve a estar en nuestro poder... ¡A JUGAR!");
			
		}
	
		libroService.guardar(libro);
		
		status.setComplete();
		
		return "redirect:/catalogo";	
	}
	

}
