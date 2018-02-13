/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "funcionario")
@NamedQueries({
    @NamedQuery(name = "Funcionario.ConsultarTodos", query = "SELECT f FROM Funcionario f")
    ,
    @NamedQuery(name = "Funcionario.AutenticaSistema", query = "SELECT f FROM Funcionario f WHERE f.user LIKE :login and f.senha LIKE :password"),
     @NamedQuery(name = "Funcionario.consultarPorNome", query = "SELECT f FROM Funcionario f WHERE f.nome LIKE :name"),
     @NamedQuery(name = "Funcionario.Login",query = "SELECT f FROM Funcionario f WHERE f.user LIKE :login"),
     @NamedQuery(name = "Funcionario.Senha",query = "SELECT f FROM Funcionario f WHERE f.senha LIKE :password")

})
public class Funcionario extends Associado {

    @OneToOne
    @JoinColumn(name = "id_Cargo")
    private Cargo cargo;
    @Column(name = "usuario", nullable = false, unique = true)
    private String user;
    @Column(name = "senha", unique = true, nullable = false)
    private String senha;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

}
