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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import projeto.classes.entitys.Hacker;
import projeto.classes.util.Login;
import projeto.classes.util.ManipulacaoArquivo;
import projeto.classes.util.MontarRanking;

/**
 *
 * @author Italo
 */
public class LoginController implements Initializable {

    RadioButton selectedRadioButton;
    String toogleGroupValue;
    
    ToggleGroup tgrpGrupo = new ToggleGroup();

    @FXML
    private Label lblErro;

    @FXML
    private TextField txfdCodenome;
    @FXML
    private PasswordField txfdSenha;

    @FXML
    private RadioButton rbtnAdmin;
    @FXML
    private RadioButton rbtnParticipante;

    @FXML
    private void Logar(ActionEvent event) throws IOException {
        selectedRadioButton = (RadioButton) tgrpGrupo.getSelectedToggle();
        toogleGroupValue = selectedRadioButton.getText();

        if (!verificarSeCamposPreenchidos()) {
            Hacker hacker = new Hacker(txfdCodenome.getText(), txfdSenha.getText());
           
            Login login = new Login();

            boolean[] validacao = login.validarAcesso(hacker);

            if (validacao[0]) {
                MontarRanking.hackerLogado = hacker;
                
                if (validacao[1]) {
                    if (toogleGroupValue.equals("Participante")) {
                        abrirRegras(event, true);
                    } else {
                        abrirPrincipal(event);
                    }
                } else {
                    abrirPrincipal(event);
                }
            } else {
                lblErro.setText("Não existe ninguém com esses dados");
                lblErro.setVisible(true);
            }

        } else {
            lblErro.setText("Os campos nao podem ficar em branco");
            lblErro.setVisible(true);
        }
    }

    private boolean verificarSeCamposPreenchidos() {
        boolean naoEstaoPreenchidos = false;

        if (txfdCodenome.getText().isEmpty() && txfdSenha.getText().isEmpty()) {
            naoEstaoPreenchidos = true;
        }

        return naoEstaoPreenchidos;
    }

    @FXML
    private void abrirCadastro(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    private void abrirRegras(ActionEvent event, boolean ehPrimeiroAcesso) throws IOException {
        Node source = (Node) event.getSource();
        Stage stageAtual = (Stage) source.getScene().getWindow();
        stageAtual.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Regras.fxml"));

        loader.setController(new RegrasController(ehPrimeiroAcesso));

        Parent root = loader.load();

        Stage stage = new Stage();

        Scene scene = new Scene(root);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private void abrirPrincipal(ActionEvent event) throws IOException {
        selectedRadioButton = (RadioButton) tgrpGrupo.getSelectedToggle();
        toogleGroupValue = selectedRadioButton.getText();

        Stage stage = new Stage();

        Parent root = null;

        if (toogleGroupValue.equals("Participante")) {
            root = FXMLLoader.load(getClass().getResource("Principal.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("PrincipalAdmin.fxml"));
        }

        Scene scene = new Scene(root);

        Node source = (Node) event.getSource();

        Stage stageAtual = (Stage) source.getScene().getWindow();
        stageAtual.close();

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        rbtnAdmin.setToggleGroup(tgrpGrupo);
        rbtnParticipante.setToggleGroup(tgrpGrupo);
    }

}
