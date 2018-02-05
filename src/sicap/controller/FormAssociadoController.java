/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicap.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.sicap.negocio.Funcionario;
import org.sicap.negocio.Pescador;
import sicap.layout.SicapAPMOM;

/**
 * FXML Controller class
 *
 * @author leandro
 */
public class FormAssociadoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //SicapAPMOM sicap = SicapAPMOM.instance();
    @FXML
    ToggleGroup group = new ToggleGroup();

    @FXML
    private RadioButton rbFuncionario, rbPescador;

    private void group() {
        rbFuncionario.setToggleGroup(group);
        rbPescador.setToggleGroup(group);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            setFuncionario(null);
            setPescador(null);
        // TODO
        group();
    }
    private static Pescador pescador;
    private static Funcionario funcionario;

    public static Pescador getPescador() {
        return pescador;
    }

    public static void setPescador(Pescador pescador) {
        FormAssociadoController.pescador = pescador;
    }

    public static Funcionario getFuncionario() {
        return funcionario;
    }

    public static void setFuncionario(Funcionario funcionario) {
        FormAssociadoController.funcionario = funcionario;
    }

   
    @FXML
    public void carregarForm(ActionEvent event) {

        if (rbPescador.isSelected()) {
            //sicap.openPrincipal("layoutcadastroassociado", "Formulario de cadastro dos Associados.");
            pescador = new Pescador();
            ViewPrincipalController.sicap.openPrincipal("layoutAssociadoForm", "Cadastrar Associado Pescador");
            
        } else if (rbFuncionario.isSelected()) {
            funcionario = new Funcionario();
            ViewPrincipalController.sicap.openPrincipal("layoutAssociadoForm", "Cadastrar Associado Funcionario");
            
            System.out.println("Aqui ok Func");
        }
        ((Stage) rbFuncionario.getScene().getWindow()).close();
    }
}
