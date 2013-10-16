/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.teste.controller.dao;

import com.edu.teste.model.Usuario;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/**
 *
 * @author eduardo
 */
@RequestScoped
public class UsuarioDao implements GenericDao<Usuario>{
    
    @Inject
    private EntityManager em;

    @Override
    public Collection<Usuario> all() {
        return em.createQuery("SELECT u FROM Usuario u WHERE u.root <> 'S' ORDER BY u.email").getResultList();
    }

    @Override
    public Usuario find(Usuario entity) {
        return em.find(Usuario.class, entity.getId());
    }

    @Override
    public void remove(Usuario entity) {
        em.remove(entity);
    }

    @Override
    public void save(Usuario entity) {
//        em.getTransaction().begin();
        if(entity.getId() != null){
            em.merge(entity);
        }else{
            em.persist(entity);
        }
//        em.getTransaction().commit();
    }

    @Override
    public Boolean exists(Usuario entity) {
        
        Usuario u;
        try {
            u = (Usuario) em.find(Usuario.class, entity.getId());
                    
            } catch (NoResultException e) {
            return false;
        }
        return true;
    }
    
    public Usuario findByLogin(String user, String password) {
        
        try {
            return (Usuario) em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
                    .setParameter("email", user)
                    .setParameter("senha", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        
    }

    @Override
    public void refresh(Usuario entity) {
        em.refresh(entity);
    }
    
}
