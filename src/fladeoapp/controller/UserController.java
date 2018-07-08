package fladeoapp.controller;

import fladeoapp.data.User;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

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
    
    public void delete(int pk){
        EntityManager em = getEntityManager();
        User us;
        try{
            us=em.getReference(User.class, pk);
            us.getIdUser();
            em.getTransaction().begin();
            em.remove(us);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public User findOneUser(String username, String hakAkses){
        EntityManager em = getEntityManager();
        User us = new User();
        try {
            Query q = em.createQuery("SELECT u FROM User u WHERE u.hakAkses = :hakAkses AND u.username = :username");
            q.setParameter("hakAkses", hakAkses);
            q.setParameter("username", username);
            us = (User) q.getSingleResult();
            
        } catch (Exception e) {
            return null;
        }
        return us;
    }
    
    public DefaultTableModel findAllUser(DefaultTableModel model, String hakAkses){
        EntityManager em = getEntityManager();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Query q = em.createQuery("SELECT u FROM User u WHERE u.hakAkses = :hakAkses");
            q.setParameter("hakAkses", hakAkses);
            List<User> listUser = q.getResultList();
            for(User u : listUser){
                Object[] obj = new Object[4];
                obj[0] = u.getUsername();
                obj[1] = u.getNama();
                obj[2] = u.getHakAkses();
                obj[3] = u.getPassword();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    
    public DefaultTableModel findUserToTable(DefaultTableModel model, String hakAkses, String cari){
        EntityManager em = getEntityManager();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Query q = em.createQuery("SELECT u FROM User u WHERE u.hakAkses = :hakAkses AND u.username LIKE :cari OR u.nama LIKE :cari");
            q.setParameter("hakAkses", hakAkses);
            q.setParameter("cari", "%"+cari+"%");
            List<User> listUser = q.getResultList();
            for(User u : listUser){
                Object[] obj = new Object[4];
                obj[0] = u.getUsername();
                obj[1] = u.getNama();
                obj[2] = u.getHakAkses();
                obj[3] = u.getPassword();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
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
