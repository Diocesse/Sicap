/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.generico;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author leandro
 */
public interface  Dao<T, I extends Serializable>{
    
     public T save(T entity);

    public void remove(Class<T> classe, int pk);

    public T getById(Class<T> classe, I pk);

    public List<T> listaGenerica(Class<T> classe);

    public EntityManager getEntityManager();

    
}
