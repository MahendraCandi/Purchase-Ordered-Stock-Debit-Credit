package fladeoapp.controller;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class CetakLaporanController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public CetakLaporanController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void cetakPenerimaanBarang(Date tgl1, Date tgl2){
    EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\src\\report\\PenerimaanBarangReport.jasper";
            HashMap param = new HashMap();
            Locale local=new Locale("id", "ID");
            param.put(JRParameter.REPORT_LOCALE, local);
            param.put("tgl1", tgl1);
            param.put("tgl2", tgl2);
            JasperPrint jprint=JasperFillManager.fillReport (namafile, param,connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
            connect.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
    
    public void cetakTransaksi(Date tgl1, Date tgl2){
    EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\src\\report\\TransaksiReport.jasper";
            HashMap param = new HashMap();
            Locale local=new Locale("id", "ID");
            param.put(JRParameter.REPORT_LOCALE, local);
            param.put("tgl1", tgl1);
            param.put("tgl2", tgl2);
            JasperPrint jprint=JasperFillManager.fillReport (namafile, param,connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
            connect.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
    
    public void cetakPurchaseOrder(String po){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\src\\report\\PurchaseOrderReport.jasper";
            HashMap param = new HashMap();
            Locale local=new Locale("id", "ID");
            param.put(JRParameter.REPORT_LOCALE, local);
            param.put("noPo", po);
            JasperPrint jprint=JasperFillManager.fillReport (namafile, param,connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
            connect.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
