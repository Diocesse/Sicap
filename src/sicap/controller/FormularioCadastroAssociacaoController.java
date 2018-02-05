package sicap.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.sicap.consultas.DaoAssociacao;
import org.sicap.negocio.Associacao;
import org.sicap.upload.UtilidadesImage;

public class FormularioCadastroAssociacaoController implements Initializable {

    @FXML
    private Button btCancelar;
    @FXML
    private ImageView imageBanner;
    @FXML
    private Hyperlink linkImage;
    @FXML
    private TextField txtNome, txtCnpj, txtOrdemUTM, txtEmail, txtEstado, txtCidade, txtEndereco, txtFixo, txtCelular, txtBairro, txtNumero, txtCep;
    @FXML
    private Label labelMsg;

    private byte[] imageLogo;
    @FXML
    private TabPane tabPaneAss;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamTela = kit.getScreenSize();
        tabPaneAss.setPrefHeight(tamTela.getHeight());
        tabPaneAss.setPrefWidth(tamTela.getWidth());
    }

    @FXML
    public void close(Event event) {
        ((Stage) btCancelar.getScene().getWindow()).close();
    }
    private DaoAssociacao da = new DaoAssociacao();
    private File f;

    @FXML
    public void carregarArquivo(ActionEvent e) throws FileNotFoundException, IOException {
        FileChooser file = new FileChooser();
        try {
            f = file.showOpenDialog(new Stage());
            FileInputStream stream = new FileInputStream(f);
            imageBanner.setImage(new Image(stream));
            System.out.println("" + f.getAbsolutePath());

        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }

    }
    ControllerFormAssociacao cfa = new ControllerFormAssociacao();

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), labelMsg);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    @FXML
    public void gravar(ActionEvent event) {

        Associacao a = new Associacao();
        a.setAssociacao(txtNome.getText());
        a.setBairro(txtBairro.getText());
        a.setEmail(txtEmail.getText());
        a.setCNPJ(txtCnpj.getText());
        a.setTelefoneCelular(txtCelular.getText());
        a.setFixo(txtFixo.getText());
        a.setOrdemUtilidade(txtOrdemUTM.getText());

        a.setCidade(txtCidade.getText());
        a.setEstado(txtEstado.getText());
        a.setEndereco(txtEndereco.getText());
        a.setNumero(Integer.valueOf(txtNumero.getText()));
        a.setCep(txtCep.getText());
        try {
            imageLogo = UtilidadesImage.read(f.getAbsolutePath(), 200, 200);
            a.setLogo(imageLogo);
        } catch (IOException ex) {
            System.out.println("A imagem não foi inserida.");
        }

        Object aqui = (Object) da.save(a);
        if (aqui != null) {
            labelMsg.setText("Cadastrou com sucesso.");
            labelMsg.setFont(new Font(30));
        } else {
            labelMsg.setText("Falha em tentar cadastrar.");

        }
        animateMessage();

    }

}
