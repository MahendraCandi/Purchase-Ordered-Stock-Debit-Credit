package fladeoapp.controller;

import fladeoapp.data.Supplier;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class SupplierController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public SupplierController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Supplier supplier){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(supplier);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(Supplier supplier){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(supplier);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        Supplier sp;
        try{
            sp=em.getReference(Supplier.class, kode);
            sp.getKdSupplier();
            em.getTransaction().begin();
            em.remove(sp);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Supplier findSupplier(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(Supplier.class, kode);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="sup-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT s FROM Supplier s ORDER BY s.kdSupplier DESC");
            q.setMaxResults(1);
            Supplier supplier=(Supplier) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("sup-000");
                String nomorurut = supplier.getKdSupplier().substring(4);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
}
