/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fladeoapp.form;

import fladeoapp.controller.UserController;
import fladeoapp.FladeoApp;
import fladeoapp.data.User;

/**
 *
 * @author 0085
 */
public class TesMain {
    public static void main(String[] args) {
        UserController uCont = new UserController(FladeoApp.emf);
        User u = new User();
        u.setHakAkses("Finance");
        u.setNama("Player 3");
        u.setPassword("1234");
        u.setUsername(uCont.nomorOtomatis(u.getHakAkses()));
        uCont.save(u);
        
        String tes = uCont.nomorOtomatis(u.getHakAkses());
        System.out.println(tes);
    }
    
    
}
