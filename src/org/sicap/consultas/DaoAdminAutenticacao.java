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
            List<Administrador> adm = q.getResultList();
            administrador = adm.get(0);

        } catch (Exception e) {
            administrador = new Administrador();
            System.out.println(""+e.getLocalizedMessage());
        }
        

        return administrador;
    }

    public static void main(String[] args) {
        Administrador adm = new DaoAdminAutenticacao().acessoRestritoAdmin("admin", "admin");
        System.out.println("Login:" + adm.getUsuario() + " Senha:" + adm.getSenha());

    }

}
