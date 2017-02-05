package com.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livraria.entity.Alugueis;
import com.livraria.entity.Aluno;
import com.livraria.entity.Livro;
import com.livraria.util.HibernateUtil;


public class AlugueisDAO {

	public void salvar(Alugueis alugueis) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(alugueis);
		s.getTransaction().commit();
		s.close();
	}

	public List<Alugueis> lista() {
		Session s = HibernateUtil.getSessionFactory().openSession();

		Query busca = s.createQuery("from Alugueis");
		List<Alugueis> lista = busca.list();

		s.close();
		return lista;
	}

	public Alugueis buscar(String codigoDoLivro) {
		Session s = HibernateUtil.getSessionFactory().openSession();

		Query busca = s.createQuery("from Alugueis where codigoLivro = :codigo");
		busca.setParameter("codigo", codigoDoLivro);
		Alugueis aluguel = (Alugueis) busca.uniqueResult();

		s.close();

		return aluguel;
	}

	public List<Alugueis> buscarEListar(String codigoDoLivro) {
		Session s = HibernateUtil.getSessionFactory().openSession();

		Query busca = s.createQuery("from Alugueis where codigoLivro = :codigo");
		busca.setParameter("codigo", codigoDoLivro);
		List<Alugueis> aluguel = busca.list();

		s.close();

		return aluguel;
	}

	public void editarAlugueis(Alugueis alugueis, String codigoLivro) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.getTransaction().begin();

		Query busca = s.createQuery("from Alugueis where codigoLivro = :codigo");
		busca.setParameter("codigo", codigoLivro);

		Alugueis alu = (Alugueis) busca.uniqueResult();
		s.refresh(alu);

		if (alugueis.getDisponivel() != null) {
			alu.setDisponivel(alugueis.getDisponivel());
		}

		s.getTransaction().commit();

		s.close();
	}
 

	public void removeCliente(long id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Alugueis c = (Alugueis) s.load(Alugueis.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	} 

	public static void main(String[] args) {

		AlugueisDAO a = new AlugueisDAO();
		
		
		a.removeCliente(3);
	}

}
