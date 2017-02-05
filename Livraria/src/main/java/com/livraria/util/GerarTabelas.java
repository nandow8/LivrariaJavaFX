package com.livraria.util;

import org.hibernate.Session;

public class GerarTabelas {

	public static void main(String[] args) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		sessao.close();
		HibernateUtil.getSessionFactory().close();
	}
	
}
