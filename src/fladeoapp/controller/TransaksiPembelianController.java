package fladeoapp.controller;

import fladeoapp.data.TransaksiPembelian;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    public List<TransaksiPembelian> findAllTransaksiPembelian(){
        EntityManager em = getEntityManager();
        List<TransaksiPembelian> list = new ArrayList<>();
        try {
            Query q=em.createQuery("SELECT tp FROM TransaksiPembelian tp");
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // form pembayaran
    public List<Object[]> findAllTransaksiBySupplier(String namaSupplier){
        EntityManager em = getEntityManager();
        List<Object[]> list = new ArrayList<>();
        try {
            Query q=em.createNativeQuery("SELECT tbl.No_Transaksi, tbl.No_Tanda_Terima, tbl.No_Invoice,  tbl.No_PO, tbl.Kd_Supplier, tbl.Nm_Supplier, tbl.Total_Transaksi \n" +
                "FROM\n" +
                "(\n" +
                "SELECT tr.No_Transaksi, tr.No_Tanda_Terima, tr.No_Invoice,  pb.No_PO, po.Kd_Supplier, s.Nm_Supplier, tr.Total_Transaksi\n" +
                "FROM transaksi_pembelian tr\n" +
                "INNER JOIN penerimaan_barang pb ON tr.No_Tanda_Terima = pb.No_Tanda_Terima\n" +
                "INNER JOIN purchase_order po ON pb.No_PO = po.No_PO\n" +
                "INNER JOIN supplier s ON po.Kd_Supplier = s.Kd_Supplier\n" +
                "WHERE s.Nm_Supplier = ?namaSupplier\n" +
                ") tbl\n" +
                "WHERE NOT EXISTS (\n" +
                "SELECT * FROM detail_pelunasan_pembayaran dt\n" +
                "WHERE tbl.No_Transaksi = dt.No_Transaksi\n" +
                ")");
            q.setParameter("namaSupplier", namaSupplier);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //form Pembayaran
    public List<Object[]> findDetailTransaksiByNoTrans(String noTrans){
        EntityManager em = getEntityManager();
        List<Object[]> list = new ArrayList<>();
        try {
            Query q=em.createNativeQuery("SELECT tr.No_Transaksi, tr.No_Invoice, pb.No_Tanda_Terima, pb.Tgl_Terima_Barang, pb.No_PO, sp.Nm_Supplier, po.Tgl_PO, po.Tgl_Kirim, po.Total_Qty,\n" +
                "(\n" +
                "	SELECT SUM(dpo.Harga_Beli) \n" +
                "    FROM detail_purchase_order dpo \n" +
                "    WHERE po.No_PO = dpo.No_PO\n" +
                ") totalBeli,  tr.Total_Transaksi\n" +
                "FROM  transaksi_pembelian tr \n" +
                "INNER JOIN penerimaan_barang pb ON tr.No_Tanda_Terima = pb.No_Tanda_Terima\n" +
                "INNER JOIN purchase_order po ON pb.No_PO = po.No_PO\n" +
                "INNER JOIN supplier sp ON po.Kd_Supplier = sp.Kd_Supplier\n" +
                "WHERE tr.No_Transaksi = ?noTrans");
            q.setParameter("noTrans", noTrans);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //form laporan
    public List<Object[]> findAllTransaksiPembelianByDate(Date tgl1, Date tgl2){
        EntityManager em = getEntityManager();
        List<Object[]> list = new ArrayList<>();
        try {
            Query q=em.createNativeQuery("SELECT tr.No_Transaksi, tr.No_Invoice, pb.No_Tanda_Terima, pb.Tgl_Terima_Barang, pb.No_PO, sp.Nm_Supplier, po.Tgl_PO, po.Tgl_Kirim, po.Total_Qty,\n" +
                "(\n" +
                "	SELECT SUM(dpo.Harga_Beli) \n" +
                "    FROM detail_purchase_order dpo \n" +
                "    WHERE po.No_PO = dpo.No_PO\n" +
                ") totalBeli,  tr.Total_Transaksi\n" +
                "FROM  transaksi_pembelian tr \n" +
                "INNER JOIN penerimaan_barang pb ON tr.No_Tanda_Terima = pb.No_Tanda_Terima\n" +
                "INNER JOIN purchase_order po ON pb.No_PO = po.No_PO\n" +
                "INNER JOIN supplier sp ON po.Kd_Supplier = sp.Kd_Supplier\n" +
                "WHERE pb.Tgl_Terima_Barang BETWEEN ?tgl1 AND ?tgl2");
            q.setParameter("tgl1", tgl1);
            q.setParameter("tgl2", tgl2);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // forma laporan
    public Object[] firstDateLastDate(){
        EntityManager em=getEntityManager();
        Object[] obj=new Object[2];
        try{
            Query q=em.createNativeQuery("SELECT MIN(pb.Tgl_Terima_Barang), MAX(pb.Tgl_Terima_Barang)\n" +
                "FROM  transaksi_pembelian tr \n" +
                "INNER JOIN penerimaan_barang pb ON tr.No_Tanda_Terima = pb.No_Tanda_Terima\n" +
                "INNER JOIN purchase_order po ON pb.No_PO = po.No_PO\n" +
                "INNER JOIN supplier sp ON po.Kd_Supplier = sp.Kd_Supplier");
            List<Object[]> list = q.getResultList();
            for(Object[] o : list){
                obj[0] = o[0];
                obj[1] = o[1];
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
        return obj;
    }
    
    public List<Object[]> searchTransaksiPembelian(String cari){
        EntityManager em = getEntityManager();
        List<Object[]> list = new ArrayList<>();
        try {
            Query q=em.createQuery("SELECT tp FROM TransaksiPembelian tp "
                    + "WHERE tp.noTransaksi LIKE :cari "
                    + "OR tp.noInvoice LIKE :cari "
                    + "OR tp.noTandaTerima LIKE :cari");
            q.setParameter("cari", cari);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
