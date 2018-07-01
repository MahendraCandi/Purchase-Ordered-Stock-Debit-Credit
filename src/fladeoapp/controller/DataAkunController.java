package fladeoapp.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import fladeoapp.data.DataAkun;
import java.text.DecimalFormat;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class DataAkunController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public DataAkunController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(DataAkun dataAkun){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(dataAkun);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(DataAkun dataAkun){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(dataAkun);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        DataAkun akun;
        try{
            akun=em.getReference(DataAkun.class, kode);
            akun.getKdAkun();
            em.getTransaction().begin();
            em.remove(akun);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public DataAkun findDataAkun(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(DataAkun.class, nama);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="akun-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT d FROM DataAkun d ORDER BY d.kdAkun DESC");
            q.setMaxResults(1);
            DataAkun dataAkun=(DataAkun) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("akun-000");
                String nomorurut = dataAkun.getKdAkun().substring(5);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
}
