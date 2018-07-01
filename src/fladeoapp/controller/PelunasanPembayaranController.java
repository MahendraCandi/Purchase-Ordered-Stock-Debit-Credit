package fladeoapp.controller;

import fladeoapp.data.PelunasanPembayaran;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PelunasanPembayaranController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public PelunasanPembayaranController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(PelunasanPembayaran pelunasanPembayaran){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pelunasanPembayaran);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(PelunasanPembayaran pelunasanPembayaran){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(pelunasanPembayaran);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        PelunasanPembayaran pp;
        try{
            pp=em.getReference(PelunasanPembayaran.class, kode);
            pp.getNoPembayaran();
            em.getTransaction().begin();
            em.remove(pp);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public PelunasanPembayaran findPelunasanPembayaran(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(PelunasanPembayaran.class, kode);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="byr-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT pp FROM PelunasanPembayaran pp ORDER BY pp.noPembayaran DESC");
            q.setMaxResults(1);
            PelunasanPembayaran pelunasanPembayaran=(PelunasanPembayaran) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("byr-000");
                String nomorurut = pelunasanPembayaran.getNoPembayaran().substring(4);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
}
