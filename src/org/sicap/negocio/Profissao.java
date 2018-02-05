/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.negocio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "profissao")
@NamedQueries({
@NamedQuery(name = "Profissao.listarTodos",query = "SELECT p  FROM Profissao p"),
@NamedQuery(name = "Profissao.consultarPrissaiPorNome",query = "SELECT p FROM Profissao p where p.profissao LIKE :consulta")

})
public class Profissao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Profissao")
    private long idProfissao;
    @Column(name = "profissao")
    private String profissao;

    
    public long getIdProfissao() {
        return idProfissao;
    }

    public void setIdProfissao(long idProfissao) {
        this.idProfissao = idProfissao;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    @Override
    public String toString() {
        return profissao; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
