/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.services;

import com.mycompany.project2.entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_project2_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario iniciarSesion(String usuario, String password) {
        Query query = em.createQuery("SELECT U FROM Usuario U where U.correoUsuario=:usuario and U.paswordUsuario=:password and u.estadoUsuario='Activo'");
        query.setParameter("usuario", usuario);
        query.setParameter("password", password);
        try {
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
        }
        Usuario usuarioInvalido = new Usuario();
        return usuarioInvalido;
    }
}
