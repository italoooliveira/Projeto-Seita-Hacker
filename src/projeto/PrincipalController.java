/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.classes.entitys.Hacker;
import projeto.classes.util.ManipulacaoArquivo;
import projeto.classes.util.MontarRanking;

/**
 * FXML Controller class
 *
 * @author Italo
 */
public class PrincipalController implements Initializable {

    List<Hacker> listaHackers = new ArrayList<Hacker>();

    @FXML
    private void abrirMontarQuiz(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("MontarQuiz.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void abrirRegras(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Regras.fxml"));

        loader.setController(new RegrasController());

        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void abrirDesafios(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("Desafios.fxml"));

        Scene scene = new Scene(root);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void abrirListaDesafios(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("DesafiosAdmin.fxml"));

        Scene scene = new Scene(root);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void abrirRanking(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("Ranking.fxml"));

        Scene scene = new Scene(root);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
