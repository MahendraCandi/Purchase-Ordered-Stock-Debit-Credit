package fladeoapp.controller;

import fladeoapp.data.User;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UserController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public UserController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(User user){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(User user){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        User us;
        try{
            us=em.getReference(User.class, kode);
            us.getIdUser();
            em.getTransaction().begin();
            em.remove(us);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public User findUser(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(User.class, kode);
        }finally{}
    }
    
    public String nomorOtomatis(String hakAkses){
        EntityManager em=null;
        String kode="";
        String formatKode="";
        if(hakAkses.equalsIgnoreCase("Finance")){
            kode = "FIN-001";
            formatKode = "FIN-000";
        }else if(hakAkses.equalsIgnoreCase("Purchasing")){
            kode = "PUC-001";
            formatKode = "PUC-000";
        }
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT u FROM User u WHERE u.hakAkses = :hakAkses ORDER BY u.username DESC");
            q.setParameter("hakAkses", hakAkses);
            q.setMaxResults(1);
            User user=(User) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat(formatKode);
                String nomorurut = user.getUsername().substring(4);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        return kode;
    }
}
