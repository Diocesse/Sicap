/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.negocio;

import java.io.Serializable;
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
@Table(name = "cargo")
@NamedQueries({
@NamedQuery(name = "Cargo.listarTodos",query = "SELECT c FROM Cargo c"),
@NamedQuery(name = "Cargo.consultarCargoPorNome",query = "SELECT c FROM Cargo c where c.cargo LIKE :consulta")

})
public class Cargo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Cargo")
    private long idCargo;
    @Column(name = "cargo",nullable = false,length = 200)
    private String cargo;

    public long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(long idCargo) {
        this.idCargo = idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return cargo; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
