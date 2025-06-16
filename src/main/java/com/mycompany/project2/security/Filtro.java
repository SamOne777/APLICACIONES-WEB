/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class Filtro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest solicitud = (HttpServletRequest) request;
        HttpServletResponse respuesta = (HttpServletResponse) response;
        //Atributos para validacion
        HttpSession sesion = solicitud.getSession();
        String rutaSolicitud = solicitud.getRequestURI();
        String raiz = solicitud.getContextPath();

        respuesta.setHeader("Cache-Control", "sin caché, sin almacenamiento, debe revalidar");
        respuesta.setHeader("Pragma", "sin caché");
        respuesta.setDateHeader("Expira", 0);
        //Validaciones
        //1. Sesion Valida
        boolean validarSesion = ((sesion != null) && (sesion.getAttribute("usuario") != null));
        //2. Ruta de  login
        boolean validarRutaLogin = ((rutaSolicitud.equals(raiz + "/")) || (rutaSolicitud.equals(raiz + "/login.xhtml")));
        //3. Cargue contenido estatico
        boolean validarContenido = rutaSolicitud.contains("/resources/");
        if (validarSesion || validarRutaLogin || validarContenido) {
            chain.doFilter(request, response);
        } else {
            respuesta.sendRedirect(raiz);
        }
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}