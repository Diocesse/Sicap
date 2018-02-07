/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.consultas;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Query;
import org.sicap.generico.DaoGenerico;
import org.sicap.negocio.Profissao;

/**
 *
 * @author leandro
 */
public class DaoProfissao extends DaoGenerico<Profissao, Serializable> {

    public List<Profissao> listProfessions() {
        Query q = getEntityManager().createNamedQuery("Profissao.listarTodos");
        return q.getResultList();
    }

    public Collection<Profissao> listProfessions(String name) {
        Query q = getEntityManager().createNamedQuery("Profissao.consultarPrissaiPorNome");
        q.setParameter("consulta", "%" + name + "%");
        return q.getResultList();
    }

    public static void main(String[] args) {
        Collection<Profissao> list = new DaoProfissao().listProfessions();
        for (Profissao profissao : list) {
            System.out.println("" + profissao.getProfissao());
        }

    }

}
