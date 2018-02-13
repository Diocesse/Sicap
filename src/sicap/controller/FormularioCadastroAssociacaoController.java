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
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.sicap.consultas.DaoAssociacao;
import org.sicap.negocio.Associacao;
import org.sicap.upload.UtilidadesImage;
import org.sicap.util.Utilidades;
import org.sicap.util.UtilitsMascara;

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
    private Label labelMsg,lbCnpj,lbOrdem;

    private byte[] imageLogo;
    @FXML
    private TabPane tabPaneAss;
    UtilitsMascara mascara = UtilitsMascara.instanceOf();
    private Long id = 0L;
    private Associacao associacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamTela = kit.getScreenSize();
        tabPaneAss.setPrefHeight(tamTela.getHeight());
        tabPaneAss.setPrefWidth(tamTela.getWidth());
        mascara.mascaraCNPJ(txtCnpj);
        mascara.mascaraCEP(txtCep);
        mascara.mascaraOrdem(txtOrdemUTM);
        mascara.mascaraFixo(txtFixo);
        mascara.mascaraCelular(txtCelular);
        associacao = ControllerFormAssociacao.getAssociacao();
        if (associacao != null) {
            try {
                id = associacao.getIdAssociacao();
                txtNome.setText(associacao.getAssociacao());
                txtCelular.setText(associacao.getTelefoneCelular());
                txtFixo.setText(associacao.getFixo());
                txtEstado.setText(associacao.getEstado());
                txtEmail.setText(associacao.getEmail());
                txtNumero.setText(String.valueOf(associacao.getNumero()));
                txtEndereco.setText(associacao.getEndereco());
                txtCnpj.setText(associacao.getCNPJ());
                txtOrdemUTM.setText(associacao.getOrdemUtilidade());
                txtCep.setText(associacao.getCep());
                txtCidade.setText(associacao.getCidade());
                txtBairro.setText(associacao.getBairro());
                imageBanner.setImage(new Image(new ByteArrayInputStream(associacao.getLogo())));
                imageLogo = associacao.getLogo();
            } catch (Exception e) {
            }

        }
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
    Utilidades utilidades = Utilidades.InstanceOf();

    @FXML
    public void validacaoNome(KeyEvent event) {
        validarNome(txtNome, lbNome);
    }

    @FXML
    public void validacaoKeyEstado(KeyEvent event) {
        validarEstado(txtEstado, lbEstado);
    }
    @FXML
    public void validacaoKeyCNPJ(KeyEvent event) {
        validarMascarasCNPJ(txtCnpj, lbCnpj);
    }
    @FXML
    public void validacaoKeyOrdem(KeyEvent event) {
        validarMascarasOrdem(txtOrdemUTM, lbOrdem);
    }

    public boolean validarMascarasCNPJ(TextField texto, Label msg) {
        boolean erro = false;
        String img;
       // Associacao teste = da.consultaPorCnpj(texto.getText());
        //System.out.println("tesy: "+teste.getAssociacao());
        if (texto.getText().trim().equals("") || texto.getText().length() != 18 || da.consultaPorCnpj(texto.getText()) != null ) {
            erro = true;
        }
        if (erro) {
            msg.setText("CNPJ Invalido!");
            msg.setTextFill(Paint.valueOf("red"));
            img = "erro";
        } else {
            msg.setText("CNPJ valido!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));

        texto.requestFocus();
        return !erro;
    }
        public boolean validarMascarasOrdem(TextField texto, Label msg) {
        boolean erro = false;
        String img;
        if (texto.getText().trim().equals("") || texto.getText().length() != 8) {
            erro = true;
        }

        if (erro) {

            msg.setText("Ordem Invalido!");
            msg.setTextFill(Paint.valueOf("red"));
            img = "erro";
        } else {
            msg.setText("Ordem valido!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));

        texto.requestFocus();
        return !erro;
    }

    
    public boolean validarCampos() {
        boolean erro = false;
        if (validarNome(txtNome, lbNome)) {
            erro = true;
        }
        if (validarMascarasCNPJ(txtCnpj, lbCnpj)){
            erro=true;
        }
        if(validarMascarasOrdem(txtOrdemUTM, lbOrdem)){
            erro=true;
        }
        if (erro) {
            labelMsg.setText("Existe dados invalidos no formulario!");
            labelMsg.setTextFill(Paint.valueOf("red"));
            labelMsg.setGraphic(utilidades.iconFalha());
            labelMsg.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), CornerRadii.EMPTY, Insets.EMPTY)));

        }
        return !erro;
    }

    @FXML
    private Label lbNome, lbEstado;

    public boolean validarNome(TextField texto, Label msg) {
        boolean erro = false;
        String img;
        if (texto.getText().trim().equals("")) {
            erro = true;
        }
        if (erro) {
            msg.setText("" + " A informação está Invalido!");
            msg.setTextFill(Paint.valueOf("red"));
            texto.setPromptText("" + " A informação está Invalido!");
            img = "erro";
        } else {
            msg.setText("" + " A informação está valido!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));

        texto.requestFocus();
        return !erro;
    }

    public boolean validarEstado(TextField texto, Label msg) {
        boolean erro = false;
        String img;
        if (texto.getText().trim().equals("") || Utilidades.validarStringDigitado(texto.getText()) || Utilidades.validarcaracEspecial(texto.getText())) {
            erro = true;
        }
        if (erro) {
            msg.setText("" + "Invalido!");
            msg.setTextFill(Paint.valueOf("red"));
            texto.setPromptText("" + " A informação está Invalido!");
            img = "erro";
        } else {
            msg.setText("" + " Valida!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));

        texto.requestFocus();
        return !erro;
    }

    @FXML
    public void textAutomatizado(ActionEvent e) {
        txtNome.setText("Associação de Pescadores de Acupe");
        txtCidade.setText("Sta");
        txtEstado.setText("Ba");
        txtEndereco.setText("Porto do Meio");
        txtEmail.setText("apmom@gmail.com");
        txtCep.setText("44218-000");
        txtBairro.setText("Acupe");
        txtFixo.setText("(75) 3209-2983");
        txtCelular.setText("(71) 98234-3984");
        txtOrdemUTM.setText("6.383/76");
        txtCnpj.setText("79.237.539/8272-98");
        txtNumero.setText("124");

    }

    @FXML
    public void gravar(ActionEvent event) {
        try {
            boolean validar = validarCampos();
            if (!validar) {
                Associacao a = new Associacao();
                a.setIdAssociacao(id);
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
                    if (imageLogo != null) {
                        a.setLogo(imageLogo);
                    } else {
                        System.out.println("A imagem nao foi atualizada!");
                    }
                } catch (IOException ex) {
                    System.out.println("A imagem não foi inserida.");
                }
                String context = "";
                String msg = "";
                Object aqui = (Object) da.save(a);
                String cor = "";
                String corF = "";
                if (aqui != null) {
                    context = "sucesso";
                    msg = "Dados salvo com sucesso!";
                    cor = "white";
                    corF = "green";
                } else {
                    context = "erro";
                    msg = " Falha em tentar cadastrar a Associação";
                    cor = "red";
                    corF = "black";
                }
                labelMsg.setText(msg);
                labelMsg.setTextFill(Paint.valueOf(cor));
                labelMsg.setGraphic(utilidades.iconSucessoFalha(context));
                labelMsg.setBackground(new Background(new BackgroundFill(Paint.valueOf(corF), CornerRadii.EMPTY, Insets.EMPTY)));
                animateMessage();
            }
            id = 0L;
            associacao = null;

        } catch (Exception e) {
        }

    }

}
