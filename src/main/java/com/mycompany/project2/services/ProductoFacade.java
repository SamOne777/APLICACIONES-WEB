/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.services;

import com.mycompany.project2.entities.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_project2_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    @Override
    public List<String> findCategoriasUnicas() {
        return em.createQuery(
            "SELECT DISTINCT p.categoriaProducto FROM Producto p", String.class)
            .getResultList();
    }

    @Override
    public List<String> findEstadosUnicos() {
        return em.createQuery(
            "SELECT DISTINCT p.estadoProducto FROM Producto p WHERE p.estadoProducto IS NOT NULL", String.class)
            .getResultList();
    }

    @Override
    public List<Producto> findByEstado(String activo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

