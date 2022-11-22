package com.rmf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rmf.entity.Socio;
import com.rmf.services.ISocioService;

@Controller
@SessionAttributes("socio")
public class SocioController {
	
	
	@Autowired
	ISocioService socioService;

	
/////////////////////////// SOCIOS INICIO
///////////////////////////

	@GetMapping("/socios")
	public String listaSocio(Model model) {
		
		//titulos
		model.addAttribute("titulo", "Socios | library.app");
		model.addAttribute("h2lista", "Listado de socios");
		
		//List
		model.addAttribute("listaSocios", socioService.lista());
		
		return "socios";
	}
	

/////////////////////////// REGISTRO Y EDICIÓN NUEVO SOCIO
///////////////////////////
	
// REGISTRO
	
	@GetMapping("/nuevo-socio")
	public String crearSocio(Model model) {
		
		//titulos
		model.addAttribute("titulo", "Socios | library.app");
		model.addAttribute("h2", "Crear nuevo socio");
		model.addAttribute("h2lista", "Listado de socios");
		
		//Form
		model.addAttribute("socio", new Socio());
		
		//List
		model.addAttribute("listaSocios", socioService.lista());
				
		return "socios";
	}
	
// EDICION
	
	@GetMapping("/editar/socio/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirect) {
		
		//titulos
		model.addAttribute("titulo", "Socios | library.app");
		model.addAttribute("h2", "Editar socio en base de datos");
		model.addAttribute("h2lista", "Listado de socios");
		
		Socio socio = socioService.encontrarPorId(id);

		//Form
		model.addAttribute("socio", socio);	
				
		//List	
		model.addAttribute("listaSocios", socioService.lista());
		
		return "socios";
	}
	
// POST REGISTRO Y EDICIÓN
	
	@PostMapping("/nuevo-socio")
	public String guardarSocio(@Valid Socio socio, BindingResult result, Model model,
			RedirectAttributes redirect, SessionStatus status){
		
		//Errors
		if (result.hasErrors()) {
			model.addAttribute("fallo", "OUCH! Algo ha ido mal, comprueba los errores");		
			return "socios";
		}
		
		// Mesaje POPUP si edita o crea socio
		String mensaje = (socio.getId() != null) ? "Ole Ole y Ole! Socio editado correctamente en la base de datos." : "Vamoooos! Otro socio creado pa'l negocio.";
		redirect.addFlashAttribute("success", mensaje);

		socio = socioService.guardar(socio);
		
		status.setComplete();
	
		return "redirect:/socios";
	}
	

/////////////////////////// ELIMINAR SOCIO
///////////////////////////
	
	@GetMapping("/eliminar/socio/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id, RedirectAttributes redirect, Model model) {
		
		Socio socio = socioService.encontrarPorId(id);
		List<Integer> sociosConLibro = socioService.sociosConLibro();
		
		if(sociosConLibro.contains(socio.getId())) {
			String mensaje = "No se que ha hecho pero no podemos eliminarlo. AÚN TIENE LIBROS!!!"; 
			redirect.addFlashAttribute("error", mensaje);		
			
			return"redirect:/socios";
						
		} else {
			socioService.borrar(socio);
			String mensaje = "Veeengaaaastaluego, Socio!!!"; 
			redirect.addFlashAttribute("success", mensaje);
		}
			
		return "redirect:/socios";
	}
	

}
