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
import org.sicap.negocio.Associado;
import org.sicap.negocio.Funcionario;
import org.sicap.negocio.Pescador;

/**
 *
 * @author leandro
 */
public class DaoAssociado extends DaoGenerico<Associado, Serializable> {

    public List<Pescador> listaPescadores() {
        Query q = getEntityManager().createNamedQuery("Pescador.todosCadastrados");
        return q.getResultList();
    }

    public List<Pescador> listaPescadoresPorNome(String name) {
        Query q = getEntityManager().createNamedQuery("Pescador.ConsultarPorName");
        q.setParameter("filtrar", "%" + name + "%");
        return q.getResultList();
    }

    public List<Funcionario> listaFuncionarios() {
        Query q = getEntityManager().createNamedQuery("Funcionario.ConsultarTodos");
        return q.getResultList();
    }

    public List<Funcionario> filtrarFuncionarios(String nome) {
        Query q = getEntityManager().createNamedQuery("Funcionario.consultarPorNome");
        q.setParameter("name", "%" + nome + "%");
        return q.getResultList();
    }

    public Funcionario getFuncionario(String login, String senha) {
        Funcionario f;
        try {
            Query q = getEntityManager().createNamedQuery("Funcionario.AutenticaSistema");
            q.setParameter("login", login);
            q.setParameter("password", senha);
            f = (Funcionario) q.getSingleResult();
            return f;
        } catch (Exception e) {
            return null;
            //  System.out.println("" + e.getMessage());
        }

    }

    public Funcionario autenticaLogin(String login) {
        Funcionario f;
        try {
            Query q = getEntityManager().createNamedQuery("Funcionario.Login");
            q.setParameter("login", login);

            f = (Funcionario) q.getSingleResult();
            return f;
        } catch (Exception e) {
            return null;
            //  System.out.println("" + e.getMessage());
        }

    }

    public Funcionario autenticacaoSenha(String senha) {
        Funcionario f;
        try {
            Query q = getEntityManager().createNamedQuery("Funcionario.Senha");
            q.setParameter("password", senha);
            f = (Funcionario) q.getSingleResult();
            return f;
        } catch (Exception e) {
            return null;
            //  System.out.println("" + e.getMessage());
        }

    }

    public static void main(String[] args) {
        System.out.println(new DaoAssociado().getFuncionario("12345", "admin").getNome());
    }

}
