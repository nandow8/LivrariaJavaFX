package com.livraria.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.livraria.dao.LivroDAO;
import com.livraria.entity.Livro;
import com.livraria.listarTabelas.ListarLivros;
import com.livraria.util.Alertas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXButton;

public class TabelaLivrosController implements Initializable{

	@FXML private TableView<ListarLivros> tbv_tabelaPai;
	@FXML private TableColumn<ListarLivros, String> tbc_codigoLivro;
	@FXML private TableColumn<ListarLivros, String> tbc_autor;
	@FXML private TableColumn<ListarLivros, String> tbc_titulo;
	@FXML private TableColumn<ListarLivros, String> tbc_distribuidora;
	@FXML private TableColumn<ListarLivros, Boolean> tbc_disponivel;
	@FXML TextField txt_busca;
	@FXML JFXButton btn_busca;
	@FXML TextField txt_nome;
	
	LivroDAO livroDAO = new LivroDAO();
	private List<Livro> livroList = livroDAO.listar();
	
	private ObservableList<ListarLivros> livroOBSList = FXCollections.observableArrayList();
	
	public void listarTabela(){
		if(!livroOBSList.isEmpty()){   //  SE NAO ESTIVER VASIO
			livroOBSList.clear();
			System.out.println("Limpou tabela");
		}
		for (Livro li : livroList) {
			ListarLivros listarLivros = new ListarLivros(li.getDisponivel(),li.getAutor(),li.getCodigodoLivro(),li.getTituloLivro(),li.getDistribuidora());
			livroOBSList.addAll(listarLivros);
		}
		
		tbc_codigoLivro.setCellValueFactory(new PropertyValueFactory<ListarLivros, String>("codigoLivro"));
		tbc_autor.setCellValueFactory(new PropertyValueFactory<ListarLivros, String>("autor"));
		tbc_titulo.setCellValueFactory(new PropertyValueFactory<ListarLivros, String>("tituloLivro"));
		tbc_distribuidora.setCellValueFactory(new PropertyValueFactory<ListarLivros, String>("distribuidora"));
		tbc_disponivel.setCellValueFactory(new PropertyValueFactory<ListarLivros, Boolean>("disponivel"));
		
		tbv_tabelaPai.setItems(livroOBSList);
		
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		listarTabela();
	}

	@FXML public void busca() {
		if (Alertas.campoVasio(txt_busca.getText())) {
			Alertas.alertaErro("Digite o nome no campo Busca");
		} else {

			Livro pe = livroDAO.getPessoaPorNome(txt_busca.getText(), "codigodoLivro"); // nome é o campo nome la no banco de dados

			if (pe == null) {
				Alert a2 = new Alert(AlertType.ERROR);
				a2.setTitle("Erro");
				a2.setContentText("Nao existe o cliente informado");
				a2.setHeaderText(null);
				a2.showAndWait();
				txt_busca.clear();
			} else {
				livroList = livroDAO.pegaListaPessoas(pe.getId(), "id"); // id é o campo id la no banco de dados
				listarTabela();
			}
		}
	}

	@FXML public void clicouTabela() {
		ListarLivros listarLivros = tbv_tabelaPai.getSelectionModel().getSelectedItem();
		txt_nome.setText(listarLivros.getAutor());
	}
	
	 
 
}


