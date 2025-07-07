/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.project2.controller;

import com.mycompany.project2.entities.Producto;
import com.mycompany.project2.services.ProductoFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author user
 */
@Named(value = "productController")
@SessionScoped
public class ProductController implements Serializable {

    private Producto pro = new Producto();
    private List<SelectItem> listaCategoria;
    private List<SelectItem> listaEstados;

    @EJB
    private ProductoFacadeLocal pfl;

    public Producto getPro() {
        return pro;
    }

    public void setPro(Producto pro) {
        this.pro = pro;
    }

    public List<Producto> obtenerProducto() {
        return this.pfl.findAll();
    }

    public List<SelectItem> getListaCategoria() {
        try {
            if (listaCategoria == null) {
                listaCategoria = new ArrayList<>();
                // Obtener categorías únicas de la base de datos
                List<String> categoriasBD = pfl.findCategoriasUnicas();

                if (categoriasBD != null && !categoriasBD.isEmpty()) {
                    for (String categoriaStr : categoriasBD) {
                        listaCategoria.add(new SelectItem(categoriaStr, categoriaStr));
                    }
                } else {
                    // Si no hay categorías en BD, cargar todas las del enum
                    for (Producto.Categoria cat : Producto.Categoria.values()) {
                        listaCategoria.add(new SelectItem(cat.name(), cat.name()));
                    }
                }
            }
            return listaCategoria;
        } catch (Exception e) {
            // Log de error (opcional, recomendado para depuración)
            System.err.println("Error al cargar categorías: " + e.getMessage());
            e.printStackTrace();

            // Retornar valores por defecto (categorías del enum) en caso de error
            listaCategoria = new ArrayList<>();
            for (Producto.Categoria cat : Producto.Categoria.values()) {
                listaCategoria.add(new SelectItem(cat.name(), cat.name()));
            }
            return listaCategoria;
        }
    }

    public List<SelectItem> getListaEstados() {
        try {
            if (listaEstados == null) {
                listaEstados = new ArrayList<>();
                // Obtener estados únicos de la base de datos
                List<String> estadosBD = pfl.findEstadosUnicos();

                if (estadosBD != null && !estadosBD.isEmpty()) {
                    for (String estadoStr : estadosBD) {
                        listaEstados.add(new SelectItem(estadoStr, estadoStr));
                    }
                } else {
                    // Valores por defecto si no hay en BD
                    listaEstados.add(new SelectItem("Activo", "Activo"));
                    listaEstados.add(new SelectItem("Inactivo", "Inactivo"));
                }
            }
            return listaEstados;
        } catch (Exception e) {
            // Log de error (opcional, recomendado para depuración)
            System.err.println("Error al cargar estados: " + e.getMessage());

            // Retornar valores por defecto en caso de error
            listaEstados = new ArrayList<>();
            listaEstados.add(new SelectItem("Activo", "Activo"));
            listaEstados.add(new SelectItem("Inactivo", "Inactivo"));
            return listaEstados;
        }
    }

    public String crearProductoP1() {
        this.pro = new Producto();
        return "/views/producto/crearact.xhtml?faces=redirect=true";
    }

    public void crearProductoP2() {
        try {
            this.pfl.create(pro);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto registrado correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
            this.pro = new Producto();
        } catch (Exception e) {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se realizo registro", "MSG_INFO");
            fc.addMessage(null, fm);
        }
    }
    
    public String editarProductoP1(Producto pro2) {
        this.pro = pro2;
        return "/views/producto/crearact.xhtml?faces=redirect=true";
    }
    
    public void  editarProductoP2(){
    try {
    this.pfl.edit(pro);
    FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto editado correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
            
    }catch (Exception e) {
    }
        
    }
    
    public void eliminarProducto(Producto pro2){
        try {
            this.pfl.remove(pro2);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto eliminado correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
        } catch (Exception e) {
        }
    }
}