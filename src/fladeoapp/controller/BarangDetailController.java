package fladeoapp.controller;

import fladeoapp.data.BarangDetail;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class BarangDetailController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public BarangDetailController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(BarangDetail barang){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(barang);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(BarangDetail barang){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(barang);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String nama){
        EntityManager em = getEntityManager();
        BarangDetail brg;
        try{
            brg=em.getReference(BarangDetail.class, nama);
            brg.getArtBarang();
            em.getTransaction().begin();
            em.remove(brg);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public BarangDetail findBarangDetail(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(BarangDetail.class, nama);
        }finally{}
    }
}
