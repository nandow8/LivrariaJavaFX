package com.livraria.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.livraria.listarTabelas.ListarLivros;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(length = 50)
	private String autor;
	
	@Column(length = 50)
	private String codigodoLivro;
	
	@Column(length = 50)
	private String tituloLivro;
	
	@Column(length = 50)
	private String distribuidora;
	
	private String disponivel;
	
	public Livro() {
		super();
	}

	
	public void Livro(ListarLivros listarLivros){
		this.autor = listarLivros.getAutor();
		this.codigodoLivro = listarLivros.getCodigoLivro();
		this.tituloLivro = listarLivros.getTituloLivro();
		this.distribuidora = listarLivros.getDistribuidora();
		this.disponivel = listarLivros.getDisponivel();
	}

	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getCodigodoLivro() {
		return codigodoLivro;
	}


	public void setCodigodoLivro(String codigodoLivro) {
		this.codigodoLivro = codigodoLivro;
	}


	public String getTituloLivro() {
		return tituloLivro;
	}


	public void setTituloLivro(String tituloLivro) {
		this.tituloLivro = tituloLivro;
	}


	public String getDistribuidora() {
		return distribuidora;
	}


	public void setDistribuidora(String distribuidora) {
		this.distribuidora = distribuidora;
	}


	public String getDisponivel() {
		return disponivel;
	}


	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}


	@Override
	public String toString() {
		return "Livro [id=" + id + ", autor=" + autor + ", codigodoLivro=" + codigodoLivro + ", tituloLivro="
				+ tituloLivro + ", distribuidora=" + distribuidora + ", disponivel=" + disponivel + "]";
	}
	
	
	
}
