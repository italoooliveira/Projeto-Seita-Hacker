/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author Italo
 */
public class RegrasController implements Initializable {

    private boolean ehPrimeiroAcesso;

    @FXML
    ScrollPane spnlRegras;

    @FXML
    TextArea txaRegras;

    public RegrasController() {

    }

    public RegrasController(boolean ehPrimeiroAcesso) {
        this.ehPrimeiroAcesso = ehPrimeiroAcesso;
    }

    public void carregarTexto() {
        try {
            String caminhoAtual = new File("").getAbsolutePath();
            String caminhoArquivo = caminhoAtual + File.separator + "src/projeto/files/txt/regras.txt";

            Scanner ler = new Scanner(new File(caminhoArquivo)).useDelimiter("\\s+");
            while (ler.hasNext()) {
                txaRegras.appendText(ler.nextLine());
                txaRegras.appendText("\n");
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void fechar(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stageAtual = (Stage) source.getScene().getWindow();
        stageAtual.close();

        if (ehPrimeiroAcesso) {
            Stage stage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("Principal.fxml"));

            Scene scene = new Scene(root);

            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTexto();
    }
}
