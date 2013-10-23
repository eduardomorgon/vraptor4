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
import com.edu.teste.model.TelefonePk;
import com.edu.teste.model.Usuario;
import com.google.common.hash.Hashing;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

/**
 *
 * @author eduardo
 */
@Controller
@ConversationScoped
public class UsuarioController implements Serializable {
    
    @Inject
    private Result result;
    @Inject
    private UsuarioDao dao;
    @Inject
    private Conversation conversation;
    private List<Usuario> listUsuarios;
    private Usuario usuario;
    
    @Get("/usuario")
    public void listagem() {
        conversation.begin();
        listUsuarios = (List<Usuario>) dao.all();
        result.include("listUsuario", listUsuarios);
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
        conversation.end();
        result.redirectTo(this).listagem();
    }
    
    @Get("/usuario/novo")
    public void form(Usuario usuario){
        result.include("usuario", usuario);
    }
    
    @Put("/usuario")
    public void editar(Usuario usuario) {
        Usuario entity = dao.find(usuario);
        this.usuario = entity;
        this.usuario.setTelefones(new ArrayList<Telefone>());
        Telefone t = new Telefone();
        t.setTelefonePk(new TelefonePk());
        t.setTipo("rrrr");
        t.getTelefonePk().setEmail(this.usuario.getEmail());
        this.usuario.getTelefones().add(t);
        result.include("cid", conversation.getId());
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
