package com.livraria.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Alugueis implements Serializable{

 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(length = 30)
	private String codigoLivro;
	
	@Column(length = 30)
	private String tituloLivro;
	
	@Column(length = 30)
	private String autorLivro;
	
	@Column(length = 30)
	private String distribuidoraLivro;
	
	@Column(length = 15)
	private String disponivel;
	
	@Column(length = 50)
	private String nomeAluno;
	
	@Column(length = 20)
	private String raAluno;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(String codigoLivro) {
		this.codigoLivro = codigoLivro;
	}

	public String getTituloLivro() {
		return tituloLivro;
	}

	public void setTituloLivro(String tituloLivro) {
		this.tituloLivro = tituloLivro;
	}

	public String getAutorLivro() {
		return autorLivro;
	}

	public void setAutorLivro(String autorLivro) {
		this.autorLivro = autorLivro;
	}

	public String getDistribuidoraLivro() {
		return distribuidoraLivro;
	}

	public void setDistribuidoraLivro(String distribuidoraLivro) {
		this.distribuidoraLivro = distribuidoraLivro;
	}

	public String getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public String getRaAluno() {
		return raAluno;
	}

	public void setRaAluno(String raAluno) {
		this.raAluno = raAluno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autorLivro == null) ? 0 : autorLivro.hashCode());
		result = prime * result + ((codigoLivro == null) ? 0 : codigoLivro.hashCode());
		result = prime * result + ((disponivel == null) ? 0 : disponivel.hashCode());
		result = prime * result + ((distribuidoraLivro == null) ? 0 : distribuidoraLivro.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nomeAluno == null) ? 0 : nomeAluno.hashCode());
		result = prime * result + ((raAluno == null) ? 0 : raAluno.hashCode());
		result = prime * result + ((tituloLivro == null) ? 0 : tituloLivro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alugueis other = (Alugueis) obj;
		if (autorLivro == null) {
			if (other.autorLivro != null)
				return false;
		} else if (!autorLivro.equals(other.autorLivro))
			return false;
		if (codigoLivro == null) {
			if (other.codigoLivro != null)
				return false;
		} else if (!codigoLivro.equals(other.codigoLivro))
			return false;
		if (disponivel == null) {
			if (other.disponivel != null)
				return false;
		} else if (!disponivel.equals(other.disponivel))
			return false;
		if (distribuidoraLivro == null) {
			if (other.distribuidoraLivro != null)
				return false;
		} else if (!distribuidoraLivro.equals(other.distribuidoraLivro))
			return false;
		if (id != other.id)
			return false;
		if (nomeAluno == null) {
			if (other.nomeAluno != null)
				return false;
		} else if (!nomeAluno.equals(other.nomeAluno))
			return false;
		if (raAluno == null) {
			if (other.raAluno != null)
				return false;
		} else if (!raAluno.equals(other.raAluno))
			return false;
		if (tituloLivro == null) {
			if (other.tituloLivro != null)
				return false;
		} else if (!tituloLivro.equals(other.tituloLivro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alugueis [id=" + id + ", codigoLivro=" + codigoLivro + ", tituloLivro=" + tituloLivro + ", autorLivro="
				+ autorLivro + ", distribuidoraLivro=" + distribuidoraLivro + ", disponivel=" + disponivel
				+ ", nomeAluno=" + nomeAluno + ", raAluno=" + raAluno + "]";
	}

	
	
	
}
