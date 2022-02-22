package com.nhom6.qlks.hibernate.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nhom6.qlks.hibernate.HibernateUtils;
import com.nhom6.qlks.hibernate.pojo.TrangThai;

public class TrangThaiDao {
	public String insertTrangThai(TrangThai trangthai) {
		String err_msg = "";
		
		if (checkTenTrangThai(trangthai.getTenTrangThai())) {
    		return err_msg = "Ten trang thai da ton tai";
        }
        
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            // save the student object
            session.save(trangthai);       
            System.out.println("saved user");
            // commit transaction
            transaction.commit();
            System.out.println("commited transaction");
            
            err_msg = "successed";
        } catch (Exception e) {
            if (transaction != null) {
            	System.out.println("roll back transaction");
                transaction.rollback();
                err_msg = "failed";
            }
            e.printStackTrace();
        } finally {
        	   session.close();
        }
        return err_msg;
	}
	
	public String updateTrangThai(int id,String tentrangthai) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("UPDATE TrangThai SET tenTrangThai=:tentrangthai"
            		+ " WHERE idTrangThai=:id");
			query.setParameter("tentrangthai", tentrangthai);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("update trangthai");
            
            // commit transaction
            transaction.commit();
            System.out.println("commited transaction");
            
            err_msg = "successed";
        } catch (Exception e) {
            if (transaction != null) {
            	System.out.println("roll back transaction");
                transaction.rollback();
                err_msg = "failed";
            }
            e.printStackTrace();
        } finally {
        	   session.close();
        }
        return err_msg;
	}
	
	public String deleteTrangThai(int id) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("DELETE FROM TrangThai WHERE idTrangThai=:id");
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("delete trangthai");
            
            // commit transaction
            transaction.commit();
            System.out.println("commited transaction");
            
            err_msg = "successed";
        } catch (Exception e) {
            if (transaction != null) {
            	System.out.println("roll back transaction");
                transaction.rollback();
                err_msg = "failed";
            }
            e.printStackTrace();
        } finally {
        	   session.close();
        }
        return err_msg;
	}
	
	private boolean checkTenTrangThai(String name) {
		if (getTenTrangThai(name) != null) {
			return true;
		}
		return false;
	}
	public List<TrangThai> getAllTrangThai() {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM TrangThai");//HQL
		
		List<TrangThai> trangthais = q.getResultList();
		
		return trangthais;
	}
	
	public TrangThai getTrangThaiById(int id) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM TrangThai WHERE idTrangThai=:id");//HQL
		
		q.setParameter("id", id);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<TrangThai> trangthais = q.getResultList();
		
		if (trangthais.size() > 0) {
			return trangthais.get(0);
		}
		
		return null;
	}
	public TrangThai getTenTrangThai(String name) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM TrangThai WHERE tenTrangThai=:name");//HQL
		
		q.setParameter("name", name);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<TrangThai> trangthais = q.getResultList();
		
		if (trangthais.size() > 0) {
			return trangthais.get(0);
		}
		
		return null;
	}
}
