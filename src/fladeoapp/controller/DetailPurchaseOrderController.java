package fladeoapp.controller;

import fladeoapp.data.DetailPurchaseOrder;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class DetailPurchaseOrderController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public DetailPurchaseOrderController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(DetailPurchaseOrder jurnal){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(jurnal);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(DetailPurchaseOrder jurnal){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(jurnal);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        DetailPurchaseOrder pod;
        try{
            pod=em.getReference(DetailPurchaseOrder.class, kode);
            pod.getId();
            em.getTransaction().begin();
            em.remove(pod);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public DetailPurchaseOrder findDetailPurchaseOrder(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(DetailPurchaseOrder.class, kode);
        }finally{}
    }
}
