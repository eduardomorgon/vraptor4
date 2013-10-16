/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.teste.interceptor;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.validator.SimpleMessage;
import com.edu.teste.controller.HomeController;
import com.edu.teste.controller.dao.UsuarioDao;
import java.util.Arrays;
import javax.inject.Inject;

/**
 *
 * @author eduardo
 */
@Intercepts
public class AuthorizationInterceptor {
    
    @Inject
    private UserInfo info;
    @Inject
    private UsuarioDao dao;
    @Inject
    private Result result;
    
//    public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {
//        /**
//    	 * You can use the result even in interceptors.
//    	 */
//    	if (info.getUsuario()== null) {
//            // remember added parameters will survive one more request, when there is a redirect
//            result.include("errors", Arrays.asList(new ValidationMessage("Usuário não está logado!", "usuario")));
//            result.forwardTo(HomeController.class).login();
//    	} else {
//            //dao.refresh(info.getUsuario());
//            // continues execution
//            stack.next(method, resourceInstance);
//    	}
//    }
//
//   
//    public boolean accepts(ResourceMethod method) {
//        return !method.containsAnnotation(Public.class);
//    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {
        
    	if (info.getUsuario()== null) {
            result.include("errors", Arrays.asList(new SimpleMessage("usuario", "Usuário não está logado!")));
            result.redirectTo(HomeController.class).login();
            return;
    	} 
        stack.next();
    }

    @Accepts
    public boolean accepts(ControllerMethod method) {
        return !method.containsAnnotation(Public.class);
    }
    
}
