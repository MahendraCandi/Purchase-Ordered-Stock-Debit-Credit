package fladeoapp.controller;

import fladeoapp.data.PenerimaanBarang;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PenerimaanBarangController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public PenerimaanBarangController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(PenerimaanBarang penerimaanBarang){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(penerimaanBarang);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(PenerimaanBarang penerimaanBarang){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(penerimaanBarang);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        PenerimaanBarang pb;
        try{
            pb=em.getReference(PenerimaanBarang.class, kode);
            pb.getNoTandaTerima();
            em.getTransaction().begin();
            em.remove(pb);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public PenerimaanBarang findPenerimaanBarang(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(PenerimaanBarang.class, kode);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="NTR-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT pb FROM PenerimaanBarang pb ORDER BY pb.noTandaTerima DESC");
            q.setMaxResults(1);
            PenerimaanBarang penerimaanBarang=(PenerimaanBarang) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("NTR-000");
                String nomorurut = penerimaanBarang.getNoTandaTerima().substring(4);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
}
