/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicap.controller;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.sicap.consultas.DaoAdminAutenticacao;
import org.sicap.consultas.DaoAssociado;
import org.sicap.consultas.DaoProfissao;
import org.sicap.negocio.Administrador;
import org.sicap.negocio.Funcionario;
import sicap.layout.SicapAPMOM;

/**
 * FXML Controller class
 *
 * @author leandro
 */
public class ControllerSicap implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtSenha;
    @FXML
    private Label txtOla;
    @FXML
    private ImageView imageAuto, logo;
    @FXML
    private Button btSave, btCancelar;
    @FXML
    private Label txtInfo;
    @FXML
    private AnchorPane layout;
    @FXML
    private Pane pane;

    public ControllerSicap() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            imageAuto.setImage(new Image(getClass().getResourceAsStream("/sicap/image/pessoaM.gif")));
           //s logo.setImage(new Image(getClass().getResourceAsStream("/sicap/image/logo.jpg")));
           

        } catch (Exception e) {
        }
    }

    SicapAPMOM sicap = SicapAPMOM.instance();
    private static Funcionario f;
    DaoProfissao dp = new DaoProfissao();
    DaoAdminAutenticacao autenticacao = new DaoAdminAutenticacao();

    public static Funcionario getF() {
        return f;
    }
    private static Administrador adm;

    public static Administrador getAdm() {
        return adm;
    }

    public void setAdm(Administrador adm) {
        this.adm = adm;
    }

    @FXML
    public void loginTextfield(KeyEvent event) {
        try {
            f = da.getFuncionario(txtUser.getText(), txtSenha.getText());
            adm = autenticacao.acessoRestritoAdmin(txtUser.getText(), txtSenha.getText());
            if (f != null || adm != null) {
                System.out.println("SucessoFul...");
                txtOla.setText("Seu acesso foi autorizado!");
                txtOla.setBackground(Background.EMPTY);
                imageAuto.setImage(new Image(getClass().getResourceAsStream("/sicap/image/pessoaM.gif")));
                txtInfo.setText("" + f.getNome());
                if (adm == null )
                logo.setImage(new Image(new ByteArrayInputStream(f.getAssociacao().getLogo())));

            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }
    private DaoAssociado da = new DaoAssociado();

    @FXML
    public void logar(Event e) {
        //Funcionario f;
        try {
            //f = da.getFuncionario(txtUser.getText(), txtSenha.getText());
            if (f != null || adm != null ) {
                ((Stage) btCancelar.getScene().getWindow()).close();
                sicap.openPrincipal("ViewPrincipal", "Associação de Pescadores de Acupe");
            } else {
                txtOla.setText("Acesso não autorizado!");
                // sicap.openPrincipal("ViewPrincipal");
            }
        } catch (Exception ex) {

        }

    }

    @FXML
    public void close(Event e) {
        try {
            if (f == null) {
                ((Stage) layout.getScene().getWindow()).close();

            } else {
                txtOla.setText("Voce esta logado.");
                // sicap.openPrincipal("ViewPrincipal");
            }
        } catch (Exception ex) {

        }

    }

}
