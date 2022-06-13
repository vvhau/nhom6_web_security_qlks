package com.nhom6.qlks.hibernate.daos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nhom6.qlks.hibernate.HibernateUtils;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.HoaDon;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.hibernate.pojo.TrangThai;

public class PhongDao {
	public String insertPhong(Phong phong) {
		String err_msg = "";
		
        if (checkTenPhong(phong.getTenPhong())) {
        	return err_msg = "Ten phong da ton tai";
        }
        Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            // save the student object
            session.save(phong);       
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
	
	public void bookRoom(Phong phong, Session session) {
		// trạng thái đang ở
		TrangThai trangThai = new TrangThaiDao().getTrangThaiById(2);
		
		Query query = session.createQuery("UPDATE Phong SET trangThai=:trangThai "
				+ "WHERE idPhong=:idPhong");
		
		query.setParameter("trangThai", trangThai);
		query.setParameter("idPhong", phong.getIdPhong());
		int result = query.executeUpdate();
	}
	
	public void checkOutRoom(Phong phong, Session session) {
		TrangThai trangThai = new TrangThaiDao().getTrangThaiById(1);
		
		Query query = session.createQuery("UPDATE Phong SET trangThai=:trangThai "
				+ "WHERE idPhong=:idPhong");
		
		query.setParameter("trangThai", trangThai);
		query.setParameter("idPhong", phong.getIdPhong());
		int result = query.executeUpdate();
	}
	
	public String updatePhong(int roomId, String roomName, LoaiPhong roomType, TrangThai roomStatus) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
			/*
			 * Query query = session.createQuery("UPDATE Phong SET tenPhong=:tenphong" +
			 * " WHERE idPhong=:id"); query.setParameter("tenphong", tenphong);
			 * query.setParameter("id", id); int result = query.executeUpdate();
			 */
			
			Phong room = (Phong)session.get(Phong.class, roomId);
			room.setTenPhong(roomName);
			room.setLoaiPhong(roomType);
			room.setTrangThai(roomStatus);
            session.update(room);
            System.out.println("update phong");
            
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
	
	public String deletePhong(int id) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("DELETE FROM Phong WHERE idPhong=:id");
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("delete phong");
            
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
	
	public List<Phong> getAllPhong() {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Phong");//HQL
		
		List<Phong> phongs = q.getResultList();
		
		return phongs;
	}
	
	public List<Phong> searchRoomsByRoomType(int idRoomType, Date checkIn, Date checkOut) {
		Session session = HibernateUtils.getFactory().openSession();
		TrangThaiDao trangThaiDao = new TrangThaiDao();
		TrangThai dangSua = trangThaiDao.getTrangThaiById(3);
		Query q;
		
		q = session.createQuery("FROM Phong "
				+ "WHERE loaiPhong.idLoaiPhong=:idRoomType "
				+ "AND trangThai != :dangSua ");//HQL
		q.setParameter("idRoomType", idRoomType);
		q.setParameter("dangSua", dangSua);
		
		List<Phong> phongs = q.getResultList();
		List<Phong> phongsCp = new ArrayList<>(phongs);
		
		if (checkIn != null) {
			if (checkOut != null) {
				for (Phong p : phongs) {
					if (checkRoomBooked(p, checkIn, checkOut)) {
						phongsCp.remove(p);
					}
				}
			} else {
				for (Phong p : phongs) {
					if (checkRoomBookedAtDate(p, checkIn)) {
						phongsCp.remove(p);
					}
				}
			}
		}
		
		session.close();
		
		return phongsCp;
	}
	
	public List<Phong> timPhong(int soNguoi, Date checkIn, Date checkOut) {
		Session session = HibernateUtils.getFactory().openSession();
		
		TrangThaiDao trangThaiDao = new TrangThaiDao();
	
		TrangThai dangSua = trangThaiDao.getTrangThaiById(3);
		
		Query q;
		if (soNguoi == 0) {
			q = session.createQuery("FROM Phong "
					+ "WHERE trangThai != : dangSua ");//HQL
		} else {
			q = session.createQuery("FROM Phong "
					+ "WHERE loaiPhong.soNguoi=:soNguoi "
						+ "AND trangThai != :dangSua ");//HQL
			q.setParameter("soNguoi", soNguoi);
		}
		
		
		q.setParameter("dangSua", dangSua);
		
		List<Phong> phongs = q.getResultList();
		List<Phong> phongsCp = new ArrayList<>(phongs);
		
		if (checkIn != null) {
			if (checkOut != null) {
				for (Phong p : phongs) {
					if (checkRoomBooked(p, checkIn, checkOut)) {
						phongsCp.remove(p);
					}
				}
			} else {
				for (Phong p : phongs) {
					if (checkRoomBookedAtDate(p, checkIn)) {
						phongsCp.remove(p);
					}
				}
			}
		}
		
		session.close();
		
		return phongsCp;
	}
	
	public List<LoaiPhong> searchRoomTypes(int numPeople, Date checkIn, Date checkOut) {
		Session session = HibernateUtils.getFactory().openSession();
		TrangThaiDao trangThaiDao = new TrangThaiDao();
		TrangThai dangSua = trangThaiDao.getTrangThaiById(3);
		Query q1;
		Query q2;
		
		q1 = session.createQuery("FROM LoaiPhong "
				+ "WHERE soNguoi <= :numPeople");//HQL
		q1.setParameter("numPeople", numPeople);		
		
		List<LoaiPhong> loaiPhongs = q1.getResultList();
		List<LoaiPhong> loaiPhongsCp = new ArrayList<LoaiPhong>(); 
		q2 = session.createQuery("FROM Phong "
				+ "WHERE loaiPhong.soNguoi <= :numPeople "
				+ "AND trangThai != :dangSua ");//HQL
		q2.setParameter("numPeople", numPeople);		
		q2.setParameter("dangSua", dangSua);
		List<Phong> phongs = q2.getResultList();
		List<Phong> phongsCp = new ArrayList<>(phongs);
		
		if (checkIn != null) {
			if (checkOut != null) {
				for (Phong p : phongs) {
					if (checkRoomBooked(p, checkIn, checkOut)) {
						phongsCp.remove(p);
					}
				}
			} else {
				for (Phong p : phongs) {
					if (checkRoomBookedAtDate(p, checkIn)) {
						phongsCp.remove(p);
					}
				}
			}
		}
		
		for (LoaiPhong loaiPhong : loaiPhongs) {
			for (Phong phong : phongsCp) {
				if (phong.getLoaiPhong() == loaiPhong) {
					loaiPhongsCp.add(loaiPhong);
					break;
				}
			}
		}
		
		session.close();
		
		return loaiPhongsCp;
	}
	
	boolean checkRoomBooked(Phong phong, Date checkIn, Date checkOut) {
		for (Booking bk : phong.getBookings()) {
			if (!checkInOut(bk, checkIn, checkOut)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkInOut(Booking booking, Date checkIn, Date checkOut) {
		if ((checkIn.compareTo(booking.getCheckIn()) == -1 
				&& checkOut.compareTo(booking.getCheckIn()) == -1) 
				|| 
				(checkIn.compareTo(booking.getCheckOut()) == 1)
				&& checkOut.compareTo(booking.getCheckOut()) == 1) {
			return true;
		}
		return false;
	}
	
	private boolean checkRoomBookedAtDate(Phong phong, Date date) {
		for (Booking bk : phong.getBookings()) {
			if (!checkBookingAtDate(bk, date)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkBookingAtDate(Booking booking, Date date) {
		if (date.compareTo(booking.getCheckIn()) == -1 
				|| date.compareTo(booking.getCheckOut()) == 1) {
			return false;
		}
		
		return true;
	}
	
	public List<Phong> getPhongByLoaiPhong(LoaiPhong loaiPhong) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Phong WHERE loaiPhong=:loaiPhong");//HQL
		
		q.setParameter("loaiPhong", loaiPhong);
		
		List<Phong> phongs = q.getResultList();
		
		session.close();
		
		return phongs;
	}
	
	public Phong getPhongById(int id) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Phong WHERE idPhong=:id");//HQL
		
		q.setParameter("id", id);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<Phong> phongs = q.getResultList();
		
		if (phongs.size() > 0) {
			return phongs.get(0);
		}
		
		session.close();
		
		return null;
	}
	
	private boolean checkTenPhong(String checktenphong) {
		if (getTenPhong(checktenphong) != null) {
			return true;
		}
		return false;
	}
	
	public Phong getTenPhong(String tenphong) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Phong WHERE tenPhong=:tenphong");//HQL
		
		q.setParameter("tenphong", tenphong);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<Phong> phongs = q.getResultList();
		
		if (phongs.size() > 0) {
			return phongs.get(0);
		}
		
		session.close();
		
		return null;
	}
}
