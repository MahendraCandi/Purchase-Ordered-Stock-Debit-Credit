/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fladeoapp.form;

import fladeoapp.controller.UserController;
import fladeoapp.FladeoApp;
import fladeoapp.controller.BarangController;
import fladeoapp.controller.PenerimaanBarangController;
import fladeoapp.data.User;
import fladeoapp.data.Barang;
import fladeoapp.data.PenerimaanBarang;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 0085
 */
public class TesMain {
    public static void main(String[] args) {
        UserController uCont = new UserController(FladeoApp.emf);
        BarangController bCont = new BarangController(FladeoApp.emf);
        PenerimaanBarangController pbCont = new PenerimaanBarangController(FladeoApp.emf);
        User u = new User();
        Barang b = new Barang();
        PenerimaanBarang pb = new PenerimaanBarang();

    }
    
    
}
