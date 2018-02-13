/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.util;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author leandro
 */
public class UtilitsMascara {
     
    
   public static UtilitsMascara instanceOf(){
       return new UtilitsMascara();
   }
    
    public void mascaraCNPJ(TextField value) {
        value.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "##.###.###/####-##";
                    String alphaAndDigits = value.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (value.getText().length() <= mascara.length()) {
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
                            value.setText(resultado.toString());
                        }
                        if (value.getText().length() > mascara.length()) {
                            value.setText(value.getText(0, mascara.length()));
                        }
                    }
                });
    }

     public void mascaraFixo(TextField value) {
        value.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "(##) ####-####";
                    String alphaAndDigits = value.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (value.getText().length() <= mascara.length()) {
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
                            value.setText(resultado.toString());
                        }
                        if (value.getText().length() > mascara.length()) {
                            value.setText(value.getText(0, mascara.length()));
                        }
                    }
                });
    }

    
     public void mascaraCelular(TextField value) {
        value.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "(##) #####-####";
                    String alphaAndDigits = value.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (value.getText().length() <= mascara.length()) {
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
                            value.setText(resultado.toString());
                        }
                        if (value.getText().length() > mascara.length()) {
                            value.setText(value.getText(0, mascara.length()));
                        }
                    }
                });
    }

    
    
    
    public void mascaraOrdem(TextField value) {
        value.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "#.###/##";
                    String alphaAndDigits = value.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (value.getText().length() <= mascara.length()) {
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
                            value.setText(resultado.toString());
                        }
                        if (value.getText().length() > mascara.length()) {
                            value.setText(value.getText(0, mascara.length()));
                        }
                    }
                });
    }

    
    public void mascaraCEP(TextField value) {
        value.lengthProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
                    String mascara = "#####-###";
                    String alphaAndDigits = value.getText().replaceAll("\\D", "");
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;
                    int quant = 0;

                    if (number2.intValue() > number.intValue()) {
                        if (value.getText().length() <= mascara.length()) {
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
                            value.setText(resultado.toString());
                        }
                        if (value.getText().length() > mascara.length()) {
                            value.setText(value.getText(0, mascara.length()));
                        }
                    }
                });
    }

    
}
