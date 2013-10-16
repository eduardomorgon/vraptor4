/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.teste.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 *
 * @author eduardo
 */
@Entity
public class Telefone implements Serializable {
    
    @EmbeddedId
    private TelefonePk telefonePk;
    
    private String tipo;
    
    @JoinColumns({
        @JoinColumn(name = "email", referencedColumnName = "email", nullable = false, insertable = false, updatable = false)
    })
    @ManyToOne
    private Usuario usuario;

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the telefonePk
     */
    public TelefonePk getTelefonePk() {
        return telefonePk;
    }

    /**
     * @param telefonePk the telefonePk to set
     */
    public void setTelefonePk(TelefonePk telefonePk) {
        this.telefonePk = telefonePk;
    }
    
}
