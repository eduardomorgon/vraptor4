/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.teste.controller.dao;

import java.util.Collection;

/**
 *
 * @author eduardo
 */
public interface GenericDao<T> {
    
    Collection<T> all();

    T find(T entity);

    void remove(T entity);

    void save(T entity);
    
    void refresh(T entity);
    
    Boolean exists(T entity);
    
}
