package com.livraria.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.livraria.dao.AlunoDAO;
import com.livraria.entity.Aluno;
import com.livraria.util.Alertas;
import com.livraria.util.HibernateUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadastroAlunoController implements Initializable {

	@FXML
	JFXTextField txt_nome;
	@FXML
	JFXTextField txt_ra;
	@FXML
	JFXButton btn_salvar;
	@FXML
	JFXButton btn_sair;
	@FXML AnchorPane anchorPane;
	Aluno aluno = new Aluno();

	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void salvar(ActionEvent evento) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		AlunoDAO alunoDAO = new AlunoDAO();
		try {
			aluno.setNome(txt_nome.getText());
			aluno.setRa(txt_ra.getText());
			
			String ra = txt_ra.getText();
			Query buscaAluno = s.createQuery("from Aluno where ra = :raAluno");
			buscaAluno.setParameter("raAluno", ra);
			String alunoRA = (String) buscaAluno.uniqueResult();
			
			if(!aluno.getNome().equals(alunoRA)){
				alunoDAO.save(aluno);
				Alertas.alertaSucesso("Aluno salvo com sucesso");
			} 
			
		} catch (Exception e) {
		   Alertas.alertaErro("Aluno já cadastrado no sistema");
		}
	}

	@FXML
	public void sair(ActionEvent evento) {
		Stage stage = (Stage) anchorPane.getScene().getWindow();
		stage.close();
	}

}
