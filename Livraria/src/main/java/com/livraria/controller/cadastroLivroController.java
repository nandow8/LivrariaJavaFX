package com.livraria.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.livraria.dao.LivroDAO;
import com.livraria.entity.Livro;
import com.livraria.util.Alertas;
import com.livraria.util.HibernateUtil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class cadastroLivroController implements Initializable{

	@FXML private JFXTextField txt_codigo;
	@FXML private JFXTextField txt_autor;
	@FXML private JFXTextField txt_distribuidora;
	@FXML private JFXTextField txt_tituloLivro;
	@FXML private JFXButton btn_salvar;
	@FXML private JFXButton btn_sair;
	@FXML AnchorPane anchorPane;
	
	Livro livro = new Livro();
	LivroDAO livroDAO = new LivroDAO();
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML public void salvar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			livro = new Livro();
			LivroDAO livroDAO = new LivroDAO();
			livro.setCodigodoLivro(txt_codigo.getText());
			livro.setAutor(txt_autor.getText());
			livro.setDistribuidora(txt_distribuidora.getText());
			livro.setTituloLivro(txt_tituloLivro.getText());
			livro.setDisponivel("Disponivel");

			// Verifica se o livro ja está cadastrado
			
			String li = txt_codigo.getText();
			Query buscaLivro = sessao.createQuery("from Livro where codigodoLivro = :codigo");
			buscaLivro.setParameter("codigo", li);
			String livr = (String) buscaLivro.uniqueResult();
			
			if (livro.getAutor().equals(livr)){

			} else {
				livroDAO.salvarR(livro);
				Alertas.alertaSucesso("Salvo com sucesso");
			}
		} catch (Exception e) {
			Alertas.alertaErro("Livro já cadastrado no sistema");
		}
	}
	
	@FXML public void sair(){
		Stage stage = (Stage) anchorPane.getScene().getWindow();
		stage.close();
	}
	
	
	
//	public void alertaTextField(){
//		TextInputDialog dialog = new TextInputDialog("Texto dialog");
//		dialog.setTitle("TITULO");
//		dialog.setHeaderText("HEADER TEXT");
//		dialog.setContentText("digite seu nome");
//		
//		Optional<String> textoDigitado = dialog.showAndWait();
//		if(textoDigitado.isPresent()){
//			System.out.println("Digitou : " + textoDigitado.get());
//		}
//	}
	
//	public void alertaCabuloZo(){
//		Alert alert = new Alert(AlertType.CONFIRMATION);
//		alert.setTitle("Titulo");
//		alert.setHeaderText("HEADER TEXT");
//		alert.setContentText("CONTENT TEXT");
//		
//		ButtonType botao1 = new ButtonType("Botao 1");
//		ButtonType botao2 = new ButtonType("Botao 2");
//		ButtonType botao3 = new ButtonType("Botao 3");
//		ButtonType botao4 = new ButtonType("Botao 3");
//		
//		alert.getButtonTypes().setAll(botao1,botao2,botao3,botao4);
//		
//		Optional<ButtonType> botaoClicado = alert.showAndWait();
//		if(botaoClicado.get() == botao1){
//			System.out.println("clicou botao 1111");
//		} else if(botaoClicado.get() == botao2){
//			System.out.println("clicou botao 2222");
//		} else if(botaoClicado.get() == botao3){
//			System.out.println("clicou botao 3333");
//		} else if(botaoClicado.get() == botao4){
//			System.out.println("clicou botao 4444");
//		}
//		
//	}

}
