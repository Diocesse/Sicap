/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.consultas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.sicap.generico.DaoGenerico;
import org.sicap.negocio.Associacao;
import org.sicap.sample.Associacao_Sample;

/**
 *
 * @author leandro
 */
public class DaoAssociacao extends DaoGenerico<Associacao, Serializable> {

    public List<Associacao> listAssociations() {

        try {
            Query q = getEntityManager().createNamedQuery("Associacao.todasAssociacoes");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Associacao> listAssociations(String name) {
        try {
            Query q = getEntityManager().createNamedQuery("Associacao.todasPorCNPJNome");
            q.setParameter("filtro", "%" + name + "%");
            q.setParameter("filtrarCNPJ", name);

            return q.getResultList();
        } catch (Exception e) {

            return null;
        }
    }

    public static void main(String[] args) {
        List<Associacao> lista = new DaoAssociacao().listAssociations("");
        System.out.println(lista);
        for (Associacao a : lista) {
            System.out.println("Name: " + a.getCNPJ());
        }
    }

    public List<Associacao_Sample> listaAssociacoesFX(String nome) {

        List<Associacao_Sample> lista = new ArrayList<>();
        listAssociations(nome).forEach((p) -> {
            lista.add(new Associacao_Sample(p));
        });
        return lista;
    }

}
