/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.teste.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import com.edu.teste.controller.dao.UsuarioDao;
import com.edu.teste.model.Telefone;
import com.edu.teste.model.Usuario;
import com.google.common.hash.Hashing;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 *
 * @author eduardo
 */
@Controller
public class UsuarioController {
    
    @Inject
    private Result result;
    @Inject
    private UsuarioDao dao;
    
    @Get("/usuario")
    public void listagem() {
        result.include("listUsuario", dao.all());
    }
    
    @Post("/usuario")
    public void salvar(Usuario usuario){
        usuario.setSenha(Hashing.md5().hashString(usuario.getSenha()).toString());
//        for (Telefone tel : usuario.getTelefones()) {
//            if(tel.getTelefonePk().getEmail() == null){
//                tel.getTelefonePk().setEmail(usuario.getEmail());
//            }
//        }
        dao.save(usuario);
        result.redirectTo(this).listagem();
    }
    
    @Get("/usuario/novo")
    public void form(Usuario usuario){
        result.include("usuario", usuario);
    }
    
    @Put("/usuario")
    public void editar(Usuario usuario) {
        Usuario entity = dao.find(usuario);
        result.redirectTo(this).form(entity);
    }
    
    @Delete("/usuario")
    public void excluir(Usuario usuario){
        Usuario entity = dao.find(usuario);
        dao.remove(entity);
        result.redirectTo(this).listagem();
    }
    
    @Get("/usuario/usuarios.json")
    public void loadJson(){
        result.use(Results.json()).from(dao.all()).exclude("senha").serialize();
    }
    
    @Post("/usuario/ajax")
    public void salvarAjax(Usuario usuario){
        usuario.setSenha(Hashing.md5().hashString(usuario.getSenha()).toString());
        dao.save(usuario);
        result.use(Results.status()).ok();
    }
    
}
