/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicap.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private Tab tabReferencia,tabAutenticacao;

    @FXML
    private DatePicker txtDataNascimento, txtMatricula;

    @FXML
    private TextArea pontoReferencia, pessoaReferencia;

    @FXML
    private ImageView imageFoto,seguranca;
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
    private PasswordField txtSenha,cfSenha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //To change body of generated methods, choose Tools | Templates.
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamTela = kit.getScreenSize();
        paneScroll.setPrefHeight(tamTela.getHeight());
        paneScroll.setPrefWidth(tamTela.getWidth());
        carregarSexos();
        System.out.println("aqui.... " + FormAssociadoController.getFuncionario());
        if (FormAssociadoController.getFuncionario() != null) {
            carregarCargos();
            tabReferencia.setDisable(true);
            txtTexto.setText("Ficha de Inscrição para Funcionario.");
            tabAutenticacao.setDisable(false);
            seguranca.setImage(new Image(getClass().getResourceAsStream("/sicap/image/seguranca.gif")));
        } else if (FormAssociadoController.getPescador() != null) {
            txtTexto.setText("Ficha de Inscrição para Associado.");
            labelCargo.setDisable(true);
            btCarg.setDisable(true);
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
    public void gravar(ActionEvent event) {

        if (FormAssociadoController.getPescador() != null) {

            Pescador pescador = new Pescador();
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
            txtMsg.setText("Associado cadastrado com sucesso.");

        }
        if (FormAssociadoController.getFuncionario() != null) {

            Funcionario funcionario = new Funcionario();
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
           txtMsg.setText("Funcionario cadastrado com sucesso.");

        }

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
    public void formProfissao(ActionEvent event){
        
        DaoProfissao dp = new DaoProfissao();
        TextInputDialog dialog = new TextInputDialog();
      
        dialog.setTitle("Form profissão");
        dialog.setHeaderText("Qual é a profissão?");
        dialog.setContentText("Digite aqui:");
        Optional<String> result =   dialog.showAndWait();
        if (result.isPresent()){
            
            Profissao p = new Profissao();
            p.setProfissao(result.get());
            dp.save(p);
           // System.out.println("Venha"+ result.get());
        }
        
       carregarProfissoes();
    }
    
    @FXML
    public void formCargo(ActionEvent event){
        
        DaoCargo dc = new DaoCargo();
        TextInputDialog dialog = new TextInputDialog();
      
        dialog.setTitle("Novos Cargos");
        dialog.setHeaderText("Qual é o Cargo?");
        dialog.setContentText("Digite aqui:");
        Optional<String> result =   dialog.showAndWait();
        if (result.isPresent()){
            
            Cargo p = new Cargo();
            p.setCargo(result.get());
            dc.save(p);
           // System.out.println("Venha"+ result.get());
        }
        
       carregarCargos();
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

    public void mascaraCIE(TextField CEP) {
        CEP.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "##.###.######/##";
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
