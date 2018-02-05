/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicap.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sicap.layout.SicapAPMOM;

/**
 * FXML Controller class
 *
 * @author leandro
 */
public class ViewPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     *
     *
     */
    @FXML
    private MenuBar menuP;
    @FXML
    private Menu itemCadastro;
    @FXML
    private Menu itemConsulta, itemPagamento;

    @FXML
    private ImageView imageBanner, imageLogo;

    @FXML
    Label txtOla, txtOla1;
    @FXML
    private Circle circle;
    @FXML
    private Hyperlink linkAssociacao;
    @FXML
    private Hyperlink linkAssociado;
    @FXML
    private Hyperlink linkConsulta;
    @FXML
    private Hyperlink linkSair;
    @FXML
    private Hyperlink linkPagamento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamTela = kit.getScreenSize();
        menuP.setPrefWidth(tamTela.getWidth());

        carregarImageMenu();
        if (ControllerSicap.getF() != null) {
            txtOla.setText("Seja bem vindo: " + ControllerSicap.getF().getNome().toUpperCase());
            txtOla1.setText(ControllerSicap.getF().getAssociacao().getAssociacao().toUpperCase());
            imageLogo.setImage(new Image(new ByteArrayInputStream(ControllerSicap.getF().getAssociacao().getLogo())));

        }

    }
  public static SicapAPMOM sicap = SicapAPMOM.instance();

    @FXML
    public void carregarFormAssociacao(ActionEvent event) {

        sicap.openPrincipal("FormAssociacao", "Pesquisar Associações");
    }
    private File f;

    @FXML
    public void carregarArquivo(ActionEvent e) throws FileNotFoundException, IOException {
        FileChooser file = new FileChooser();
        try {
            f = file.showOpenDialog(new Stage());
            FileInputStream stream = new FileInputStream(f);
            imageBanner.setImage(new Image(stream));

        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }

    }

    @FXML
    public void carregarFormAssociado(ActionEvent event) {
        sicap.carregarFormCargosProfissao("FormAssociado", "Formulario de cadastro dos Cargos", new Stage());

    }

    private int h = 40;
    private int w = 40;

    private void carregarImageMenu() {
        try {
            Image imageM = new Image(getClass().getResourceAsStream("/sicap/image/pessoaM.gif"));
            ImageView im = new ImageView(imageM);
            im.fitHeightProperty().set(h);
            im.fitWidthProperty().set(w);
            linkAssociado.setGraphic(im);

            Image imageC = new Image(getClass().getResourceAsStream("/sicap/image/pesquisar.gif"));
            ImageView ic = new ImageView(imageC);
            ic.fitHeightProperty().set(h);
            ic.fitWidthProperty().set(w);
            linkConsulta.setGraphic(ic);

            Image image = new Image(getClass().getResourceAsStream("/sicap/image/casa.gif"));
            ImageView img = new ImageView(image);
            img.fitHeightProperty().set(h);
            img.fitWidthProperty().set(w);
            linkAssociacao.setGraphic(img);
            //---------------------------
            Image image1 = new Image(getClass().getResourceAsStream("/sicap/image/cofre.gif"));
            ImageView img1 = new ImageView(image1);
            img1.fitHeightProperty().set(h);
            img1.fitWidthProperty().set(w);
            linkPagamento.setGraphic(img1);

            //---------------------------
            Image image2 = new Image(getClass().getResourceAsStream("/sicap/image/ferramenta.gif"));
            ImageView img2 = new ImageView(image2);
            img2.fitHeightProperty().set(h);
            img2.fitWidthProperty().set(w);
            itemPagamento.setGraphic(img2);

            imageBanner.setImage(new Image(getClass().getResourceAsStream("/sicap/image/pessoaM.gif")));
            imageBanner.fitHeightProperty().set(108);
            imageBanner.fitWidthProperty().set(118);
        } catch (Exception e) {
            System.out.println("Falha em carregar a imagem." + e.getMessage());
        }
    }

}
