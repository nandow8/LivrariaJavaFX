package com.livraria.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.jfoenix.controls.JFXButton;
import com.livraria.dao.AlugueisDAO;
import com.livraria.dao.AlunoDAO;
import com.livraria.dao.LivroDAO;
import com.livraria.entity.Alugueis;
import com.livraria.entity.Aluno;
import com.livraria.entity.Livro;
import com.livraria.listarTabelas.ListarLivros;
import com.livraria.util.Alertas;
import com.livraria.util.HibernateUtil;

import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;

public class TelaPrincipalController implements Initializable {

	@FXML
	private Button btn_addMembro;
	@FXML
	private Button btn_addLivro;
	@FXML
	private Button btn_verMembros;
	@FXML
	private Button btn_verLivros;
	@FXML
	private Button btn_configuracoes;
	@FXML
	private TextField txt_codigoMembro;
	@FXML
	private JFXButton btn_edicao;
	@FXML
	private JFXButton btn_remover;
	@FXML
	private Text tx_status;
	@FXML
	private Text tx_autor;
	@FXML
	private Text tx_tituloLivro;

	@FXML
	JFXButton btn_salvarLocacao;
	@FXML
	JFXButton btn_busca;
	@FXML
	TextField txt_busca;
	@FXML
	Label lb_codigo;
	@FXML
	Label lb_titulo;
	@FXML
	Label lb_autor;
	@FXML
	Label lb_distribuidora;
	@FXML
	Label lb_disponivel;

	LivroDAO livroDAO = new LivroDAO();
	private List<Livro> livroList = livroDAO.listar();;

	private ObservableList<ListarLivros> OBSlivroList = FXCollections.observableArrayList();
	@FXML
	Button btn_verAlunos;
	@FXML
	JFXButton btn_buscaAluno;
	@FXML
	Button btn_addAluno;
	@FXML
	Label lb_nome;
	@FXML
	Label lb_ra;

	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void addLivro(ActionEvent evento) throws IOException {
		chamarJanelas("/fxml/FXMLCadastroLivro.fxml", "Cadastrar Livro");
	}

	@FXML
	public void addAluno(ActionEvent event) {
		chamarJanelas("/fxml/FXMLCadastroAluno.fxml", "Cadastrar Aluno");
	}

	@FXML
	public void verAlunos(ActionEvent event) {
		chamarJanelas("/fxml/FXMLTabelaAlunos.fxml", "Lista de Alunos");
	}

	@FXML
	public void remover() {
	}

	@FXML
	public void edicaoBTN() {
	}

	@FXML
	public void configuracoes() {
		chamarJanelas("/fxml/FXMLAlugueis.fxml", "Desalugar Livro");
	}

	@FXML
	public void verLivros() {
		chamarJanelas("/fxml/FXMLTabelaLivros.fxml", "Lista de Livros");
	}

	@FXML
	public void controle() {
	}

	void chamarJanelas(String local, String titulo) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(local));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(titulo);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FXML
	public void buscaAlunoBTN(ActionEvent event) {

		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = alunoDAO.buscarAluno(txt_codigoMembro.getText());

		if (aluno != null) {
			lb_nome.setText(aluno.getNome());
			lb_ra.setText(aluno.getRa());
		} else {
			Alertas.alertaErro("Aluno nao encontrado");
		}

	}

	@FXML
	public void busca() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		String li = txt_busca.getText();

		Query buscarLivro = sessao.createQuery("from Livro where codigodoLivro = :codigo");

		buscarLivro.setParameter("codigo", li);
		Livro livro = (Livro) buscarLivro.uniqueResult();

		if (livro != null) {

			lb_autor.setText(livro.getAutor());
			lb_codigo.setText(livro.getCodigodoLivro());
			lb_distribuidora.setText(livro.getDistribuidora());
			lb_titulo.setText(livro.getTituloLivro());
			lb_disponivel.setText(livro.getDisponivel());
			
			
	    } else if (livro.getDisponivel().equals("Alugado")) {
			Alertas.alertaErro("O livro " + lb_codigo.getText() + " já está alugado");
		} else {
			Alertas.alertaErro("Livro não encontrado");
		}
		sessao.close();
	}

	@FXML
	public void salvarLocacaoBTN() {
		if (lb_autor.getText().equals("") && lb_nome.getText().equals("")) {
			Alertas.alertaErro("Por favor, selecione um livro e um aluno");
		} else {

			Alugueis alugueis = new Alugueis();
			alugueis.setAutorLivro(lb_autor.getText());
			alugueis.setCodigoLivro(lb_codigo.getText());
			alugueis.setDisponivel("Alugado");
			alugueis.setDistribuidoraLivro(lb_distribuidora.getText());
			alugueis.setNomeAluno(lb_nome.getText());
			alugueis.setRaAluno(lb_ra.getText());
			alugueis.setTituloLivro(lb_titulo.getText());

			if (lb_disponivel.getText().equals("Alugado")) {
				Alertas.alertaErro("O livro já esta alugado");
				limpaCampos();
			} else {
				AlugueisDAO aluguel = new AlugueisDAO();
				aluguel.salvar(alugueis);

				Livro livro = new Livro();
				
				LivroDAO livroDAO = new LivroDAO();
				livro.setDisponivel("Alugado");
				livroDAO.editar(livro, lb_codigo.getText());
				
				limpaCampos();

				Alertas.alertaSucesso("Salvo com sucesso");
			}
		}
	}

	private void limpaCampos() {
		lb_autor.setText("");
		lb_codigo.setText("");
		lb_disponivel.setText("");
		lb_distribuidora.setText("");
		lb_nome.setText("");
		lb_ra.setText("");
		lb_titulo.setText("");

		txt_busca.setText("");
		txt_codigoMembro.setText("");
	}

}
