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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import projeto.classes.entitys.Classificacao;
import projeto.classes.util.MontarRanking;
import projeto.classes.util.Ranking;

/**
 * FXML Controller class
 *
 * @author Italo
 */
public class RankingController implements Initializable {

    @FXML
    private TableView<Classificacao> tbvRanking;

    @FXML
    private TableColumn<Classificacao, Integer> tbcClassificacao;
    @FXML
    private TableColumn<Classificacao, String> tbcCodenome;
    @FXML
    private TableColumn<Classificacao, Integer> tbcPontuacao;

    @FXML
    private ObservableList<Classificacao> listarHackers() {
        Ranking ranking = new Ranking();

        List<Classificacao> listaHackers = ranking.listar();

        return FXCollections.observableArrayList(
                listaHackers
        );
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            MontarRanking.gerarRankingHackers();
        } catch (IOException ex) {
            Logger.getLogger(RankingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tbcClassificacao.setCellValueFactory(new PropertyValueFactory<>("lugar"));
        tbcCodenome.setCellValueFactory(new PropertyValueFactory<>("codenome"));
        tbcPontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));

        tbvRanking.setItems(listarHackers());
    }

}
