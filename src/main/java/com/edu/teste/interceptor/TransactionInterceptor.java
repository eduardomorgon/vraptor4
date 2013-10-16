/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.teste.interceptor;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.http.MutableResponse;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author eduardo
 */
@Intercepts
public class TransactionInterceptor {

    @Inject
    private EntityManager manager;
    @Inject
    private Validator validator;
    @Inject
    private MutableResponse response;

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {

        addRedirectListener();
        EntityTransaction transaction = null;
        try {
            transaction = manager.getTransaction();
            transaction.begin();
            stack.next();
            commit(transaction);
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    private void commit(EntityTransaction transaction) {
        if (!validator.hasErrors() && transaction.isActive()) {
            transaction.commit();
        }
    }

    private void addRedirectListener() {
        response.addRedirectListener(new MutableResponse.RedirectListener() {

            @Override
            public void beforeRedirect() {
                commit(manager.getTransaction());
            }
        });
    }
}
