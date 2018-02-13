/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicap.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.sicap.consultas.DaoAssociacao;
import org.sicap.consultas.DaoAssociado;
import org.sicap.consultas.DaoCargo;
import org.sicap.consultas.DaoProfissao;
import org.sicap.date.FormatarDateFx;
import org.sicap.negocio.Associacao;
import org.sicap.negocio.Cargo;
import org.sicap.negocio.Funcionario;
import org.sicap.negocio.Pescador;
import org.sicap.negocio.Profissao;
import org.sicap.upload.UtilidadesImage;
import org.sicap.util.Utilidades;
import org.sicap.util.ValidacaoForms;

/**
 *
 * @author leandro
 */
public class ControllerAssociado implements Initializable {

    @FXML
    private Pane pane1;
    @FXML
    private TabPane paneScroll;
    @FXML
    private ComboBox sexosBox, associacaoBox, profissaoBox, cargosBox;
    @FXML
    private Tab tabReferencia, tabAutenticacao;

    @FXML
    private DatePicker txtDataNascimento, txtMatricula;

    @FXML
    private TextArea pontoReferencia, pessoaReferencia;

    @FXML
    private ImageView imageFoto, seguranca;
    @FXML
    private Label labelCargo;
    @FXML
    private Button btProf, btCarg;
    @FXML
    private TextField txtCPF, txtRG, txtNIT, txtCIE, txtFixo, txtCelular, txtCEP, txtNome;
    @FXML
    private TextField txtNumero, txtCidade, txtEstado, txtBairro, txtEmail, txtEndereco;
    @FXML
    private Label txtTexto, txtMsg;
    @FXML
    private TextField txtlogin;
    @FXML
    private PasswordField txtSenha, cfSenha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //To change body of generated methods, choose Tools | Templates.
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamTela = kit.getScreenSize();
        paneScroll.setPrefHeight(tamTela.getHeight());
        paneScroll.setPrefWidth(tamTela.getWidth());
        carregarSexos();
        // System.out.println("aqui.... " + FormAssociadoController.getFuncionario());
        if (FormAssociadoController.getFuncionario() != null) {
            try {
                carregarCargos();
                tabReferencia.setDisable(true);
                txtTexto.setText("Ficha de Inscrição para Funcionario.");
                seguranca.setImage(new Image(getClass().getResourceAsStream("/sicap/image/seguranca.gif")));

            } catch (Exception e) {
                System.out.println("Image não encontrada! " + e.getLocalizedMessage());
            }
        } else if (FormAssociadoController.getPescador() != null) {
            txtTexto.setText("Ficha de Inscrição para Associado.");
            labelCargo.setDisable(true);
            btCarg.setDisable(true);
            tabAutenticacao.setDisable(true);
            cargosBox.setDisable(true);

        }
        System.out.println("carregar form aqui...");

