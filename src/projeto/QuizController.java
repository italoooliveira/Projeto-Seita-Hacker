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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import projeto.classes.entitys.Hacker;
import projeto.classes.util.Login;
import projeto.classes.util.ManipulacaoArquivo;
import projeto.classes.util.MontarRanking;

/**
 * FXML Controller class
 *
 * @author Italo
 */
public class QuizController implements Initializable {

    ToggleGroup tgrpGrupo = new ToggleGroup();

    private int numero;
    private String titulo;
    private String descricao;
    private int pontos;
    private int tempo;
    private String opcaoA;
    private String opcaoB;
    private String opcaoC;
    private String opcaoD;
    private String resposta;

    private int pontosExtras = 0;

    Timer timer = new Timer();

    @FXML
    private Label lblTitulo;
    @FXML
    private Label cronometro;
    @FXML
    private Label lblPontosNormais;
    @FXML
    private Label lblPontosExtras;

    @FXML
    private TextArea txaDescricao;

    @FXML
    private RadioButton rbtnA;
    @FXML
    private RadioButton rbtnB;
    @FXML
    private RadioButton rbtnC;
    @FXML
    private RadioButton rbtnD;

    @FXML
    private Button btnComecar;

    public QuizController(int numero, String titulo, String descricao, int pontos, int tempo, String opcaoA, String opcaoB, String opcaoC, String opcaoD, String resposta) {
        this.numero = numero;
        this.titulo = titulo;
        this.descricao = descricao;
        this.pontos = pontos;
        this.tempo = tempo;
        this.opcaoA = opcaoA;
        this.opcaoB = opcaoB;
        this.opcaoC = opcaoC;
        this.opcaoD = opcaoD;
        this.resposta = resposta;
    }

    @FXML
    private void comecar(ActionEvent event) {
        btnComecar.setDisable(true);
        rbtnA.setDisable(false);
        rbtnB.setDisable(false);
        rbtnC.setDisable(false);
        rbtnD.setDisable(false);

        timer.scheduleAtFixedRate(new TimerTask() {
            int contador = tempo;

            @Override
            public void run() {
                Platform.runLater(() -> {
                    contador--;
                    pontosExtras--;

                    int seg = contador;
                    int min = 0;

                    if (contador >= 60) {
                        seg = contador % 60;
                        min = contador / 60;
                    }

                    if (contador == 0) {
                        timer.cancel();
                    }

                    cronometro.setText(String.format("%02d:%02d", min, seg));
                });
            }
        }, 1000, 1000);

    }

    @FXML
    private void testaResposta(ActionEvent event) throws IOException {
        timer.cancel();

        RadioButton selectedRadioButton = (RadioButton) tgrpGrupo.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();

        int pontosTotais;

        if (toogleGroupValue.equals(resposta)) {
            pontosTotais = pontos + pontosExtras;

            MontarRanking.hackerLogado.setPontuacao(pontosTotais);
            MontarRanking.atualizaRanking();

        } else {
            pontos = 0;
            pontosExtras = 0;
            pontosTotais = 0;

            MontarRanking.hackerLogado.setPontuacao(pontosTotais);
            MontarRanking.atualizaRanking();
        }

        lblPontosNormais.setText("Pontos Normais: " + pontos);
        lblPontosExtras.setText("Pontos Extras: " + pontosExtras);

        lblPontosNormais.setVisible(true);
        lblPontosExtras.setVisible(true);
    }

    private void atualizarLabelTempo() {
        int seg = tempo % 60;
        int min = tempo / 60;

        cronometro.setText(String.format("%02d:%02d", min, seg));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String caminhoAtual = new File("").getAbsolutePath();
        String caminhoArquivo = caminhoAtual + File.separator + "src/projeto/files/hackers.csv";

        try {
            ManipulacaoArquivo.lerArquivo(caminhoArquivo, ",");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<String[]> linhas = ManipulacaoArquivo.getLinhasHacker();

        for (String[] linha : linhas) {
            Hacker hacker = new Hacker(linha[1], linha[2], linha[3], linha[4], linha[5], Integer.parseInt(linha[6]));

            MontarRanking.listaHackers.add(hacker);
        }

        pontosExtras = ((tempo / 60) * 100) + tempo % 60;

        atualizarLabelTempo();

        lblTitulo.setText(titulo);
        txaDescricao.setText(descricao);

        rbtnA.setToggleGroup(tgrpGrupo);
        rbtnB.setToggleGroup(tgrpGrupo);
        rbtnC.setToggleGroup(tgrpGrupo);
        rbtnD.setToggleGroup(tgrpGrupo);

        rbtnA.setText(opcaoA);
        rbtnB.setText(opcaoB);
        rbtnC.setText(opcaoC);
        rbtnD.setText(opcaoD);

        rbtnA.setDisable(true);
        rbtnB.setDisable(true);
        rbtnC.setDisable(true);
        rbtnD.setDisable(true);

    }

}
