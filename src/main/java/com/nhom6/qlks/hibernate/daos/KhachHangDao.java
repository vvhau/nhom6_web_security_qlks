package com.nhom6.qlks.hibernate.daos;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nhom6.qlks.hibernate.HibernateUtils;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.HoaDon;
import com.nhom6.qlks.hibernate.pojo.KhachHang;

public class KhachHangDao {
	public void insertKhachHangs(KhachHang[] khachHangs, Booking booking, Session session) {
		// save the KhachHangs object
        for (KhachHang kh : khachHangs) {
        	kh.setBooking(booking);
            session.save(kh);
        }
                   
//        System.out.println("saved khachhang");
	}
	
	public String insertKhachHangs(KhachHang[] khachHangs, Booking booking) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
        	transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            // save the KhachHangs object
            for (KhachHang kh : khachHangs) {
            	kh.setBooking(booking);
                session.save(kh);
            }
            
            transaction.commit();
            System.out.println("commited transaction");
        	
        	err_msg = "successed";
        } catch (Exception e) {
			// TODO: handle exception
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
	
	public String updateKhachHang(int id,String hoten,String cmnd,String diachi) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("UPDATE KhachHang SET ho_ten=:hoten,cmnd=:cmnd,diaChi=:diachi"
            		+ " WHERE idKhach=:id");
			query.setParameter("hoten", hoten);
			query.setParameter("cmnd", cmnd);
			query.setParameter("diachi", diachi);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("update khachhang");
            
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
	
	public String deleteKhachHang(int id) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("DELETE FROM KhachHang WHERE idKhach=:id");
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("delete Khach");
            
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
	
	public List<KhachHang> getAllKhachHang() {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM KhachHang");//HQL
		
		List<KhachHang> khachhangs = q.getResultList();
		
		return khachhangs;
	}
	
	public KhachHang getIdKhach(int idkhach) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM KhachHang WHERE idKhach=:idkhach");//HQL
		
		q.setParameter("idkhach", idkhach);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<KhachHang> khachhangs = q.getResultList();
		
		if (khachhangs.size() > 0) {
			return khachhangs.get(0);
		}
		
		return null;
	}
	//hoten
	private boolean checkHoTen(String checkhoten) {
		if (getHoTen(checkhoten) != null) {
			return true;
		}
		return false;
	}
	
	public KhachHang getHoTen(String hoten) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM KhachHang WHERE ho_ten=:hoten");//HQL
		
		q.setParameter("hoten", hoten);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<KhachHang> khachhangs = q.getResultList();
		
		if (khachhangs.size() > 0) {
			return khachhangs.get(0);
		}
		
		return null;
	}
	//cmnd
	private boolean checkCmnd(String checkcmnd) {
		if (getCmnd(checkcmnd) != null) {
			return true;
		}
		return false;
	}
	
	public KhachHang getCmnd(String cmnd) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM KhachHang WHERE cmnd=:cmnd");//HQL
		
		q.setParameter("cmnd", cmnd);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<KhachHang> khachhangs = q.getResultList();
		
		if (khachhangs.size() > 0) {
			return khachhangs.get(0);
		}
		
		return null;
	}
	
}
