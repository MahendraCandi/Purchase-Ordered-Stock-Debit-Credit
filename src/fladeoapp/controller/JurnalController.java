package fladeoapp.controller;

import fladeoapp.data.DataPerkiraan;
import fladeoapp.data.Jurnal;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class JurnalController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public JurnalController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Jurnal jurnal){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(jurnal);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(Jurnal jurnal){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(jurnal);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        Jurnal jr;
        try{
            jr=em.getReference(Jurnal.class, kode);
            jr.getNoJurnal();
            em.getTransaction().begin();
            em.remove(jr);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Jurnal findJurnal(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(Jurnal.class, kode);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="Ju-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT j FROM Jurnal j ORDER BY j.noJurnal DESC");
            q.setMaxResults(1);
            Jurnal jurnal=(Jurnal) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("Ju-000");
                String nomorurut = jurnal.getNoJurnal().substring(3);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){
            return kode;
        }
        return kode;
    }
}
