package com.mycompany.project2.controller;

import com.mycompany.project2.entities.Domicilios;
import com.mycompany.project2.entities.Factura;
import com.mycompany.project2.entities.Usuario;
import com.mycompany.project2.services.DomiciliosFacadeLocal;
import com.mycompany.project2.services.FacturaFacadeLocal;
import com.mycompany.project2.services.UsuarioFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "domicilioController")
@SessionScoped
public class DomicilioController implements Serializable {

    private Domicilios dom = new Domicilios();
    
    @EJB
    private DomiciliosFacadeLocal dfl;
    
    @EJB
    private FacturaFacadeLocal ffl;
    
    @EJB
    private UsuarioFacadeLocal ufl;

    public Domicilios getDom() {
        return dom;
    }

    public void setDom(Domicilios dom) {
        this.dom = dom;
    }
    
    public List<Domicilios> obtenerDomicilios() {
        return this.dfl.findAll();
    }
    
    public List<Factura> obtenerFacturasDisponibles() {
        return this.ffl.findAll();
    }
    
    public List<Usuario> obtenerDomiciliarios() {
        return this.ufl.findByRol(3); // Rol 3 es domiciliario
    }
    
    public String crearDomicilioP1() {
        this.dom = new Domicilios();
        this.dom.setFechaDomicilio(new Date());
        return "/views/domicilio/crearactDomi.xhtml?faces-redirect=true";
    }

    public void crearDomicilioP2() {
        try {
            this.dfl.create(dom);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Domicilio asignado correctamente", ""));
            this.dom = new Domicilios();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al asignar domicilio", ""));
        }
    }
    
    public String editarDomicilioP1(Domicilios dom2) {
        this.dom = dom2;
        return "/views/domicilio/crearactDomi.xhtml?faces-redirect=true";
    }
    
    public void editarDomicilioP2() {
        try {
            this.dfl.edit(dom);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Domicilio actualizado correctamente", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar domicilio", ""));
        }
    }
    
    public void cancelarDomicilio(Domicilios dom2) {
        try {
            this.dfl.remove(dom2);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Domicilio cancelado correctamente", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cancelar domicilio", ""));
        }
    }
}