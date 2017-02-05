 package com.livraria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.livraria.entity.Aluno;
import com.livraria.util.HibernateUtil;

public class AlunoDAO {
	
	public void save(Aluno aluno){
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aluno);
		s.getTransaction().commit();
		s.close();
	}
	
	@SuppressWarnings("unchecked")
	public  List<Aluno> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		sessao.beginTransaction();
		
		Query buscaAlunos = sessao.createQuery("from Aluno");
		List<Aluno> alunoList = buscaAlunos.list();

		sessao.close();
		return alunoList;
	}
	
	
	public List<Aluno> buscarAlunoEncontrado(String text){
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		Query buscaAluno = s.createQuery("from Aluno where ra = :ra" );
		buscaAluno.setParameter("ra", text);
		
		List<Aluno> alunolist = buscaAluno.list();
		
		s.close();
		return alunolist;
	}
 
    public Aluno buscarAluno(String text){
    	Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
    	
    	Query buscaAluno = s.createQuery("from Aluno where ra = :ra");
    	
    	buscaAluno.setParameter("ra", text);
    	Aluno aluno = (Aluno) buscaAluno.uniqueResult();
    	s.close();
		
    	return aluno;
    }
    
}
