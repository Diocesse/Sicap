/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicap.controller;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.eclipse.persistence.sdo.helper.extension.SDOUtil;
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
    private Label txtInfo, txtInfo1,lbNome;
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
            logo.setImage(new Image(getClass().getResourceAsStream("/sicap/image/logo.jpg")));

        } catch (Exception e) {
        }
    }

    SicapAPMOM sicap = SicapAPMOM.instance();
    private static Funcionario f = null;
    DaoProfissao dp = new DaoProfissao();
    DaoAdminAutenticacao autenticacao = new DaoAdminAutenticacao();

    public static Funcionario getF() {
        return f;
    }

    public static void setF(Funcionario f) {
        ControllerSicap.f = f;
    }

    private static Administrador adm = null;

    public static Administrador getAdm() {
        return adm;
    }

    public static void setAdm(Administrador adm) {
        ControllerSicap.adm = adm;
    }

    public ImageView iconSucesso() {
        Image image = new Image(getClass().getResourceAsStream("/sicap/image/sucesso.gif"));
        ImageView iv = new ImageView(image);
        iv.fitHeightProperty().set(20);
        iv.fitWidthProperty().set(20);
        return iv;

    }

    public ImageView iconFalha() {
        Image image = new Image(getClass().getResourceAsStream("/sicap/image/erro.gif"));
        ImageView iv = new ImageView(image);
        iv.fitHeightProperty().set(20);
        iv.fitWidthProperty().set(20);
        return iv;

    }
    Administrador user=null;
    Funcionario userF=null;

    @FXML
    public void userLogin(KeyEvent event) {

        user = autenticacao.autenticacaoLogin(txtUser.getText());
        if (user != null) {
            txtInfo.setText("Seu login esta valido! ");

            txtInfo.setGraphic(iconSucesso());
            userF =null;

        }
        userF = da.autenticaLogin(txtUser.getText());
        if (userF != null) {
            txtInfo.setText("Seu login esta valido! ");
            txtInfo.setGraphic(iconSucesso());
             user=null;
        }
        if (user == null && userF == null) {
            txtInfo.setText("Seu login esta Invalido! ");
            txtInfo.setGraphic(iconFalha());
        }

    }

    @FXML
    public void passwordLogin(KeyEvent event) {
        try {
           System.out.println("Adm: " + user + " Func: " + userF);
            if (user != null) {
                System.out.println("Passou aqui...");
                Administrador passwd = autenticacao.autenticacaoPAssword(txtSenha.getText());
                if (passwd != null) {
                    txtInfo1.setText("Sua senha está valido!");
                    txtInfo1.setGraphic(iconSucesso());
                    txtOla.setText("Seu acesso foi autorizado!");
                    txtOla.setTextFill(Paint.valueOf("green"));
                    txtOla.setGraphic(iconSucesso());
                    lbNome.setText("Adm: "+passwd.getUsuario());
                }
                if (passwd == null) {
                    txtInfo1.setText("Sua senha está Invalido!");
                    txtInfo1.setGraphic(iconFalha());
                 //   txtOla.setText("Seu acesso nao esta mais foi autorizado!");
                }
            }else if (userF != null) {
                Funcionario passwdF = da.getFuncionario(userF.getUser(),txtSenha.getText());
                if (passwdF != null) {
                    txtInfo1.setText("Sua senha está valido! ");
                    txtInfo1.setGraphic(iconSucesso());
                    txtOla.setText("Seu acesso foi autorizado!");
                    txtOla.setTextFill(Paint.valueOf("green"));
                    txtOla.setGraphic(iconSucesso());
                    lbNome.setText(passwdF.getNome());
                    try {
                        imageAuto.setImage(new Image(new ByteArrayInputStream(passwdF.getFoto())));
                    } catch (Exception e) {
                        System.out.println("Foto não encontrada seus dados.");
                    }

                }
                if (passwdF == null) {
                    txtInfo1.setText("Sua senha está Invalido!");
                    txtInfo1.setGraphic(iconFalha());
                    
                }
            }

        } catch (Exception e) {
        }

    }

    public Funcionario validarAcessoFunc(String login, String senha) {

        f = da.getFuncionario(login, senha);

        return f;

    }

    public Administrador validarAcessoAdm(String login, String senha) {
        adm = autenticacao.acessoRestritoAdmin(login, senha);
        return adm;

    }
    private DaoAssociado da = new DaoAssociado();

    @FXML
    public void logar(ActionEvent e) {
        //Funcionario f;
        String login = txtUser.getText();
        String senha = txtSenha.getText();

        f = validarAcessoFunc(login, senha);
        if (f != null) {
            ((Stage) btCancelar.getScene().getWindow()).close();
            sicap.openPrincipal("ViewPrincipal", "Associação de Pescadores de Acupe");
            setF(f);
        }
        adm = validarAcessoAdm(login, senha);
        //  System.out.println("Adm: " + userAdm + " Func: " + userFunc);
        if (adm != null) {
            ((Stage) btCancelar.getScene().getWindow()).close();
            sicap.openPrincipal("ViewPrincipal", "Associação de Pescadores de Acupe");
        }

        if (adm == null || f == null) {
            txtOla.setText("Login ou senha invalidos!");
            txtOla.setTextFill(Paint.valueOf("red"));
            txtOla.setGraphic(iconFalha());
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
