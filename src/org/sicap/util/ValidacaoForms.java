package org.sicap.util;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class ValidacaoForms {

    public static ValidacaoForms instanceOf() {
        return new ValidacaoForms();
    }
    public Utilidades utilidades = Utilidades.InstanceOf();

    public boolean validacaoTextNumber(TextField texto, Label msg) {
        boolean erro = false;
        String img;
        if (texto.getText().trim().equals("") || Utilidades.validarStringDigitado(texto.getText()) ) {
            erro = true;
        }
        if (erro) {
            msg.setText("" + "Invalido!");
            msg.setTextFill(Paint.valueOf("red"));
            texto.setPromptText("" + " A informação está Invalido!");
            img = "erro";
        } else {
            msg.setText("" + " Valido!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));
        texto.requestFocus();
        return !erro;
    }

    
    
    public boolean validacaoForm(TextField texto, Label msg) {
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
            msg.setText("" + " Valido!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));
        texto.requestFocus();
        return !erro;
    }

    public boolean validacaoFormCarectere(TextField texto, Label msg) {
        boolean erro = false;
        String img;
        if (texto.getText().trim().equals("")  || Utilidades.validarcaracEspecial(texto.getText())) {
            erro = true;
        }
        if (erro) {
            msg.setText("" + "Invalido!");
            msg.setTextFill(Paint.valueOf("red"));
            texto.setPromptText("" + " A informação está Invalido!");
            img = "erro";
        } else {
            msg.setText("" + " Valido!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));
        texto.requestFocus();
        return !erro;
    }
public boolean validacaoFormText(TextField texto, Label msg) {
        boolean erro = false;
        String img;
        if (texto.getText().trim().equals("")) {
            erro = true;
        }
        if (erro) {
            msg.setText("" + "Invalido!");
            msg.setTextFill(Paint.valueOf("red"));
            texto.setPromptText("" + " A informação está Invalido!");
            img = "erro";
        } else {
            msg.setText("" + " Valido!");
            msg.setTextFill(Paint.valueOf("green"));
            img = "sucesso";
        }
        msg.setGraphic(utilidades.iconSucessoFalha(img));
        texto.requestFocus();
        return !erro;
    }

}
