/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "pescador")
@NamedQueries({
@NamedQuery(name = "Pescador.todosCadastrados",query = "SELECT p FROM Pescador p"),
@NamedQuery(name = "Pescador.ConsultarPorName",query = "SELECT p FROM Pescador p WHERE p.nome like :filtrar")
    
})
public class Pescador extends Associado{
   
    @Column(name = "ponto_Referencia", nullable = false, length = 400)
    private String pontoReferencia;    
    @Column(name = "referencia", nullable = false, length = 255)
    private String referencia;

     public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
}
