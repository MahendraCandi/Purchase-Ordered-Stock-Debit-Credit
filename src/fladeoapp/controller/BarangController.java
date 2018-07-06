package fladeoapp.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import fladeoapp.data.Barang;
import java.text.DecimalFormat;
import javax.persistence.NoResultException;
import javax.persistence.Query;

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
    
    public void delete(String nama){
        EntityManager em = getEntityManager();
        Barang brg;
        try{
            brg=em.getReference(Barang.class, nama);
            brg.getKdBarang();
            em.getTransaction().begin();
            em.remove(brg);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Barang findBarang(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(Barang.class, nama);
        }finally{}
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