        carregarAssociacoes();
        carregarProfissoes();
        mascaraCPF(txtCPF);
        mascaraRG(txtRG);
        mascaraCIE(txtCIE);
        mascaraNit(txtNIT);
        mascaraTelefoneFixo(txtFixo);
        mascaraCEP(txtCEP);
        mascaraTelefoneCelular(txtCelular);

    }

    DaoAssociado daoAssociado = new DaoAssociado();
    private byte[] foto;

    @FXML
    public void carregarFormCargo(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Novo cargo?");
        dialog.setHeaderText("Informação nova?");
        dialog.setContentText("Qual é o novo cargo:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            Cargo c = new Cargo();
            c.setCargo(result.get());
            new DaoCargo().save(c);
        }
        carregarCargos();
    }

    @FXML
    public void carregarFormProfissao(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nova profissão?");
        dialog.setHeaderText("Nova Informação?");
        dialog.setContentText("Qual é o nova profissão:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            Profissao p = new Profissao();
            p.setProfissao(result.get());
            new DaoProfissao().save(p);
        }
        carregarProfissoes();
    }

    @FXML
    public void gravar(ActionEvent event) {
        boolean campos = validarCampos();
        System.out.println("Testar campos: " + campos);
        String context = "";
        if (!campos) {
            if (FormAssociadoController.getPescador() != null) {

                Pescador pescador = new Pescador();
                pescador.setIdAssociado(0);
                pescador.setNome(txtNome.getText());
                pescador.setTelefoneFixo(txtFixo.getText());
                pescador.setNIT(txtNIT.getText());
                pescador.setCIE(txtCIE.getText());
                pescador.setCPF(txtCPF.getText());
                pescador.setRG(txtRG.getText());
                pescador.setSexo((String) sexosBox.getSelectionModel().getSelectedItem());
                pescador.setEstado(txtEstado.getText());
                pescador.setCidade(txtCidade.getText());
                pescador.setNumero(Long.valueOf(txtNumero.getText()));
                pescador.setPontoReferencia(pontoReferencia.getText());
                pescador.setReferencia(pessoaReferencia.getText());
                pescador.setTelefoneCelular(txtCelular.getText());
                pescador.setTelefoneFixo(txtFixo.getText());
                pescador.setDataCadastro(FormatarDateFx.asDate(FormatarDateFx.localInserir(txtMatricula)));
                pescador.setDataNascimento(FormatarDateFx.asDate(FormatarDateFx.localInserir(txtDataNascimento)));
                pescador.setEmail(txtEmail.getText());
                pescador.setEndereco(txtEndereco.getText());
                pescador.setFoto(fotoImage());
                pescador.setAssociacao((Associacao) associacaoBox.getSelectionModel().getSelectedItem());
                pescador.setProfissao((Profissao) profissaoBox.getSelectionModel().getSelectedItem());
                daoAssociado.save(pescador);
                context = "associado";

            }
            if (FormAssociadoController.getFuncionario() != null) {

                Funcionario funcionario = new Funcionario();
                funcionario.setIdAssociado(0);
                funcionario.setNome(txtNome.getText());
                funcionario.setTelefoneFixo(txtFixo.getText());
                funcionario.setNIT(txtNIT.getText());
                funcionario.setCIE(txtCIE.getText());
                funcionario.setCPF(txtCPF.getText());
                funcionario.setRG(txtRG.getText());
                funcionario.setSexo((String) sexosBox.getSelectionModel().getSelectedItem());
                funcionario.setEstado(txtEstado.getText());
                funcionario.setCidade(txtCidade.getText());
                funcionario.setNumero(Long.valueOf(txtNumero.getText()));
                funcionario.setTelefoneCelular(txtCelular.getText());
                funcionario.setTelefoneFixo(txtFixo.getText());
                funcionario.setDataCadastro(FormatarDateFx.asDate(FormatarDateFx.localInserir(txtMatricula)));
                funcionario.setDataNascimento(FormatarDateFx.asDate(FormatarDateFx.localInserir(txtDataNascimento)));
                funcionario.setEmail(txtEmail.getText());
                funcionario.setEndereco(txtEndereco.getText());
                funcionario.setFoto(fotoImage());
                Associacao associacao = (Associacao) associacaoBox.getSelectionModel().getSelectedItem();
                funcionario.setAssociacao(associacao);
                Profissao profissao = (Profissao) profissaoBox.getSelectionModel().getSelectedItem();
                funcionario.setProfissao(profissao);
                Cargo cargo = (Cargo) cargosBox.getSelectionModel().getSelectedItem();
                funcionario.setCargo(cargo);
                funcionario.setSenha(txtSenha.getText());
                funcionario.setUser(txtlogin.getText());
                daoAssociado.save(funcionario);
                context = "funcionario";

            }
            txtMsg.setText("Aguarde...");
            animateMessage();
            txtMsg.setText("O processo de " + context + " foi realizado com sucesso...");
            txtMsg.setTextFill(Paint.valueOf("white"));
            txtMsg.setFont(Font.font(15));
            txtMsg.setBackground(new Background(new BackgroundFill(Paint.valueOf("green"), CornerRadii.EMPTY, Insets.EMPTY)));
            txtMsg.setGraphic(utilidades.iconSucesso());
            animateMessage();

        }

    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), txtMsg);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
    @FXML
    private Label lbNome, lbSexo, lbDate, lbCpf, lbRg,
            lbNit, lbCie, lbEstado, lbCidade , lbBairro;
    ValidacaoForms forms = ValidacaoForms.instanceOf();

    @FXML
    public void validacaoEstado(KeyEvent event) {

        if (forms.validacaoForm(txtEstado, lbEstado)) ;
    }
    @FXML
    public void validacaoEndereco(KeyEvent event) {

        if (forms.validacaoFormCarectere(txtBairro, lbBairro)) ;
    }

    @FXML
    public void validacaoCidade(KeyEvent event) {

        if (forms.validacaoForm(txtCidade, lbCidade)) ;
    }

    @FXML
    public void validarCpf(KeyEvent event) {

        if (validarMascarasCPF(txtCPF, lbCpf)) ;
    }

    @FXML
    public void validarNIT(KeyEvent event) {

        if (validarMascarasNit(txtNIT, lbNit)) ;
    }

    @FXML
    public void validarCIE(KeyEvent event) {

        if (validarMascarasCIE(txtCIE, lbCie)) ;
    }

    @FXML
    public void validarRG(KeyEvent event) {

        if (validarMascarasRG(txtRG, lbRg));
    }

    @FXML
    public void validarName(KeyEvent event) {

        if (validarNome(txtNome, lbNome)) ;

    }

    @FXML
    public void validarCombo(KeyEvent event) {

        if (validarComboBox(sexosBox, lbSexo)) ;

    }

    @FXML
    public void validarDatas(KeyEvent event) {
        try {
            if (validarDatas(txtDataNascimento, lbDate)) ;

        } catch (Exception e) {
        }

    }

    @FXML
    public void validarDatasMouse(DragEvent event) {
        try {
            if (validarDatas(txtDataNascimento, lbDate)) ;

        } catch (Exception e) {
        }

    }

    public boolean validarCampos() {
        boolean erro = false;

        if (validarNome(txtNome, lbNome)) {

            erro = true;
            System.out.println("Oi : " + erro);
        }
        if (validarComboBox(sexosBox, lbSexo)) {
            erro = true;
            System.out.println("Oi 1: " + erro);
        }
        if (validarDatas(txtDataNascimento, lbDate)) {
            erro = true;
            System.out.println("Oi 2: " + erro);
        }
        if (validarMascarasCPF(txtCPF, lbCpf)) {
            erro = true;
            System.out.println("Oi 3: " + erro);
        }
        if (validarMascarasRG(txtRG, lbRg)) {
            erro = true;
            System.out.println("Oi 4: " + erro);
        }
        if (validarMascarasCIE(txtCIE, lbCie)) {
            erro = true;
            System.out.println("Oi 5: " + erro);
        }
        if (validarMascarasNit(txtNIT, lbNit)) {
            erro = true;
            System.out.println("Oi 5: " + erro);
        }
        if (erro) {
            txtMsg.setText("Existe informações invalidas no preencimento do formulario!");
            txtMsg.setTextFill(Paint.valueOf("black"));
            txtMsg.setBackground(new Background(new BackgroundFill(Paint.valueOf("red"), CornerRadii.EMPTY, Insets.EMPTY)));
            txtMsg.setGraphic(utilidades.iconFalha());
            System.out.println("Vamos fugir...");
        }

        return !erro;

    }
    Utilidades utilidades = Utilidades.InstanceOf();

    public boolean validarComboBox(ComboBox model, Label msg) {
        boolean erro = false;
        String img;
        if (model.getSelectionModel().getSelectedIndex() == -1) {
            erro = true;
        }
        if (erro) {

            msg.setText("" + "Seleção Invalida!");
            msg.setTextFill(Paint.valueOf("red"));

            img = "erro";
        } else {
            msg.setText("" + "Seleção valida!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));

        model.requestFocus();
        return !erro;
    }

    public boolean validarDatas(DatePicker data, Label msg) {
        boolean erro = false;
        String img;
        //  java.util.Date format = FormatarDateFx.asDate(FormatarDateFx.localInserir(data));
        if (data.getValue() == null) {
            erro = true;
        }
        if (erro) {
            msg.setText("" + "Data invalida!");
            msg.setTextFill(Paint.valueOf("red"));
            img = "erro";
        } else {
            msg.setText("" + "Data valida!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));

        data.requestFocus();
        return !erro;
    }

    public boolean validarNome(TextField texto, Label msg) {
        boolean erro = false;
        String img;
        if (texto.getText().trim().equals("") || Utilidades.validarStringDigitado(texto.getText()) || Utilidades.validarcaracEspecial(texto.getText())) {
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

    public boolean validarMascarasCPF(TextField texto, Label msg) {
        boolean erro = false;
        String img;
        if (texto.getText().trim().equals("") || texto.getText().length() != 14) {
            erro = true;
        }

        if (erro) {

            msg.setText("CPF Invalido!");
            msg.setTextFill(Paint.valueOf("red"));
            img = "erro";
        } else {
            msg.setText("CPF valido!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));

        texto.requestFocus();
        return !erro;
    }

    public boolean validarMascarasRG(TextField texto, Label msg) {
        boolean erro = false;
        String img;
        if (texto.getText().trim().equals("") || texto.getText().length() != 13) {
            erro = true;
        }

        if (erro) {

            msg.setText("RG Invalido!");
            msg.setTextFill(Paint.valueOf("red"));
            img = "erro";
        } else {
            msg.setText("RG valido!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));

        texto.requestFocus();
        return !erro;
    }

    public boolean validarMascarasCIE(TextField texto, Label msg) {
        boolean erro = false;
        String img;
        if (texto.getText().trim().equals("") || texto.getText().length() != 17) {
            erro = true;
        }

        if (erro) {

            msg.setText("CIE Invalido!");
            msg.setTextFill(Paint.valueOf("red"));
            img = "erro";
        } else {
            msg.setText("CIE valido!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));

        texto.requestFocus();
        return !erro;
    }

    public boolean validarMascarasNit(TextField texto, Label msg) {
        boolean erro = false;
        String img;
        if (texto.getText().trim().equals("") || texto.getText().length() != 15) {
            erro = true;
        }
        if (erro) {
            msg.setText("NIT Invalido!");
            msg.setTextFill(Paint.valueOf("red"));
            img = "erro";
        } else {
            msg.setText("NIT valido!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));

        texto.requestFocus();
        return !erro;
    }

    public byte[] fotoImage() {
        try {

            return foto = UtilidadesImage.read(f.getAbsolutePath(), 200, 200);
        } catch (Exception e) {
            return null;
        }

    }

    @FXML
    public void testarForm(ActionEvent event) {

        System.out.println("Validaçao Form...");
    }
    String[] sexos = {"Masculino", "Feminino"};

    public void carregarSexos() {
        ObservableList<String> lista = FXCollections.observableArrayList(sexos);
        sexosBox.setItems(lista);
    }
    DaoAssociacao dao = new DaoAssociacao();
    DaoProfissao dp = new DaoProfissao();
    DaoCargo dc = new DaoCargo();

    public void carregarAssociacoes() {
        try {
            ObservableList<Associacao> lista = FXCollections.observableList(dao.listAssociations());
            associacaoBox.setItems(lista);
        } catch (Exception e) {
        }

    }

    public void carregarCargos() {
        try {
            ObservableList<Cargo> lista = FXCollections.observableList(dc.listaCargos());
            cargosBox.setItems(lista);
        } catch (Exception e) {
        }

    }

    private File f;

    @FXML
    public void carregarArquivo(ActionEvent e) {
        FileChooser file = new FileChooser();
        try {
            f = file.showOpenDialog(new Stage());
            FileInputStream stream = new FileInputStream(f);
            imageFoto.setImage(new Image(stream));

        } catch (FileNotFoundException ex) {
            System.out.println(ex.fillInStackTrace());
        }

    }

    @FXML
    public void telaCargo(ActionEvent event) {

        Dialog dialog = new TextInputDialog();
        dialog.setTitle("Ola");
        dialog.setHeaderText("Foi la!");
        dialog.setContentText("Vamus!");
        dialog.show();

    }

    public void carregarProfissoes() {
        try {
            ObservableList<Profissao> lista = FXCollections.observableList(dp.listProfessions());
            profissaoBox.setItems(lista);
        } catch (Exception e) {
        }

    }

    public void mascaraTelefoneFixo(TextField CEP) {
        CEP.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "(##) ####-####";
                    String alphaAndDigits = CEP.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (CEP.getText().length() <= mascara.length()) {
                            while (i < mascara.length()) {
                                if (quant < alphaAndDigits.length()) {
                                    if ("#".equals(mascara.substring(i, i + 1))) {
                                        resultado.append(alphaAndDigits.substring(quant, quant + 1));
                                        quant++;
                                    } else {
                                        resultado.append(mascara.substring(i, i + 1));
                                    }
                                }
                                i++;
                            }
                            CEP.setText(resultado.toString());
                        }
                        if (CEP.getText().length() > mascara.length()) {
                            CEP.setText(CEP.getText(0, mascara.length()));
                        }
                    }
                });
    }

    public void mascaraTelefoneCelular(TextField CEP) {
        CEP.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "(##) #####-####";
                    String alphaAndDigits = CEP.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (CEP.getText().length() <= mascara.length()) {
                            while (i < mascara.length()) {
                                if (quant < alphaAndDigits.length()) {
                                    if ("#".equals(mascara.substring(i, i + 1))) {
                                        resultado.append(alphaAndDigits.substring(quant, quant + 1));
                                        quant++;
                                    } else {
                                        resultado.append(mascara.substring(i, i + 1));
                                    }
                                }
                                i++;
                            }
                            CEP.setText(resultado.toString());
                        }
                        if (CEP.getText().length() > mascara.length()) {
                            CEP.setText(CEP.getText(0, mascara.length()));
                        }
                    }
                });
    }

    public void mascaraRG(TextField CEP) {
        CEP.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "##.###.###-##";
                    String alphaAndDigits = CEP.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (CEP.getText().length() <= mascara.length()) {
                            while (i < mascara.length()) {
                                if (quant < alphaAndDigits.length()) {
                                    if ("#".equals(mascara.substring(i, i + 1))) {
                                        resultado.append(alphaAndDigits.substring(quant, quant + 1));
                                        quant++;
                                    } else {
                                        resultado.append(mascara.substring(i, i + 1));
                                    }
                                }
                                i++;
                            }
                            CEP.setText(resultado.toString());
                        }
                        if (CEP.getText().length() > mascara.length()) {
                            CEP.setText(CEP.getText(0, mascara.length()));
                        }
                    }
                });
    }

    public void mascaraCPF(TextField CEP) {
        CEP.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "###.###.###-##";
                    String alphaAndDigits = CEP.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (CEP.getText().length() <= mascara.length()) {
                            while (i < mascara.length()) {
                                if (quant < alphaAndDigits.length()) {
                                    if ("#".equals(mascara.substring(i, i + 1))) {
                                        resultado.append(alphaAndDigits.substring(quant, quant + 1));
                                        quant++;
                                    } else {
                                        resultado.append(mascara.substring(i, i + 1));
                                    }
                                }
                                i++;
                            }
                            CEP.setText(resultado.toString());
                        }
                        if (CEP.getText().length() > mascara.length()) {
                            CEP.setText(CEP.getText(0, mascara.length()));
                        }
                    }
                });
    }

    @FXML
    public void testeAutomatizado() {
        txtNome.setText("Leandro cade");
        txtBairro.setText("Acupe");
        associacaoBox.getSelectionModel().select(0);
        sexosBox.getSelectionModel().select(0);
        cargosBox.getSelectionModel().select(0);
        profissaoBox.getSelectionModel().select(0);
        txtNIT.setText("7.637.832.685-2");
        txtCIE.setText("78.638.765827/65");
        txtCEP.setText("12345-000");
        txtCPF.setText("123.445.566.566-44");
        txtEstado.setText("Ba");
        //   txtMatricula.setValue(LocalDate.MAX);
        txtRG.setText("87.638.732-68");
        txtCidade.setText("Sta");
        txtNumero.setText("12");
        //  txtDataNascimento.setValue(LocalDate.MAX);
        txtCelular.setText("(87) 37835-6286");
        txtFixo.setText("(74) 6876-1487");
        txtEmail.setText("doce@gmail.com");
        txtlogin.setText("olapessoal");
        txtSenha.setText("admin");
        cfSenha.setText("admin");
        pontoReferencia.setText("Aqui mesmo!");
        pessoaReferencia.setText("Eu mesmo!");

    }

    public void mascaraCIE(TextField CEP) {
        CEP.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "##.###.######/#.#";
                    String alphaAndDigits = CEP.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (CEP.getText().length() <= mascara.length()) {
                            while (i < mascara.length()) {
                                if (quant < alphaAndDigits.length()) {
                                    if ("#".equals(mascara.substring(i, i + 1))) {
                                        resultado.append(alphaAndDigits.substring(quant, quant + 1));
                                        quant++;
                                    } else {
                                        resultado.append(mascara.substring(i, i + 1));
                                    }
                                }
                                i++;
                            }
                            CEP.setText(resultado.toString());
                        }
                        if (CEP.getText().length() > mascara.length()) {
                            CEP.setText(CEP.getText(0, mascara.length()));
                        }
                    }
                });
    }

    public void mascaraNit(TextField CEP) {
        CEP.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "#.###.###.###-#";
                    String alphaAndDigits = CEP.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (CEP.getText().length() <= mascara.length()) {
                            while (i < mascara.length()) {
                                if (quant < alphaAndDigits.length()) {
                                    if ("#".equals(mascara.substring(i, i + 1))) {
                                        resultado.append(alphaAndDigits.substring(quant, quant + 1));
                                        quant++;
                                    } else {
                                        resultado.append(mascara.substring(i, i + 1));
                                    }
                                }
                                i++;
                            }
                            CEP.setText(resultado.toString());
                        }
                        if (CEP.getText().length() > mascara.length()) {
                            CEP.setText(CEP.getText(0, mascara.length()));
                        }
                    }
                });
    }

    public void mascaraCEP(TextField CEP) {
        CEP.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "#####-###";
                    String alphaAndDigits = CEP.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (CEP.getText().length() <= mascara.length()) {
                            while (i < mascara.length()) {
                                if (quant < alphaAndDigits.length()) {
                                    if ("#".equals(mascara.substring(i, i + 1))) {
                                        resultado.append(alphaAndDigits.substring(quant, quant + 1));
                                        quant++;
                                    } else {
                                        resultado.append(mascara.substring(i, i + 1));
                                    }
                                }
                                i++;
                            }
                            CEP.setText(resultado.toString());
                        }
                        if (CEP.getText().length() > mascara.length()) {
                            CEP.setText(CEP.getText(0, mascara.length()));
                        }
                    }
                });
    }

}
