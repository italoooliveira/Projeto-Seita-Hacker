/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import projeto.classes.entitys.Desafio;
import projeto.classes.util.Login;
import projeto.classes.util.ManipulacaoArquivo;

/**
 * FXML Controller class
 *
 * @author Italo
 */
public class MontarQuizController implements Initializable {

    ToggleGroup tgrpGrupo = new ToggleGroup();

    @FXML
    private TextField txfdNome;

    @FXML
    private TextArea txaDescricao;

    @FXML
    private TextField txfdOpcaoA;
    @FXML
    private TextField txfdOpcaoB;
    @FXML
    private TextField txfdOpcaoC;
    @FXML
    private TextField txfdOpcaoD;

    @FXML
    private RadioButton rbtnA;
    @FXML
    private RadioButton rbtnB;
    @FXML
    private RadioButton rbtnC;
    @FXML
    private RadioButton rbtnD;

    @FXML
    private TextField txfdPontos;

    @FXML
    private TextField txfdTempoMinutos;
    @FXML
    private TextField txfdTempoSegundos;

    @FXML
    private void salvarNoArquivo(ActionEvent event) throws IOException {
        List<Desafio> listaDesafios = new ArrayList<Desafio>();
        
        String caminhoAtual = new File("").getAbsolutePath();
        String caminhoArquivo = caminhoAtual + File.separator + "src/projeto/files/desafios.csv";
        
        try {
            ManipulacaoArquivo.lerArquivo(caminhoArquivo, ",");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<String[]> linhas = ManipulacaoArquivo.getLinhasDesafios();

        RadioButton selectedRadioButton = (RadioButton) tgrpGrupo.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();

        String nome = txfdNome.getText();
        String descricao = txaDescricao.getText();
        String opcaoA = txfdOpcaoA.getText();
        String opcaoB = txfdOpcaoB.getText();
        String opcaoC = txfdOpcaoC.getText();
        String opcaoD = txfdOpcaoD.getText();
        String respostaCerta;

        if (toogleGroupValue.equals("A")) {
            respostaCerta = opcaoA;
        } else if (toogleGroupValue.equals("B")) {
            respostaCerta = opcaoB;
        } else if (toogleGroupValue.equals("C")) {
            respostaCerta = opcaoC;
        } else {
            respostaCerta = opcaoD;
        }

        int pontos = Integer.parseInt(txfdPontos.getText());
        int segundos = 0;
        int minutos = 0;
        
        if(!txfdTempoSegundos.getText().equals("")){
            segundos = Integer.parseInt(txfdTempoSegundos.getText());
        } else {
            segundos = 0;
        }

        if(!txfdTempoMinutos.getText().equals("")){
            minutos = Integer.parseInt(txfdTempoMinutos.getText());
        } else {
            minutos = 0;
        }
        
        if(segundos == 0 && minutos == 0) {
            JOptionPane.showMessageDialog(null, "Coloque um valor num dos campos");
        }
        
        int tempo = minutos + segundos ;

        int numeroDesafio = (linhas.size() + 1);

        Desafio desafio = new Desafio(numeroDesafio, nome, pontos, tempo, descricao, opcaoA, opcaoB, opcaoC, opcaoD, respostaCerta);

        desafio.salvarNoArquivo();

        Node source = (Node) event.getSource();
        Stage stageAtual = (Stage) source.getScene().getWindow();
        stageAtual.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rbtnA.setToggleGroup(tgrpGrupo);
        rbtnB.setToggleGroup(tgrpGrupo);
        rbtnC.setToggleGroup(tgrpGrupo);
        rbtnD.setToggleGroup(tgrpGrupo);
    }

}
