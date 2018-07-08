package fladeoapp.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import fladeoapp.data.Barang;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

public class BarangController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public BarangController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Barang barang){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(barang);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(Barang barang){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(barang);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(int idBarang){
        EntityManager em = getEntityManager();
        Barang brg;
        try{
            brg=em.getReference(Barang.class, idBarang);
            brg.getKdBarang();
            em.getTransaction().begin();
            em.remove(brg);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Barang findByKodeBarang(String kodeBarang){
        EntityManager em=getEntityManager();
        Barang barang = new Barang();
        try {
            Query q = em.createQuery("SELECT b FROM Barang b WHERE b.kdBarang = :kodeBarang");
            q.setParameter("kodeBarang", kodeBarang);
            barang = (Barang) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return barang;
    }
    
    public DefaultTableModel findAllBarang(DefaultTableModel model){
        EntityManager em = getEntityManager();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Query q = em.createNativeQuery("SELECT barang.Kd_Barang, barang.Jenis_Barang, barang.Hrg_Beli, barang.Hrg_Jual, barang.Kd_Supplier, supplier.Nm_Supplier, supplier.Kota FROM `barang`\n" +
                "INNER JOIN supplier ON barang.Kd_Supplier = supplier.Kd_Supplier "
                    + "ORDER BY barang.Kd_Barang ASC");
            List<Object[]> listBarang = q.getResultList();
            for(Object[] obj : listBarang){
                Object[] objModel = new Object[7];
//                objModel[0] = obj[0];
//                objModel[1] = obj[1];
//                objModel[2] = obj[2];
//                objModel[3] = obj[3];
//                objModel[4] = obj[4];
//                objModel[5] = obj[5];
//                objModel[6] = obj[6];
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    
    public List<Barang> findBarangBySupplierToList(String kodeSupplier){
        EntityManager em = getEntityManager();
        List<Barang> listBarang = new ArrayList<>();
        try {
            Query q=em.createQuery("SELECT b FROM Barang b WHERE b.kdSupplier = :kodeSupplier");
            q.setParameter("kodeSupplier", kodeSupplier);
            listBarang = q.getResultList();
        } catch (Exception e) {
            return null;
        }
        return listBarang;
    }
    
    public DefaultTableModel searchBarang(DefaultTableModel model, String cari){
        EntityManager em = getEntityManager();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Query q = em.createNativeQuery("SELECT barang.Kd_Barang, barang.Jenis_Barang, barang.Hrg_Beli, barang.Hrg_Jual, barang.Kd_Supplier, supplier.Nm_Supplier, supplier.Kota FROM `barang`\n" +
                "INNER JOIN supplier ON barang.Kd_Supplier = supplier.Kd_Supplier\n" +
                "WHERE barang.Kd_Barang LIKE ?cari\n" +
                "OR barang.Jenis_Barang LIKE ?cari\n" +
                "OR barang.Kd_Supplier LIKE ?cari\n" +
                "OR supplier.Nm_Supplier LIKE ?cari\n" +
                "OR supplier.Kota LIKE ?cari\n" +
                "ORDER BY barang.Kd_Barang ASC");
            q.setParameter("cari", "%"+cari+"%");
            List<Object[]> listBarang = q.getResultList();
            for(Object[] obj : listBarang){
                Object[] objModel = new Object[7];
                objModel[0] = obj[0];
                objModel[1] = obj[1];
                objModel[2] = obj[2];
                objModel[3] = obj[3];
                objModel[4] = obj[4];
                objModel[5] = obj[5];
                objModel[6] = obj[6];
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    
    public String kodeOtomatis(String jenisBarang){
        EntityManager em=null;
        String kode="";
        String formatKode="";
        int indexSubstring=4;
        if(jenisBarang.equalsIgnoreCase("LADIES TALI")){
            kode="LDT-001";
            formatKode="LDT-000";
        }else if(jenisBarang.equalsIgnoreCase("LADIES HAK")){
            kode="LDH-001";
            formatKode="LDH-000";
        }else if(jenisBarang.equalsIgnoreCase("LADIES SANDAL")){
            kode="LDS-001";
            formatKode="LDS-000";
        }else if(jenisBarang.equalsIgnoreCase("LADIES CASUAL")){
            kode="LSC-001";
            formatKode="LSC-000";
        }else if(jenisBarang.equalsIgnoreCase("LADIES FORMAL")){
            kode="LSM-001";
            formatKode="LSM-000";
        }else if(jenisBarang.equalsIgnoreCase("MENS TALI")){
            kode="MDT-001";
            formatKode="MDT-000";
        }else if(jenisBarang.equalsIgnoreCase("MENS CASUAL")){
            kode="MSC-001";
            formatKode="MSM-000";
        }else if(jenisBarang.equalsIgnoreCase("MENS FORMAL")){
            kode="MSM-001";
            formatKode="MSM-000";
        }else if(jenisBarang.equalsIgnoreCase("MENS SANDAL")){
            kode="MDS-001";
            formatKode="MDS-000";
        }else if(jenisBarang.equalsIgnoreCase("SEPATU KIDS CASUAL")){
            kode="KSGC-001";
            formatKode="KSGC-000";
            indexSubstring = 5;
        }else if(jenisBarang.equalsIgnoreCase("SEPATU KIDS SEKOLAH")){
            kode="KSGR-001";
            formatKode="KSGR-000";
            indexSubstring = 5;
        }else if(jenisBarang.equalsIgnoreCase("KIDS SANDAL")){
            kode="KSGS-001";
            formatKode="KSGS-000";
            indexSubstring = 5;
        }else if(jenisBarang.equalsIgnoreCase("KIDS TALI")){
            kode="KSGS-001";
            formatKode="KSGS-000";
            indexSubstring = 5;
        }else if(jenisBarang.equalsIgnoreCase("BAG")){
            kode="QC-001";
            formatKode="QC-000";
            indexSubstring = 3;
        }
        
        try {
            em=getEntityManager();
            Query q=em.createQuery("SELECT b FROM Barang b WHERE b.jenisBarang=:jenisBarang ORDER BY b.kdBarang DESC");
            q.setParameter("jenisBarang", jenisBarang);
            q.setMaxResults(1);
            Barang barang = (Barang) q.getSingleResult();
            if(q != null){
                DecimalFormat formatnomor = new DecimalFormat(formatKode);
                String nomorurut = barang.getKdBarang().substring(indexSubstring);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        } catch (NoResultException e) {
        }
        return kode;
    }
}
