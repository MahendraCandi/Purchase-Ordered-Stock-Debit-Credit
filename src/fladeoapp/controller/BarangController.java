package fladeoapp.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import fladeoapp.data.Barang;
import java.text.DecimalFormat;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class BarangController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public BarangController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Barang barang){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(barang);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(Barang barang){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(barang);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String nama){
        EntityManager em = getEntityManager();
        Barang brg;
        try{
            brg=em.getReference(Barang.class, nama);
            brg.getArtBarang();
            em.getTransaction().begin();
            em.remove(brg);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Barang findBarang(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(Barang.class, nama);
        }finally{}
    }
}
