/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.project2.services;

import com.mycompany.project2.entities.Domicilios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface DomiciliosFacadeLocal {

    void create(Domicilios domicilios);

    void edit(Domicilios domicilios);

    void remove(Domicilios domicilios);

    Domicilios find(Object id);

    List<Domicilios> findAll();

    List<Domicilios> findRange(int[] range);

    int count();
    
}
