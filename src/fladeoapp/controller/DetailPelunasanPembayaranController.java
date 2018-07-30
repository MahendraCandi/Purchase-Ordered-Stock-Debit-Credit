package fladeoapp.controller;

import fladeoapp.data.DetailPelunasanPembayaran;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class DetailPelunasanPembayaranController implements  Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public DetailPelunasanPembayaranController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(DetailPelunasanPembayaran pembayaranDetail){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pembayaranDetail);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(DetailPelunasanPembayaran pembayaranDetail){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(pembayaranDetail);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        DetailPelunasanPembayaran pp;
        try{
            pp=em.getReference(DetailPelunasanPembayaran.class, kode);
            pp.getId();
            em.getTransaction().begin();
            em.remove(pp);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public DetailPelunasanPembayaran findDetailPelunasanPembayaran(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(DetailPelunasanPembayaran.class, kode);
        }finally{}
    }
    
    public List<Object[]> findDetailPembayaran(String noBayar){
        EntityManager em = getEntityManager();
        List<Object[]> list = new ArrayList<>();
        try {
            Query q=em.createNativeQuery("SELECT dpp.No_Transaksi, pb.No_Tanda_Terima, po.No_PO, s.Nm_Supplier, po.Total_Qty, \n" +
                "(SELECT SUM(dpo.Harga_Beli) FROM detail_purchase_order dpo WHERE po.No_PO = dpo.No_PO) totalBeli,\n" +
                "tp.Total_Transaksi\n" +
                "FROM detail_pelunasan_pembayaran dpp\n" +
                "LEFT JOIN transaksi_pembelian tp ON dpp.No_Transaksi = tp.No_Transaksi\n" +
                "LEFT JOIN penerimaan_barang pb ON tp.No_Tanda_Terima = pb.No_Tanda_Terima\n" +
                "LEFT JOIN purchase_order po ON pb.No_PO = po.No_PO\n" +
                "LEFT JOIN supplier s ON po.Kd_Supplier = s.Kd_Supplier\n" +
                "WHERE dpp.No_Pembayaran = ?noBayar");
            q.setParameter("noBayar", noBayar);
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /*
    SELECT dpp.No_Pembayaran, dpp.No_Transaksi, pb.No_Tanda_Terima, po.No_PO, s.Nm_Supplier, po.Total_Qty, 
(SELECT SUM(dpo.Harga_Beli) FROM detail_purchase_order dpo WHERE po.No_PO = dpo.No_PO) totalBeli,
tp.Total_Transaksi
FROM detail_pelunasan_pembayaran dpp
LEFT JOIN transaksi_pembelian tp ON dpp.No_Transaksi = tp.No_Transaksi
LEFT JOIN penerimaan_barang pb ON tp.No_Tanda_Terima = pb.No_Tanda_Terima
LEFT JOIN purchase_order po ON pb.No_PO = po.No_PO
LEFT JOIN supplier s ON po.Kd_Supplier = s.Kd_Supplier
WHERE dpp.No_Pembayaran = 'Byr-003'
    */
}
