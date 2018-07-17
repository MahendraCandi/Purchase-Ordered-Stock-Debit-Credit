package fladeoapp.controller;

import fladeoapp.data.Supplier;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

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
    
    public DefaultTableModel findAllSupplier(DefaultTableModel model){
        EntityManager em = getEntityManager();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Query q = em.createQuery("SELECT s FROM Supplier s");
            List<Supplier> listAkun = q.getResultList();
            for(Supplier supplier : listAkun){
                Object[] obj = new Object[5];
                obj[0] = supplier.getKdSupplier();
                obj[1] = supplier.getNmSupplier();
                obj[2] = supplier.getTelepon();
                obj[3] = supplier.getKota();
                obj[4] = supplier.getAlamat();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    
    
    public DefaultTableModel searchSupplier(DefaultTableModel model, String cari ){
        EntityManager em = getEntityManager();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Query q = em.createQuery("SELECT s FROM Supplier s "
                    + "WHERE s.kdSupplier LIKE :cari "
                    + "OR s.nmSupplier LIKE :cari "
                    + "OR s.telepon LIKE :cari "
                    + "OR s.alamat LIKE :cari "
                    + "OR s.kota LIKE :cari");
            q.setParameter("cari", "%"+cari+"%");
            List<Supplier> listAkun = q.getResultList();
            for(Supplier supplier : listAkun){
                Object[] obj = new Object[5];
                obj[0] = supplier.getKdSupplier();
                obj[1] = supplier.getNmSupplier();
                obj[2] = supplier.getTelepon();
                obj[3] = supplier.getKota();
                obj[4] = supplier.getAlamat();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
   
    public List<Supplier> findSupplierToList(){
        EntityManager em = getEntityManager();
        List<Supplier> listSupplier = new ArrayList<>();
        try {
            Query q = em.createQuery("SELECT s From Supplier s");
            listSupplier = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSupplier;
    }
    
    public Supplier findSupplierByName(String namaSupplier){
        EntityManager em = getEntityManager();
        Supplier supplier = new Supplier();
        try {
            Query q=em.createQuery("SELECT s FROM Supplier s WHERE s.nmSupplier = :namaSupplier");
            q.setParameter("namaSupplier", namaSupplier);
            supplier = (Supplier) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return supplier;
    }
    
    public String nomorOtomatis(){
        String kode="SUP-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT s FROM Supplier s ORDER BY s.kdSupplier DESC");
            q.setMaxResults(1);
            Supplier supplier=(Supplier) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("SUP-000");
                String nomorurut = supplier.getKdSupplier().substring(4);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
}
