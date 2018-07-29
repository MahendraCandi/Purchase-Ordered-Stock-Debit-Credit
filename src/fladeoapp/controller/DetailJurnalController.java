package fladeoapp.controller;

import fladeoapp.data.DetailJurnal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class DetailJurnalController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public DetailJurnalController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(DetailJurnal jurnal){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(jurnal);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(DetailJurnal jurnal){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(jurnal);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        DetailJurnal jr;
        try{
            jr=em.getReference(DetailJurnal.class, kode);
            jr.getId();
            em.getTransaction().begin();
            em.remove(jr);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public DetailJurnal findDetailJurnal(int kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(DetailJurnal.class, kode);
        }finally{}
    }
    
    public List<Object[]> findDetailToListByKode(String noJurnal){
        EntityManager em = getEntityManager();
        List<Object[]> list = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT dj, dp.nmPerkiraan FROM DetailJurnal dj, DataPerkiraan dp WHERE dj.kdPerkiraan = dp.kdPerkiraan AND dj.noJurnal = :noJurnal");
            q.setParameter("noJurnal", noJurnal);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
