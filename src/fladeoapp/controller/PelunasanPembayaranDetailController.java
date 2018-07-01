package fladeoapp.controller;

import fladeoapp.data.PelunasanPembayaranDetail;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PelunasanPembayaranDetailController implements  Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public PelunasanPembayaranDetailController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(PelunasanPembayaranDetail pembayaranDetail){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pembayaranDetail);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(PelunasanPembayaranDetail pembayaranDetail){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(pembayaranDetail);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        PelunasanPembayaranDetail pp;
        try{
            pp=em.getReference(PelunasanPembayaranDetail.class, kode);
            pp.getId();
            em.getTransaction().begin();
            em.remove(pp);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public PelunasanPembayaranDetail findPelunasanPembayaranDetail(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(PelunasanPembayaranDetail.class, kode);
        }finally{}
    }
}
