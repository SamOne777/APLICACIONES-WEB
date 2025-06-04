/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.project2.controller;

import com.mycompany.project2.entities.Producto;
import com.mycompany.project2.services.ProductoFacadeLocal;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author user
 */
@Named(value = "productController")
@ViewScoped
public class ProductController implements Serializable {

   private Producto pro = new Producto();
@EJB
private ProductoFacadeLocal pfl; //Objecto para consultar datos

    public Producto getPro() {
        return pro;
    }

    public void setPro(Producto pro) {
        this.pro = pro;
    }
   
   public List<Producto> obtenerProducto(){   //Metodo que retorna todos los registros
   return this.pfl.findAll();
   }
    
    public ProductController() {
    }
    
}
