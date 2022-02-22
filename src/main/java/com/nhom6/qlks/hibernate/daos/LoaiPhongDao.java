package com.nhom6.qlks.hibernate.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nhom6.qlks.hibernate.HibernateUtils;
import com.nhom6.qlks.hibernate.daos.PhongDao;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Phong;
public class LoaiPhongDao {
	
	public String insertLoaiPhong(LoaiPhong loaiPhong) {
		String err_msg = "";
		
		if (checkTenLoaiPhong(loaiPhong.getTenLoaiPhong())) {
    		return err_msg = "Tên loại phòng đã tồn tại";
        }
        
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            // save the student object
            session.save(loaiPhong);       
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
	
	public String updateLoaiPhong(int roomTypeId, String roomTypeName, String roomTypeImage, float roomTypeUnitPrice, int roomTypeNumPeople, String roomTypeNote) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
			/*
			 * Query query = session.
			 * createQuery("UPDATE LoaiPhong SET tenLoaiPhong=:tenloaiphong,hinhAnh=:hinhanh,donGia=:dongia,soNguoi=:songuoi,ghiChu:=ghichu"
			 * + " WHERE idLoaiPhong=:id"); query.setParameter("tenloaiphong",
			 * tenloaiphong); query.setParameter("hinhanh", hinhanh);
			 * query.setParameter("dongia", dongia); query.setParameter("songuoi", songuoi);
			 * query.setParameter("ghichu", ghichu); query.setParameter("id", id); int
			 * result = query.executeUpdate();
			 */
			
			LoaiPhong roomType = (LoaiPhong)session.get(LoaiPhong.class, roomTypeId);
			roomType.setTenLoaiPhong(roomTypeName);
			roomType.setHinhAnh(roomTypeImage);
			roomType.setDonGia(roomTypeUnitPrice);
			roomType.setSoNguoi(roomTypeNumPeople);
			roomType.setGhiChu(roomTypeNote);
			
            session.update(roomType);
			
            System.out.println("update LoaiPhong");
            
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
	
	public String deleteLoaiPhong(int roomTypeId) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("DELETE FROM LoaiPhong WHERE idLoaiPhong=:id");
			query.setParameter("id", roomTypeId);
			int result = query.executeUpdate();
			
            System.out.println("delete LoaiPhong");
            
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
	
	private boolean checkTenLoaiPhong(String name) {
		if (getLoaiPhongByName(name) != null) {
			return true;
		}
		return false;
	}

	public List<LoaiPhong> getAllLoaiPhong() {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM LoaiPhong");//HQL1
		
		List<LoaiPhong> loaiPhongs = q.getResultList();
		
		return loaiPhongs;
	}
	
	public LoaiPhong getLoaiPhongById(int id) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM LoaiPhong WHERE idLoaiPhong=:id");//HQL
		
		q.setParameter("id", id);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<LoaiPhong> loaiPhongs = q.getResultList();
		
		if (loaiPhongs.size() > 0) {
			return loaiPhongs.get(0);
		}
		
		return null;
	}
	
	public LoaiPhong getLoaiPhongByName(String name) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM LoaiPhong WHERE tenLoaiPhong=:name");//HQL
		
		q.setParameter("name", name);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<LoaiPhong> loaiPhongs = q.getResultList();
		
		if (loaiPhongs.size() > 0) {
			return loaiPhongs.get(0);
		}
		
		return null;
	}
	
	
	
}
