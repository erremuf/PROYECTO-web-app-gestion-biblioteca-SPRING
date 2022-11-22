package com.rmf.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "SOCIOS")
public class Socio {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NOMBRE")
	@NotEmpty
	private String nombre;
	
	@Column(name = "APELLIDOS")
	@NotEmpty
	private String apellidos;

	
	// RELACIÓN BBDD
	
	private	@OneToMany(mappedBy = "socio", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	List<Libro> libros;
	
	
	// CONSTRUCTORES
	
	public Socio() {
	}
	
	public Socio(@NotEmpty String nombre, @NotEmpty String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}


	// GETTERS & SETTERS
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	

}
