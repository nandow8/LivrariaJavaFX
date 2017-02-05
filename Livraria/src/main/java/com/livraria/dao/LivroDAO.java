package com.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.jandex.Main;

import com.livraria.entity.Livro;
import com.livraria.util.HibernateUtil;

public class LivroDAO extends GenericDAO<Livro>{

	
	
	public Livro buscarLivroPorAutor(Livro livro){
		Session sesssao = HibernateUtil.getSessionFactory().openSession();

		Livro li = livro;
		
		Query buscarLivro = sesssao.createQuery("from Livro where autor = :autorLivro");
		
		buscarLivro.setParameter("autorLivro", li);
		 
		return livro;
	}
	
	public void salvarR(Livro livro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
			sessao.beginTransaction();
			sessao.save(livro);
			sessao.getTransaction().commit();
			sessao.close();
		 
	}
	
	@SuppressWarnings("unchecked")
	public List<Livro> listarLivros(){
		List<Livro> livroList = new ArrayList<Livro>();
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		sessao.beginTransaction();
		livroList = sessao.createQuery("from Livro").list();
		sessao.close();
		return livroList;
	}
 
	@SuppressWarnings("unchecked")
	public List<Livro> pegaListaPessoas(long id, String mesmoNomeQueColocouNoIdNoBancoDeDados) {

		List<Livro> list = new ArrayList<Livro>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		try {

			list = s.createCriteria(Livro.class).add(Restrictions.eq(mesmoNomeQueColocouNoIdNoBancoDeDados, id)).list();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}
		return list;
	}
	
	public Livro getPessoaPorNome(String pessoa, String mesmoNomeQueColocouNoNOMENoBancoDeDados) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Livro pe = null;

		try {

			pe = (Livro) s.createCriteria(Livro.class).add(Restrictions.eq(mesmoNomeQueColocouNoNOMENoBancoDeDados,pessoa)).uniqueResult();

		} catch (Exception e) {

			e.getMessage();

		} finally {
			s.close();

		}

		if (pe == null) {
			System.out.println("Retornou null");
			return null;
		} else {

			System.out.println("Retornou o usuario");
			return pe;
		}

	}
	public  void editar(Livro livro, String codigoLivro) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.getTransaction().begin();
		
		Query busca = s.createQuery("from Livro where codigodoLivro = :codigo");
		busca.setParameter("codigo", codigoLivro);
		
		Livro li = (Livro) busca.uniqueResult();
		s.refresh(li);
		
		if(livro.getDisponivel() != null){
			li.setDisponivel(livro.getDisponivel());
		}
		
		s.getTransaction().commit();
		
		s.close();
	}
 
	
}
