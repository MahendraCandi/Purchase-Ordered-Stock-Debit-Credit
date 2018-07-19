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
    
    public void delete(String kodeBarang){
        EntityManager em = getEntityManager();
        Barang brg;
        try{
            brg=em.getReference(Barang.class, kodeBarang);
            brg.getKdBarang();
            em.getTransaction().begin();
            em.remove(brg);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Barang findBarang(String kodeBarang){
        EntityManager em=getEntityManager();
        try{
            return em.find(Barang.class, kodeBarang);
        }finally{}
    }
    
    public DefaultTableModel findAllBarang(DefaultTableModel model){
        EntityManager em = getEntityManager();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Query q = em.createNativeQuery("SELECT barang.Kd_Barang, barang.Nama_Barang, barang.size, barang.Warna, barang.Hrg_Beli, barang.Hrg_Jual, barang.Kd_Supplier, supplier.Nm_Supplier, supplier.Kota FROM `barang`\n" +
                "INNER JOIN supplier ON barang.Kd_Supplier = supplier.Kd_Supplier "
                    + "ORDER BY barang.Kd_Barang ASC");
            List<Object[]> listBarang = q.getResultList();
            for(Object[] obj : listBarang){
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
            Query q = em.createNativeQuery("SELECT barang.Kd_Barang, barang.Nama_Barang, barang.size, barang.Warna, barang.Hrg_Beli, barang.Hrg_Jual, barang.Kd_Supplier, supplier.Nm_Supplier, supplier.Kota FROM `barang`\n" +
                "INNER JOIN supplier ON barang.Kd_Supplier = supplier.Kd_Supplier\n" +
                "WHERE barang.Kd_Barang LIKE ?cari\n" +
                "OR barang.Nama_Barang LIKE ?cari\n" +
                "OR barang.size LIKE ?cari\n" +
                "OR barang.Warna LIKE ?cari\n" +
                "OR barang.Kd_Supplier LIKE ?cari\n" +
                "OR supplier.Nm_Supplier LIKE ?cari\n" +
                "OR supplier.Kota LIKE ?cari\n" +
                "ORDER BY barang.Kd_Barang ASC");
            q.setParameter("cari", "%"+cari+"%");
            List<Object[]> listBarang = q.getResultList();
            for(Object[] obj : listBarang){
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    
    public String kodeOtomatis(String namaBarang){
        EntityManager em=null;
        String kode="";
        String formatKode="";
        int indexSubstring=4;
        if(namaBarang.equalsIgnoreCase("LADIES SANDAL TALI")){
            kode="LDT-001";
            formatKode="LDT-000";
        }else if(namaBarang.equalsIgnoreCase("LADIES SANDAL PARTY")){
            kode="LDP-001";
            formatKode="LDP-000";
        }else if(namaBarang.equalsIgnoreCase("LADIES SANDAL JEPIT")){
            kode="LDJ-001";
            formatKode="LDJ-000";
        }else if(namaBarang.equalsIgnoreCase("LADIES SEPATU CASUAL")){
            kode="LSC-001";
            formatKode="LSC-000";
        }else if(namaBarang.equalsIgnoreCase("LADIES SEPATU FORMAL")){
            kode="LSF-001";
            formatKode="LSF-000";
        }else if(namaBarang.equalsIgnoreCase("MEN SANDAL TALI")){
            kode="MDT-001";
            formatKode="MDT-000";
        }else if(namaBarang.equalsIgnoreCase("MEN SANDAL JEPIT")){
            kode="MDJ-001";
            formatKode="MDJ-000";
        }else if(namaBarang.equalsIgnoreCase("MEN SEPATU CASUAL")){
            kode="MSC-001";
            formatKode="MSC-000";
        }else if(namaBarang.equalsIgnoreCase("MEN SEPATU FORMAL")){
            kode="MSF-001";
            formatKode="MSF-000";
        }else if(namaBarang.equalsIgnoreCase("KIDS SANDAL TALI")){
            kode="KDT-001";
            formatKode="KDT-000";
        }else if(namaBarang.equalsIgnoreCase("KIDS SANDAL JEPIT")){
            kode="KDJ-001";
            formatKode="KDJ-000";
        }else if(namaBarang.equalsIgnoreCase("KIDS SEPATU CASUAL")){
            kode="KSC-001";
            formatKode="KSC-000";
        }else if(namaBarang.equalsIgnoreCase("TAS CASUAL")){
            kode="TC-001";
            formatKode="TC-000";
            indexSubstring = 3;
        }else if(namaBarang.equalsIgnoreCase("TAS PARTY")){
            kode="TP-001";
            formatKode="TP-000";
            indexSubstring = 3;
        }
        
        try {
            em=getEntityManager();
            Query q=em.createQuery("SELECT b FROM Barang b WHERE b.namaBarang=:namaBarang ORDER BY b.kdBarang DESC");
            q.setParameter("namaBarang", namaBarang);
            q.setMaxResults(1);
            Barang barang = (Barang) q.getSingleResult();
            if(q != null){
                DecimalFormat formatnomor = new DecimalFormat(formatKode);
                String nomorurut = barang.getKdBarang().substring(indexSubstring);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        } catch (NoResultException e) {
            return kode;
        }
        return kode;
    }
}
