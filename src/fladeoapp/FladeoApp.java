/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fladeoapp;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 0085
 */
public class FladeoApp {
    public static EntityManagerFactory emf=Persistence.createEntityManagerFactory("FladeoAppPU");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
}
