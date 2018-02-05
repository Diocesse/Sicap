/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.sample;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.sicap.negocio.Associacao;

/**
 *
 * @author leandro
 */
public class Associacao_Sample {

    private final SimpleLongProperty idAssociacaoFX;
    private final SimpleStringProperty nameFx;
    private final SimpleStringProperty enderecoFx;
   
    public Associacao_Sample(Associacao a) {

        idAssociacaoFX = new SimpleLongProperty(a.getIdAssociacao());
        nameFx = new SimpleStringProperty(a.getAssociacao());
        enderecoFx = new SimpleStringProperty(a.getEndereco());
        

    }

   
    
    
    public Long getIdAssociacaoFX() {
        return idAssociacaoFX.getValue();
    }

    public String getNameFx() {
        return nameFx.getValue();
    }

    public String getEnderecoFx() {
        return enderecoFx.getValue();
    }

    

}
