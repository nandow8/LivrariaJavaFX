package com.livraria.listarTabelas;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class ListarAlugueisLivro {

	private SimpleLongProperty id;
	private SimpleStringProperty codigoLivro;
	private SimpleStringProperty tituloLivro;
	private SimpleStringProperty nomeAluno;
	private SimpleStringProperty raAluno;
	private SimpleStringProperty disponivel;
	
	public ListarAlugueisLivro(long id, String codigoLivro, String tituloLivro,
			String nomeAluno, String raAluno, String disponivel) {
		super();
		
		this.id = new SimpleLongProperty(id);
		this.codigoLivro = new SimpleStringProperty(codigoLivro);
		this.tituloLivro = new SimpleStringProperty(tituloLivro);
		this.nomeAluno = new SimpleStringProperty(nomeAluno);
		this.raAluno = new SimpleStringProperty(raAluno);
		this.disponivel = new SimpleStringProperty(disponivel);
	}
	
	public long getId(){
		return id.get();
	}
	
	public String getCodigoLivro(){
		return codigoLivro.get();
	}

	public String getTituloLivro() {
		return tituloLivro.get();
	}
 

	public String getNomeAluno() {
		return nomeAluno.get();
	}

	 
	public String getRaAluno() {
		return raAluno.get();
	}

 

	public String getDisponivel() {
		return disponivel.get();
	}

 
	
	
	
}
