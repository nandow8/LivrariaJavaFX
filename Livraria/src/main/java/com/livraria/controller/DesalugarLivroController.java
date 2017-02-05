package com.livraria.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.livraria.dao.AlugueisDAO;
import com.livraria.dao.LivroDAO;
import com.livraria.entity.Alugueis;
import com.livraria.entity.Aluno;
import com.livraria.entity.Livro;
import com.livraria.listarTabelas.ListarAlugueisLivro;
import com.livraria.listarTabelas.ListarLivros;
import com.livraria.util.Alertas;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class DesalugarLivroController implements Initializable {

	@FXML
	TableView<ListarAlugueisLivro> tbv_tabelaPai = new TableView<ListarAlugueisLivro>();
	@FXML
	TableColumn<ListarAlugueisLivro, Long> tbc_id;
	@FXML
	TableColumn<ListarAlugueisLivro, String> tbc_codigoLivro;
	@FXML
	TableColumn<ListarAlugueisLivro, String> tbc_tituloLivro;
	@FXML
	TableColumn<ListarAlugueisLivro, String> tbc_nomeAluno;
	@FXML
	TableColumn<ListarAlugueisLivro, String> tbc_raAluno;
	@FXML
	TableColumn<ListarAlugueisLivro, String> tbc_disponivel;
	@FXML
	TextField txt_busca;
	@FXML
	JFXButton btn_busca;

	AlugueisDAO alugueisDAO = new AlugueisDAO();
	List<Alugueis> alugueisList = alugueisDAO.lista();

	ObservableList<ListarAlugueisLivro> listarLivrosOBS = FXCollections.observableArrayList();

	public void listarTabela() {
		if (!listarLivrosOBS.isEmpty()) { // SE Não ESTIVER VASIO
			listarLivrosOBS.clear();
			System.out.println("Listou tabela");
		}

		for (Alugueis o : alugueisList) {
			ListarAlugueisLivro list = new ListarAlugueisLivro(o.getId(), o.getCodigoLivro(), o.getTituloLivro(),
					o.getNomeAluno(), o.getRaAluno(), o.getDisponivel());
			listarLivrosOBS.addAll(list);
		}

		tbc_id.setCellValueFactory(new PropertyValueFactory<ListarAlugueisLivro, Long>("id"));
		tbc_codigoLivro.setCellValueFactory(new PropertyValueFactory<ListarAlugueisLivro, String>("codigoLivro"));
		tbc_tituloLivro.setCellValueFactory(new PropertyValueFactory<ListarAlugueisLivro, String>("tituloLivro"));
		tbc_nomeAluno.setCellValueFactory(new PropertyValueFactory<ListarAlugueisLivro, String>("nomeAluno"));
		tbc_raAluno.setCellValueFactory(new PropertyValueFactory<ListarAlugueisLivro, String>("raAluno"));
		tbc_disponivel.setCellValueFactory(new PropertyValueFactory<ListarAlugueisLivro, String>("disponivel"));

		tbv_tabelaPai.setItems(listarLivrosOBS);
	}

	public void initialize(URL location, ResourceBundle resources) {
		listarTabela();
	}

	@FXML
	public void buscaBTN(ActionEvent event) {
		alugueisDAO = new AlugueisDAO();
		if (Alertas.campoVasio(txt_busca.getText())) {
			Alertas.alertaErro("Digite o codigo do livro no campo Busca");
		} else {
			AlugueisDAO alugueisDAO = new AlugueisDAO();

			Alugueis alugueis = alugueisDAO.buscar(txt_busca.getText());

			if (alugueis == null) {
				Alertas.alertaErro("O livro não está alugado");
			} else {
				alugueisList = alugueisDAO.buscarEListar(txt_busca.getText());

				listarTabela();
			}
		}
	}

	@FXML
	public void pegarCodigoLivro(MouseEvent event) {
		ListarAlugueisLivro pegarCodigoLivro = tbv_tabelaPai.getSelectionModel().getSelectedItem();
		if (pegarCodigoLivro != null) {

			pegarCodigoLivro.getId();

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Liverar locação");
			alert.setContentText("Desalugar livro ?");

			ButtonType confirmar = new ButtonType("Confirmar");
			ButtonType cancelar = new ButtonType("Cancelar");

			alert.getButtonTypes().setAll(confirmar, cancelar);

			Optional<ButtonType> botaoClicado = alert.showAndWait();

			if (botaoClicado.get() == confirmar) {
				AlugueisDAO alugueisDAO = new AlugueisDAO();
				Alugueis alugueis = new Alugueis();

				alugueisDAO.removeCliente(pegarCodigoLivro.getId());
				listarLivrosOBS.remove(pegarCodigoLivro);

				Livro livro = new Livro();

				LivroDAO livroDAO = new LivroDAO();
				livro.setDisponivel("Disponivel");
				livroDAO.editar(livro, pegarCodigoLivro.getCodigoLivro());

				Alertas.alertaSucesso("O livro está disponivel para locação");
			} else if (botaoClicado.get() == cancelar) {

			}
		} else {
			Alertas.alertaErro("Por favor, selecione um item da tabela");
		}

	}
}
