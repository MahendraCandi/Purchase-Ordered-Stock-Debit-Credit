package fladeoapp.controller;

import fladeoapp.data.PenerimaanBarang;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
    public List<Object[]> findAllPenerimaanBarang(Date tgl1, Date tgl2){
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
                "INNER JOIN supplier s ON po.Kd_Supplier = s.Kd_Supplier "
                    + "WHERE pb.Tgl_Terima_Barang BETWEEN ?tgl1 AND ?tgl2");
            q.setParameter("tgl1", tgl1);
            q.setParameter("tgl2", tgl2);
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
    
    public Object[] firstDateLastDate(){
        EntityManager em=getEntityManager();
        Object[] obj=new Object[2];
        try{
            Query q = em.createQuery("SELECT MIN(pb.tglTerimaBarang) AS MinTgl, MAX(pb.tglTerimaBarang) AS MaxTgl FROM PenerimaanBarang pb");
            List<Object[]> list = q.getResultList();
            for(Object[] o : list){
                obj[0] = o[0];
                obj[1] = o[1];
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return obj;
    }
    
    //form transaksi
    public List<Object[]> findAllNomorNotExistInTransaksiPembelian(){
        EntityManager em = getEntityManager();
        List<Object[]> list = new ArrayList<>();
        try {
            Query q = em.createNativeQuery("SELECT * FROM penerimaan_barang pb\n" +
                "WHERE NOT EXISTS (\n" +
                "	SELECT * FROM transaksi_pembelian tp\n" +
                "    WHERE pb.No_Tanda_Terima = tp.No_Tanda_Terima\n" +
                ")");
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // form transaksi
    public List<Object[]> findTotalPenerimaanBarang(String nomor){
        EntityManager em = getEntityManager();
        List<Object[]> list = new ArrayList<>();
        try {
            Query q=em.createNativeQuery("SELECT pb.No_Tanda_Terima, pb.Tgl_Terima_Barang, pb.No_PO, sp.Nm_Supplier, po.Tgl_PO, po.Tgl_Kirim, po.Total_Qty, \n" +
                "(\n" +
                "    SELECT SUM(dpo.Harga_Beli) \n" +
                "    FROM detail_purchase_order dpo\n" +
                "    WHERE po.No_PO = dpo.No_PO\n" +
                ") totalBeli,\n" +
                "(\n" +
                "    SELECT SUM(dpo.Qty_Order * dpo.Harga_Beli) \n" +
                "    FROM detail_purchase_order dpo\n" +
                "    WHERE po.No_PO = dpo.No_PO\n" +
                ") totalPerPO\n" +
                "FROM penerimaan_barang pb\n" +
                "INNER JOIN purchase_order po ON pb.No_PO = po.No_PO\n" +
                "INNER JOIN supplier sp ON po.Kd_Supplier = sp.Kd_Supplier\n" +
                "WHERE pb.No_Tanda_Terima = ?nomor");
            q.setParameter("nomor", nomor);
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