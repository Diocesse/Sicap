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
@Table(name = "administrador")
@NamedQueries({
    @NamedQuery(name = "Administrador.AutenticacaoRestrita", query = "SELECT a FROM Administrador a WHERE a.usuario LIKE :user and a.senha LIKE :password")
})
public class Administrador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Admin")
    private int idAdmin;
    @Column(name = "login", unique = true,  nullable = false)
    private String usuario;
    @Column(name = "senha", nullable = false)
    private String senha;

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Administrador(int idAdmin, String usuario, String senha) {
        this.idAdmin = idAdmin;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Administrador() {
    }

}
