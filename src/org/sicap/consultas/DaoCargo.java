/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.consultas;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import org.sicap.generico.DaoGenerico;
import org.sicap.negocio.Cargo;

/**
 *
 * @author leandro
 */
public class DaoCargo extends DaoGenerico<Cargo, Serializable> {

    public List<Cargo> listaCargos() {
        Query q = getEntityManager().createNamedQuery("Cargo.listarTodos");
        return q.getResultList();
    }

    public Collection<Cargo> listaCargosPorNome(String consultar) {
        Query q = getEntityManager().createNamedQuery("Cargo.consultarCargoPorNome");
        q.setParameter("consulta", "%" + consultar + "%");
        return q.getResultList();
    }

    public static void main(String[] args) {
        Collection<Cargo> cargos = new DaoCargo().listaCargosPorNome("r");
        for (Iterator<Cargo> iterator = cargos.iterator(); iterator.hasNext();) {
            Cargo c = (Cargo) iterator.next();
            System.out.println("" + c.getCargo());

        }

    }

}
