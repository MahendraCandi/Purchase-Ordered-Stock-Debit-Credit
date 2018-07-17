package fladeoapp.controller;

import fladeoapp.data.PenerimaanBarang;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PenerimaanBarangController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public PenerimaanBarangController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(PenerimaanBarang penerimaanBarang){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(penerimaanBarang);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(PenerimaanBarang penerimaanBarang){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(penerimaanBarang);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        PenerimaanBarang pb;
        try{
            pb=em.getReference(PenerimaanBarang.class, kode);
            pb.getNoTandaTerima();
            em.getTransaction().begin();
            em.remove(pb);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public PenerimaanBarang findPenerimaanBarang(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(PenerimaanBarang.class, kode);
        }finally{}
    }
    
    public List<Object[]> findAllPenerimaanBarang(){
        EntityManager em = getEntityManager();
        List<Object[]> list = new ArrayList<>();
        try {
            Query q=em.createNativeQuery("SELECT \n" +
                "pb.No_Tanda_Terima, \n" +
                "pb.Tgl_Terima_Barang,	\n" +
                "pb.No_PO, \n" +
                "po.Tgl_PO,\n" +
                "po.Tgl_Kirim,\n" +
                "s.Nm_Supplier,\n" +
                "po.Total_Qty,\n" +
                "po.Username\n" +
                "FROM penerimaan_barang pb\n" +
                "INNER JOIN purchase_order po ON pb.No_PO = po.No_PO\n" +
                "INNER JOIN supplier s ON po.Kd_Supplier = s.Kd_Supplier");
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Object[]> searchPenerimaanBarang(String cari){
        EntityManager em = getEntityManager();
        List<Object[]> list = new ArrayList<>();
        try {
            Query q=em.createNativeQuery("SELECT \n" +
                "pb.No_Tanda_Terima, \n" +
                "pb.Tgl_Terima_Barang,	\n" +
                "pb.No_PO, \n" +
                "po.Tgl_PO,\n" +
                "po.Tgl_Kirim,\n" +
                "s.Nm_Supplier,\n" +
                "po.Total_Qty,\n" +
                "po.Username\n" +
                "FROM penerimaan_barang pb\n" +
                "INNER JOIN purchase_order po ON pb.No_PO = po.No_PO\n" +
                "INNER JOIN supplier s ON po.Kd_Supplier = s.Kd_Supplier\n" +
                "WHERE pb.No_Tanda_Terima LIKE ?cari \n" +
                "OR pb.Tgl_Terima_Barang LIKE ?cari	\n" +
                "OR pb.No_PO LIKE ?cari\n" +
                "OR s.Nm_Supplier LIKE ?cari\n" +
                "OR po.Username LIKE ?cari");
            q.setParameter("cari", "%"+cari+"%");
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String nomorOtomatis(){
        String kode="00001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT pb FROM PenerimaanBarang pb ORDER BY pb.noTandaTerima DESC");
            q.setMaxResults(1);
            PenerimaanBarang penerimaanBarang=(PenerimaanBarang) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("00000");
                String nomorurut = penerimaanBarang.getNoTandaTerima();
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
}