package com.livraria.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.type.descriptor.java.CalendarTypeDescriptor.CalendarMutabilityPlan;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import com.jfoenix.controls.JFXButton;
import com.livraria.dao.AlunoDAO;
import com.livraria.entity.Aluno;
import com.livraria.listarTabelas.ListarAlunos;
import com.livraria.util.Alertas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class TabelaAlunosController implements Initializable {

	@FXML
	TableView<ListarAlunos> tbv_tabelaPai;
	@FXML
	TableColumn<ListarAlunos, Long> tbc_codigo;
	@FXML
	TableColumn<ListarAlunos, String> tbc_nome;
	@FXML
	TableColumn<ListarAlunos, String> tbc_ra;
	@FXML
	TextField txt_busca;
	@FXML
	JFXButton btn_busca;

	AlunoDAO alunoDAO = new AlunoDAO();
	private List<Aluno> alunoList = alunoDAO.listar();

	private ObservableList<ListarAlunos> OBSalunoList = FXCollections.observableArrayList();

	public void listarTabela() {

		if(OBSalunoList != null){
			OBSalunoList.clear();
		}
		
		for (Aluno aluno : alunoList) {
			ListarAlunos listarAlunos = new ListarAlunos(aluno.getId(), aluno.getNome(), aluno.getRa());
			OBSalunoList.addAll(listarAlunos);
		}

		tbc_codigo.setCellValueFactory(new PropertyValueFactory<ListarAlunos, Long>("id"));
		tbc_nome.setCellValueFactory(new PropertyValueFactory<ListarAlunos, String>("nome"));
		tbc_ra.setCellValueFactory(new PropertyValueFactory<ListarAlunos, String>("ra"));

		tbv_tabelaPai.setItems(OBSalunoList);

	}

	public void initialize(URL location, ResourceBundle resources) {
		listarTabela();
	}

	@FXML
	public void buscaBTN(ActionEvent event) {

		if (Alertas.campoVasio(txt_busca.getText())) {
			Alertas.alertaErro("Digite o RA no campo Busca");
		} else {

			Aluno aluno = alunoDAO.buscarAluno(txt_busca.getText());
			if (aluno == null) {
				Alertas.alertaErro("RA não cadastrado no sistema");
			} else {
				alunoList = alunoDAO.buscarAlunoEncontrado(txt_busca.getText());
				// tbv_tabelaPai.getColumns().clear();
				listarTabela();
			}
		}

	}

}
