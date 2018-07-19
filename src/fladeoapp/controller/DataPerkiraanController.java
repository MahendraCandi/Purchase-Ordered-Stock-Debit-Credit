package fladeoapp.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import fladeoapp.data.DataPerkiraan;
import java.text.DecimalFormat;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

public class DataPerkiraanController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public DataPerkiraanController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(DataPerkiraan dataAkun){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(dataAkun);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(DataPerkiraan dataAkun){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(dataAkun);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        DataPerkiraan perkiraan;
        try{
            perkiraan=em.getReference(DataPerkiraan.class, kode);
            perkiraan.getKdPerkiraan();
            em.getTransaction().begin();
            em.remove(perkiraan);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public DataPerkiraan findDataPerkiraan(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(DataPerkiraan.class, nama);
        }finally{}
    }
    
    public DefaultTableModel findAllAkun(DefaultTableModel model){
        EntityManager em = getEntityManager();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Query q = em.createQuery("SELECT da FROM DataPerkiraan da");
            List<DataPerkiraan> listAkun = q.getResultList();
            for(DataPerkiraan perkiraan : listAkun){
                Object[] obj = new Object[2];
                obj[0] = perkiraan.getKdPerkiraan();
                obj[1] = perkiraan.getNmPerkiraan();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    
    public DefaultTableModel findOneAkunToTable(DefaultTableModel model, String cari){
        EntityManager em = getEntityManager();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Query q = em.createQuery("SELECT da FROM DataPerkiraan da WHERE da.kdAkun LIKE :cari OR da.nmAkun LIKE :cari");
            q.setParameter("cari", "%"+cari+"%");
            List<DataPerkiraan> listAkun = q.getResultList();
            for(DataPerkiraan perkiraan : listAkun){
                Object[] obj = new Object[2];
                obj[0] = perkiraan.getKdPerkiraan();
                obj[1] = perkiraan.getNmPerkiraan();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    
    public String nomorOtomatis(){
        String kode="AKUN-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT d FROM DataPerkiraan d ORDER BY d.kdAkun DESC");
            q.setMaxResults(1);
            DataPerkiraan dataPerkiraan=(DataPerkiraan) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("AKUN-000");
                String nomorurut = dataPerkiraan.getKdPerkiraan().substring(5);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
}
