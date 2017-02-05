package com.livraria.listarTabelas;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class ListarLivros {

	private SimpleStringProperty disponivel;
	private SimpleStringProperty autor;
	private SimpleStringProperty codigoLivro;
	private SimpleStringProperty tituloLivro;
	private SimpleStringProperty distribuidora;

	public ListarLivros(String disponivel, String autor, String codigoLivro, String tituloLivro,
			String distribuidora) {
		this.disponivel = new SimpleStringProperty(disponivel);
		this.autor = new SimpleStringProperty(autor);
		this.codigoLivro = new SimpleStringProperty(codigoLivro);
		this.tituloLivro = new SimpleStringProperty(tituloLivro);
		this.distribuidora = new SimpleStringProperty(distribuidora);
	}

	public String getDisponivel() {
		return disponivel.get();
	}

	public String getAutor() {
		return autor.get();
	}

	public String getCodigoLivro() {
		return codigoLivro.get();
	}

	public String getTituloLivro() {
		return tituloLivro.get();
	}

	public String getDistribuidora() {
		return distribuidora.get();
	}

}
