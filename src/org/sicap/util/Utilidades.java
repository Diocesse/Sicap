/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.util;

import java.util.Objects;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author leandro
 */
public class Utilidades {

    public ImageView iconSucesso() {
        Image image = new Image(getClass().getResourceAsStream("/sicap/image/sucesso.gif"));
        ImageView iv = new ImageView(image);
        iv.fitHeightProperty().set(20);
        iv.fitWidthProperty().set(20);
        return iv;

    }

    public ImageView iconPerson(int heigtht, int width, String context) {
        Image image = new Image(getClass().getResourceAsStream("/sicap/image/"+context+".gif"));
        ImageView iv = new ImageView(image);
        iv.fitHeightProperty().set(heigtht);
        iv.fitWidthProperty().set(width);
        return iv;

    }

    public ImageView iconSucessoFalha(String url) {
        Image image;
        if (url.equals("sucesso")) {
            image = new Image(getClass().getResourceAsStream("/sicap/image/sucesso.gif"));
        } else {
            image = new Image(getClass().getResourceAsStream("/sicap/image/erro.gif"));

        }
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

    public static Utilidades InstanceOf() {
        return new Utilidades();
    }

    public static String formatarCasaDecimal(double valor) {
        String palavra[] = String.valueOf(valor).split("\\.");
        String valorFormatado = "";
        valorFormatado = palavra[0] + "," + palavra[1];

        return valorFormatado;
    }

    public static double formatarCasaPonto(String valor) {
        try {
            String[] texto = valor.split(",");

            String mascara = texto[0] + "." + texto[1];
            return Double.valueOf(mascara);
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
            return 0;
        }
    }

    public static void utilitsMsg(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Informações:");
        a.setContentText(msg);

        //  a.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/image/sucesso.gif"))));
        a.setHeight(400);
        a.setWidth(400);
        a.show();
    }

    public static boolean verificarNumero(String valor) {
        try {

            Integer.parseInt(valor);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static int opcao;

    public static int mensagemRemocao(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        alert.getButtonTypes().setAll(btnSim, btnNao);
        //  Integer opcao;
        alert.setContentText("Deseja excluir " + name + "\n ");

        alert.showAndWait().ifPresent(b -> {
            if (b == btnSim) {
                opcao = 0;
                System.out.println("Opcao 1");
            } else if (b == btnNao) {
                System.out.println("Opcao 2");
                opcao = 1;
            }
        });
        return opcao;
    }

    public static int mensagemLogout(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        alert.getButtonTypes().setAll(btnSim, btnNao);
        //  Integer opcao;
        alert.setContentText("Opção: " + name + "\n ");

        alert.showAndWait().ifPresent(b -> {
            if (b == btnSim) {
                opcao = 0;
                System.out.println("Opcao 1");
            } else if (b == btnNao) {
                System.out.println("Opcao 2");
                opcao = 1;
            }
        });
        return opcao;
    }

    public static void main(String[] args) {
        System.out.println("Resposta: " + Utilidades.validarcaracEspecial("Leandro"));
    }

    public static boolean validarcaracEspecial(String text) {
        try {
            String[] texto = text.split(" +");
            String completo = "";
            boolean erro;
            int count = 0;
            char valide[] = {'@', '$', '#', '%', '"', '&', '*', '(', ')', '-', '_', '+', '=', '/', '?', '[', ']', '{', '}', ';', '>', '<', '.', '\\', '|', '!'};
            for (String t : texto) {
                completo += t;
            }
            for (int i = 0; i < completo.length(); i++) {
                char c = completo.charAt(i);
                for (char d : valide) {
                    if (c == d) {
                        count++;
                    }

                }

            }
            erro = count > 0;

            return erro;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validarStringDigitado(String text) {
        try {
            String[] texto = text.split(" +");
            String completo = "";
            boolean erro;
            for (String t : texto) {
                completo += t;
            }
            int count = 0;
            for (int i = 0; i < completo.length(); i++) {
                char c = completo.charAt(i);
                if (verificarNumero(String.valueOf(c))) {
                    count++;
                }
            }
            erro = count > 0;
            return erro;
        } catch (Exception e) {
            return false;
        }
    }
    private static String nome;

    public static String dialogTexto() {
        try {
            TextInputDialog dialogoNome = new TextInputDialog();
            dialogoNome.setTitle("Formulario:");
            dialogoNome.setHeaderText("Informe o valor:");
            dialogoNome.setContentText("Nome:");
            dialogoNome.showAndWait().ifPresent(v -> nome = v);
            return nome;
        } catch (Exception e) {
            return null;
        }
    }

    public static String criarValorMensalidade(String valor1, String valor2) {

        String formatar = "0.0";
        if (!valor1.equals("") && !valor2.equals("")) {
            formatar = valor1 + "," + valor2;
        } else if (valor2.equals("") || Objects.isNull(valor2)) {
            formatar = valor1 + "," + "00";
        }
        return formatar;
    }
}
