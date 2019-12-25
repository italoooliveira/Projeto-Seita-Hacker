/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import projeto.classes.entitys.Desafio;
import projeto.classes.util.Desafios;

/**
 * FXML Controller class
 *
 * @author Italo
 */
public class DesafiosController implements Initializable {

    @FXML
    private TableView<Desafio> tbvDesafios;

    @FXML
    private TableColumn<Desafio, Integer> tbcNumero;
    @FXML
    private TableColumn<Desafio, String> tbcNome;
    @FXML
    private TableColumn<Desafio, Integer> tbcPontos;

    private ObservableList<Desafio> listarDesafios() {
        Desafios desafios = new Desafios();

        List<Desafio> listaDesafios = desafios.listar();

        return FXCollections.observableArrayList(
            listaDesafios
        );
    }

    @FXML
    private void abrirQuiz(ActionEvent event) throws IOException {
        int numero = tbvDesafios.getSelectionModel().getSelectedItem().getNumero();
        String titulo = tbvDesafios.getSelectionModel().getSelectedItem().getNome();
        String descricao = tbvDesafios.getSelectionModel().getSelectedItem().getDescricao();
        int pontos = tbvDesafios.getSelectionModel().getSelectedItem().getPontos();
        int tempo = tbvDesafios.getSelectionModel().getSelectedItem().getTempoLimite();
        String opcaoA = tbvDesafios.getSelectionModel().getSelectedItem().getOpcaoA();
        String opcaoB = tbvDesafios.getSelectionModel().getSelectedItem().getOpcaoB();
        String opcaoC = tbvDesafios.getSelectionModel().getSelectedItem().getOpcaoC();
        String opcaoD = tbvDesafios.getSelectionModel().getSelectedItem().getOpcaoD();
        String resposta = tbvDesafios.getSelectionModel().getSelectedItem().getRespostaCerta();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Quiz.fxml"));

        loader.setController(new QuizController(numero, titulo, descricao, pontos, tempo, opcaoA, opcaoB, opcaoC, opcaoD, resposta));

        Parent root = loader.load();

        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcPontos.setCellValueFactory(new PropertyValueFactory<>("pontos"));

        tbvDesafios.setItems(listarDesafios());
    }

}
