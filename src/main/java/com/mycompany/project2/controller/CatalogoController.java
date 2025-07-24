package com.mycompany.project2.controller;

import com.mycompany.project2.entities.Producto;
import com.mycompany.project2.services.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "catalogoController")
@SessionScoped
public class CatalogoController implements Serializable {
    
    @EJB
    private ProductoFacadeLocal productoFacade;
    
    private Producto productoSeleccionado = new Producto();
    
    public List<Producto> listarProductos() {
        return productoFacade.findAll();
    }
    
    public List<Producto> listarProductosActivos() {
        return productoFacade.findByEstado("Activo");
    }
    
    public String prepararNuevoProducto() {
        productoSeleccionado = new Producto();
        return "crearactCatalogo?faces-redirect=true";
    }
    
    public String prepararEditarProducto(Producto producto) {
        productoSeleccionado = producto;
        return "crearactCatalogo?faces-redirect=true";
    }
    
    public String guardarProducto() {
        productoFacade.create(productoSeleccionado);
        return "indexCatalogo?faces-redirect=true";
    }
    
    public String actualizarProducto() {
        productoFacade.edit(productoSeleccionado);
        return "indexCatalogo?faces-redirect=true";
    }
    
    public void eliminarProducto(Producto producto) {
        productoFacade.remove(producto);
    }
    
    // Getters y Setters
}