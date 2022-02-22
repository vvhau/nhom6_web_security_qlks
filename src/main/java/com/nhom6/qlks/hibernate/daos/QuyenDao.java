package com.nhom6.qlks.hibernate.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nhom6.qlks.hibernate.HibernateUtils;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Quyen;
import com.nhom6.qlks.hibernate.pojo.TrangThai;

public class QuyenDao {
	public String insertQuyen(Quyen quyen) {
		String err_msg = "";
		
        
        if (checkTenQuyen(quyen.getTenQuyen())) {
        	return err_msg = "Tên quyền đã tồn tại";
        }
        Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            // save the student object
            session.save(quyen);       
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
	
	public String updateQuyen(int id,String tenquyen) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("UPDATE Quyen SET tenQuyen=:tenquyen"
            		+ " WHERE idQuyen=:id");
			query.setParameter("tenquyen", tenquyen);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("update quyen");
            
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
	
	public String deleteQuyen(int id) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("DELETE FROM Quyen WHERE idPhong=:id");
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("delete quyen");
            
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
	
	public List<Quyen> getAllQuyen() {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Quyen");//HQL
		
		List<Quyen> quyens = q.getResultList();
		
		return quyens;
	}
	
	public Quyen getQuyenById(int id) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Quyen WHERE idQuyen=:id");//HQL
		
		q.setParameter("id", id);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<Quyen> quyens = q.getResultList();
		
		if (quyens.size() > 0) {
			return quyens.get(0);
		}
		
		return null;
	}
	
	private boolean checkTenQuyen(String checktenquyen) {
		if (getTenQuyen(checktenquyen) != null) {
			return true;
		}
		return false;
	}
	
	public Quyen getTenQuyen(String tenquyen) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Quyen WHERE tenQuyen=:tenquyen");//HQL
		
		q.setParameter("tenquyen", tenquyen);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<Quyen> quyens = q.getResultList();
		
		if (quyens.size() > 0) {
			return quyens.get(0);
		}
		
		return null;
	}
	
}
