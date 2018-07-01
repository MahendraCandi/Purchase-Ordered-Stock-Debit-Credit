package fladeoapp.controller;

import fladeoapp.data.TransaksiPembelian;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class TransaksiPembelianController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public TransaksiPembelianController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(TransaksiPembelian transaksiPembelian){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(transaksiPembelian);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(TransaksiPembelian transaksiPembelian){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(transaksiPembelian);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        TransaksiPembelian tr;
        try{
            tr=em.getReference(TransaksiPembelian.class, kode);
            tr.getNoTransaksi();
            em.getTransaction().begin();
            em.remove(tr);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public TransaksiPembelian findTransaksiPembelian(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(TransaksiPembelian.class, kode);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="Trans-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT tr  FROM TransaksiPembelian tr ORDER BY tr.noTransaksi DESC");
            q.setMaxResults(1);
            TransaksiPembelian transaksiPembelian=(TransaksiPembelian) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("Trans-000");
                String nomorurut = transaksiPembelian.getNoTransaksi().substring(6);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
}
