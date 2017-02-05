package com.livraria.listarTabelas;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class ListarAlunos {

	private SimpleLongProperty id;
	private SimpleStringProperty nome;
	private SimpleStringProperty ra;
	
	public ListarAlunos(long id, String nome, String ra) {
		super();
		this.id = new SimpleLongProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.ra = new SimpleStringProperty(ra);
	}
	
	public Long getId(){
		return id.get();
	}
	
	public String getNome(){
		return nome.get();
	}
	
	public String getRa(){
		return ra.get();
	}
	
}
