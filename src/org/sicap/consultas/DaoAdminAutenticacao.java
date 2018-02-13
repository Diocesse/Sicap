/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.consultas;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import org.sicap.generico.DaoGenerico;
import org.sicap.negocio.Administrador;

/**
 *
 * @author leandro
 */
public class DaoAdminAutenticacao extends DaoGenerico<Administrador, Serializable> {

    public Administrador acessoRestritoAdmin(String login, String senha) {
        Administrador administrador;
        try {
            Query q = getEntityManager().createNamedQuery("Administrador.AutenticacaoRestrita");
            q.setParameter("user", login);
            q.setParameter("password", senha);
            administrador = (Administrador) q.getSingleResult();
            return administrador;
        } catch (Exception e) {
            return null;

        }

    }

    public Administrador autenticacaoLogin(String login) {
        try {
            Query q = getEntityManager().createNamedQuery("Adm.Login");
            q.setParameter("user", login);
            Administrador adm =(Administrador) q.getSingleResult();
            return adm;
        } catch (Exception e) {
            System.out.println("" + e.getLocalizedMessage());
            return null;
        }

    }
     public Administrador autenticacaoPAssword(String senha) {
        try {
            Query q = getEntityManager().createNamedQuery("Adm.user");
            q.setParameter("password", senha);
            Administrador adm =(Administrador) q.getSingleResult();
            return adm;
        } catch (Exception e) {
            System.out.println("" + e.getLocalizedMessage());
            return null;
        }

    }

    public static void main(String[] args) {

        Administrador adm = new DaoAdminAutenticacao().autenticacaoPAssword("12345");

        System.out.println("Login:" + adm.getUsuario() + " Senha:" + adm.getSenha());

    }

}
