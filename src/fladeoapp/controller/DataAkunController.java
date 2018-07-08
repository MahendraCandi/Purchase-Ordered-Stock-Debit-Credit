package fladeoapp.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import fladeoapp.data.DataAkun;
import java.text.DecimalFormat;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

public class DataAkunController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private EntityManagerFactory emf=null;
    
    public DataAkunController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(DataAkun dataAkun){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(dataAkun);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void update(DataAkun dataAkun){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(dataAkun);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String kode){
        EntityManager em = getEntityManager();
        DataAkun akun;
        try{
            akun=em.getReference(DataAkun.class, kode);
            akun.getKdAkun();
            em.getTransaction().begin();
            em.remove(akun);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public DataAkun findDataAkun(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(DataAkun.class, nama);
        }finally{}
    }
    
    public DefaultTableModel findAllAkun(DefaultTableModel model){
        EntityManager em = getEntityManager();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Query q = em.createQuery("SELECT da FROM DataAkun da");
            List<DataAkun> listAkun = q.getResultList();
            for(DataAkun akun : listAkun){
                Object[] obj = new Object[2];
                obj[0] = akun.getKdAkun();
                obj[1] = akun.getNmAkun();
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
            Query q = em.createQuery("SELECT da FROM DataAkun da WHERE da.kdAkun LIKE :cari OR da.nmAkun LIKE :cari");
            q.setParameter("cari", "%"+cari+"%");
            List<DataAkun> listAkun = q.getResultList();
            for(DataAkun akun : listAkun){
                Object[] obj = new Object[2];
                obj[0] = akun.getKdAkun();
                obj[1] = akun.getNmAkun();
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
            Query q=em.createQuery("SELECT d FROM DataAkun d ORDER BY d.kdAkun DESC");
            q.setMaxResults(1);
            DataAkun dataAkun=(DataAkun) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("AKUN-000");
                String nomorurut = dataAkun.getKdAkun().substring(5);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
}
