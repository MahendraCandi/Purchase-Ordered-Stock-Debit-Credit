package fladeoapp.controller;

import fladeoapp.data.PurchaseOrder;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PurchaseOrderController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public PurchaseOrderController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(PurchaseOrder purchaseOrder){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(purchaseOrder);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(PurchaseOrder purchaseOrder){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(purchaseOrder);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        PurchaseOrder po;
        try{
            po=em.getReference(PurchaseOrder.class, kode);
            po.getNoPO();
            em.getTransaction().begin();
            em.remove(po);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public PurchaseOrder findPurchaseOrder(String kode){
        EntityManager em=getEntityManager();
        try{
            return em.find(PurchaseOrder.class, kode);
        }finally{}
    }
    
    public List<Object[]> findAllPurchaseOrder(){
        EntityManager em=getEntityManager();
        List<Object[]> listPO = new ArrayList<>();
        try {
            Query q=em.createNativeQuery("SELECT purchase_order.*, supplier.Nm_Supplier \n" +
                "FROM `purchase_order`\n" +
                "INNER JOIN supplier ON purchase_order.Kd_Supplier = supplier.Kd_Supplier");
            listPO = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPO;
    }
    
    public List<Object[]> searchPurchaseOrder(String cari){
        EntityManager em=getEntityManager();
        List<Object[]> listPO = new ArrayList<>();
        try {
            Query q=em.createNativeQuery("SELECT purchase_order.*, supplier.Nm_Supplier \n" +
                "FROM `purchase_order` \n" +
                "INNER JOIN supplier ON purchase_order.Kd_Supplier = supplier.Kd_Supplier\n" +
                "WHERE purchase_order.No_PO LIKE ?cari\n" +
                "OR purchase_order.Kd_Supplier LIKE ?cari\n" +
                "OR purchase_order.Username LIKE ?cari\n" +
                "OR purchase_order.Tgl_PO LIKE ?cari\n" +
                "OR purchase_order.Tgl_Kirim LIKE ?cari\n" +
                "OR supplier.Nm_Supplier LIKE ?cari");
            q.setParameter("cari", "%"+cari+"%");
            listPO = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPO;
    }
    
    public List<Object[]> findAllPONotExistInTerimaBarang(){
        EntityManager em = getEntityManager();
        List<Object[]> list = new ArrayList<>();
        try {
            Query q = em.createNativeQuery("SELECT * \n" +
                "FROM purchase_order po\n" +
                "WHERE NOT EXISTS (\n" +
                "SELECT * FROM penerimaan_barang pb\n" +
                "WHERE po.No_PO = pb.No_PO\n" +
                ")");
            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String nomorOtomatis(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal=Calendar.getInstance();
        String tahun=sdf.format(cal.getTime());
        String kode=tahun+"001"; 
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT po FROM PurchaseOrder po ORDER BY po.noPO DESC");
            q.setMaxResults(1);
            PurchaseOrder po=(PurchaseOrder) q.getSingleResult();
            if(q!=null){
                String nomorurut = po.getNoPO().substring(8);
                int urut = (Integer.parseInt(nomorurut)+1);
                String formatted=String.format("%03d", urut);
                kode=tahun+(formatted);
            }
        }catch(NoResultException ex){
            return kode;
        }
        return kode;
    }
}
