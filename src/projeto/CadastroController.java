/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.classes.entitys.Hacker;

/**
 * FXML Controller class
 *
 * @author Italo
 */
public class CadastroController implements Initializable {

    @FXML
    private TextField txfdNome;
    @FXML
    private TextField txfdEmail;
    @FXML
    private TextField txfdTelefone;
    @FXML
    private TextField txfdCodenome;

    @FXML
    private PasswordField txfdSenha;

    @FXML
    private void salvarNoArquivo(ActionEvent event) throws IOException {
        String nome = txfdNome.getText();
        String email = txfdEmail.getText();
        String telefone = txfdTelefone.getText();
        String codenome = txfdCodenome.getText();
        String senha = txfdSenha.getText();

        Hacker hacker = new Hacker(nome, email, telefone, codenome, senha);

        hacker.salvarNoArquivo();

        Node source = (Node) event.getSource();
        Stage stageAtual = (Stage) source.getScene().getWindow();
        stageAtual.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
