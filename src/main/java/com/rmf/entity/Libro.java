package com.rmf.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "LIBROS")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer referencia;
	
	@Column(name = "TITULO")
	@NotEmpty
	private String titulo;
	
	@Column(name = "AUTOR")
	@NotEmpty
	private String autor;
	
	@Column(name = "SINOPSIS")
	private String sinopsis;
	
	@Lob
	@Column(name = "PORTADA")
	private String fotoPortada;
	
	@NotNull
	@Column(name = "FECHA_ADQUISICION")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaAdquisicion;
	
	@Column(name = "EXPURGADO")
	private Boolean expurgado;
	
	
	// RELACIÓN BBDD
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOCIO_ID")
	private Socio socio;

	
	// CONSTRUCTORES
	
	public Libro() {
	}
	
	public Libro(@NotEmpty String titulo, @NotEmpty String autor, String sinopsis,
			String fotoPortada, @NotEmpty Date fechaAdquisicion, Boolean expurgado, Socio socio) {
		this.titulo = titulo;
		this.autor = autor;
		this.sinopsis = sinopsis;
		this.fotoPortada = fotoPortada;
		this.fechaAdquisicion = fechaAdquisicion;
		this.expurgado = expurgado;
		this.socio = socio;
	}
	

	// GETTERS & SETTERS
	
	public Integer getReferencia() {
		return referencia;
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getFotoPortada() {
		return fotoPortada;
	}

	public void setFotoPortada(String fotoPortada) {
		this.fotoPortada = fotoPortada;
	}

	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public Boolean getExpurgado() {
		return expurgado;
	}

	public void setExpurgado(Boolean expurgado) {
		this.expurgado = expurgado;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	

		//SETTER PARA LAS IMAGENES EN BBDD 
	
		public void setFotoPortada(MultipartFile file) throws IOException {  
			this.fotoPortada = Base64.getEncoder().encodeToString(file.getBytes()); 
		}
		
		public void setFotoPortada(File file) throws IOException
		{
			FileInputStream fl = new FileInputStream(file);

			byte[] arr = new byte[(int) file.length()];

			fl.read(arr);

			fl.close();
			this.fotoPortada = Base64.getEncoder().encodeToString(arr);
		}
	
	

}
