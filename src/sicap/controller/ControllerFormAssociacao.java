/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicap.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.sicap.consultas.DaoAssociacao;
import org.sicap.negocio.Associacao;
import org.sicap.sample.Associacao_Sample;
import org.sicap.util.Utilidades;
import sicap.layout.SicapAPMOM;

/**
 * FXML Controller class
 *
 * @author leandro
 */
public class ControllerFormAssociacao implements Initializable {

    @FXML
    private AnchorPane layout;

    private static Associacao associacao;

    @FXML
    private TextField txtBusca, txtName, txtCNPJ;
    @FXML
    private TableView<Associacao_Sample> tableAssociacoes;
    @FXML
    private TableColumn<Associacao_Sample, Long> idAssociacaoFX;
    @FXML
    private TableColumn<Associacao_Sample, String> nameFX;
    @FXML
    private TableColumn<Associacao_Sample, String> enderecoFX;
    @FXML
    private Label lbPesquisa;
    @FXML
    private Button btNovo, btEditar, btRemove;
    public Utilidades utilidades = Utilidades.InstanceOf();
    
    public boolean editar(ActionEvent event) {
        try {
            associacao=null;
            Associacao_Sample sample = (Associacao_Sample)tableAssociacoes.getSelectionModel().getSelectedItem();
            associacao = daoAssociacao.getById(Associacao.class, sample.getIdAssociacaoFX());
            sicap.openPrincipal("FormularioCadastroAssociacao", "Formulario de cadastro das Associações.");
            return true;
        } catch (Exception e) {
            System.out.println("" + e.getLocalizedMessage());
            return false;
        }
    }

    public void carregarButtonImg() {
        btNovo.setGraphic(utilidades.iconPerson(30, 30, "novo"));
        btEditar.setGraphic(utilidades.iconPerson(30, 30, "editar"));
        btRemove.setGraphic(utilidades.iconPerson(30, 30, "excluir"));
    }

    public static Associacao getAssociacao() {
        return associacao;
    }

    public static void setAssociacao(Associacao associacao) {
        ControllerFormAssociacao.associacao = associacao;
    }
    public static DaoAssociacao daoAssociacao = new DaoAssociacao();

    @FXML
    public void pesquisarButton(ActionEvent e) {
        atualizarTableAssociacao(txtBusca.getText());
    }

    public void atualizarTableAssociacao(String busca) {

        try {
            ImageView view = new ImageView(new Image(getClass().getResourceAsStream("/sicap/image/pesquisar.gif")));
            view.fitHeightProperty().set(20);
            view.fitWidthProperty().set(20);
            lbPesquisa.setGraphic(view);
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screen = kit.getScreenSize();

            //  tableAssociacoes.setPrefWidth(screen.width - 10);
            // tableAssociacoes.setPrefHeight(screen.height-10 );
            ObservableList<Associacao_Sample> lista = FXCollections.observableList(daoAssociacao.listaAssociacoesFX(busca));
            idAssociacaoFX.setCellValueFactory(new PropertyValueFactory<Associacao_Sample, Long>("idAssociacaoFX"));
            nameFX.setCellValueFactory(new PropertyValueFactory<Associacao_Sample, String>("nameFx"));
            enderecoFX.setCellValueFactory(new PropertyValueFactory<Associacao_Sample, String>("enderecoFx"));

            tableAssociacoes.setItems(lista);

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    @FXML
    public void filtrarAssociacoes(KeyEvent event) {
        try {
            atualizarTableAssociacao(txtBusca.getText());
        } catch (Exception e) {
        }

    }

    @FXML
    public void navegarKey(KeyEvent event) {
        try {
            Associacao_Sample sample = (Associacao_Sample) tableAssociacoes.getSelectionModel().getSelectedItem();
            associacao = daoAssociacao.getById(Associacao.class, sample.getIdAssociacaoFX());

        } catch (Exception e) {
        }
    }
    SicapAPMOM sicap = SicapAPMOM.instance();

    @FXML
    public void novaAssociacao(ActionEvent event) {
        associacao=null;
        sicap.openPrincipal("FormularioCadastroAssociacao", "Formulario de cadastro das Associações.");
    }
    long id = -2;

    @FXML
    public void nevegar(ActionEvent e) {
        Associacao_Sample sample = (Associacao_Sample) tableAssociacoes.getSelectionModel().getSelectedItem();
        tableAssociacoes.getSelectionModel().selectFirst();
        if (associacao == null && id < -1) {
            associacao = daoAssociacao.getById(Associacao.class, sample.getIdAssociacaoFX());
            id = associacao.getIdAssociacao();
        }
        // System.out.println("ola: " + id);
        tableAssociacoes.getSelectionModel().select(Integer.valueOf(String.valueOf(id)));
        Associacao a = daoAssociacao.getById(Associacao.class, id);
        txtCNPJ.setText(a.getCNPJ());
        txtName.setText(a.getAssociacao());
        if (id < daoAssociacao.listAssociations().size()) {
            id++;
        } else {
            id = 1;
            a = daoAssociacao.getById(Associacao.class, id);
            txtCNPJ.setText(a.getCNPJ());
            txtName.setText(a.getAssociacao());
            //     tableAssociacoes.getSelectionModel().select(Integer.valueOf(String.valueOf(id)));

        }
        // System.out.println("" + id);

    }

    @FXML
    public void nevegarVolta(ActionEvent e) {

        tableAssociacoes.getSelectionModel().select(Integer.valueOf(String.valueOf(id)));

        if (id > 0) {
            id--;
        } else {
            id = 1;
            //     tableAssociacoes.getSelectionModel().select(Integer.valueOf(String.valueOf(id)));

        }
        // System.out.println("" + id);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  atualizarTableAssociacao(txtBusca.getText());
        // mascaraCNPJ();
        carregarButtonImg();
        atualizarTableAssociacao(txtBusca.getText());
    }

    @FXML
    public void close(ActionEvent event) {
        ((Stage) layout.getScene().getWindow()).close();
    }

}
