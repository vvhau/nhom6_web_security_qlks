package com.nhom6.qlks.hibernate.daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nhom6.qlks.hibernate.HibernateUtils;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.HoaDon;
import com.nhom6.qlks.hibernate.pojo.KhachHang;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.hibernate.pojo.TrangThai;

public class BookingDao {
	public String insertBooking(Booking booking, KhachHang[] dataKH) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            // save the student object
            session.save(booking);       
            System.out.println("saved booking");
            System.out.printf("id booking: %d\n", booking.getIdBooking());
            
            
            KhachHangDao khachHangDao = new KhachHangDao();
            khachHangDao.insertKhachHangs(dataKH, booking, session);
            System.out.println("saved khachHangs");
            
            Phong phong = booking.getPhong();
            PhongDao phongDao = new PhongDao();
            phongDao.bookRoom(phong, session);
            System.out.println("updated room status");
            
            
            // commit transaction
            transaction.commit();
            System.out.println("commited transaction");
            
            System.out.printf("id booking: %d\n", booking.getIdBooking());
            
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
	
	public String insertBookingOnline(Booking booking) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            session.save(booking);       
            System.out.println("saved booking");
            System.out.printf("id booking: %d\n", booking.getIdBooking());                        
            
            // commit transaction
            transaction.commit();
            System.out.println("commited transaction");
            
            System.out.printf("id booking: %d\n", booking.getIdBooking());
            
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
	
	public void payBookings(List<Booking> bookings, HoaDon hoaDon, Session session) {
		for (Booking bk : bookings) {
			
			Query q = session.createQuery(
					"UPDATE Booking "
					+ "SET hoaDon=:hoaDon "
					+ "WHERE idBooking=:idBooking");
			
			q.setParameter("hoaDon", hoaDon);
			q.setParameter("idBooking", bk.getIdBooking());
			
			q.executeUpdate();
			
			Phong phong = bk.getPhong();
			PhongDao phongDao = new PhongDao();
			phongDao.checkOutRoom(phong, session);
		}
	}
	
	public String updateBooking(int id, int soNguoi, Date checkIn, Date checkOut) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery(
            		"UPDATE Booking "
            		+ "SET soNguoi=:soNguoi, checkIn=:checkIn, checkOut=:checkOut"
            		+ " WHERE idBooking=:id");
			query.setParameter("soNguoi", soNguoi);
			query.setParameter("checkIn", checkIn);
			query.setParameter("checkOut", checkOut);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("update Booking");
            
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
	
	public String deleteBooking(int id) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("DELETE FROM Booking WHERE idBooking=:id");
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("delete Booking");
            
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
	
	public int tongSuDungPhong(String monthStr) {
		Session session = HibernateUtils.getFactory().openSession();
		
		Query q;
		
		if (monthStr == null) {
			q = session.createNativeQuery("SELECT "
					+ "SUM(DATEDIFF(bk.check_out, bk.check_in)) "
					+ "FROM booking bk ");
		} else {
			monthStr = monthStr.concat("-01");
			System.out.println(monthStr);
			
			q = session.createNativeQuery("SELECT "
					+ "SUM(temp.so_ngay) "
					+ "FROM ( "
					+ "SELECT "
					+ "(CASE "
					+ "WHEN bk.check_in < :month "
						+ "AND bk.check_out >= :month "
						+ "AND bk.check_out <= last_day(:month) "
						+ "THEN DATEDIFF(bk.check_out, :month) "
					+ "WHEN bk.check_out > last_day(:month) "
						+ "AND bk.check_in >= :month "
						+ "AND bk.check_in <= last_day(:month) "
						+ "THEN DATEDIFF(last_day(:month), bk.check_in) "
					+ "WHEN bk.check_in >= :month AND bk.check_in <= last_day(:month) "
						+ "AND bk.check_out >= :month AND bk.check_out <= last_day(:month) "
						+ "THEN DATEDIFF(bk.check_out, bk.check_in) "
					+ "ELSE 0 "
					+ "END) AS so_ngay "
					+ "FROM booking bk "
					+ ") AS temp");
			
			q.setParameter("month", monthStr);
		}
		
		
		Object rs = q.getSingleResult();
		
		int tongTGSuDung = 0;
		if (rs != null) {
			tongTGSuDung = ((BigDecimal) rs).intValue();
		}
		
		System.out.println(tongTGSuDung);
		
		session.close();
		
		return tongTGSuDung;
	}
	
	public List<Object[]> thongKeMatDoSuDungPhong(String monthStr) {
		Session session = HibernateUtils.getFactory().openSession();
		
		Query q;
		if (monthStr == null) {
			q = session.createNativeQuery("SELECT "
					+ "p.id_phong, "
					+ "p.ten_phong, "
					+ "(CASE "
					+ "WHEN bk.id_booking is NULL THEN 0 "
					+ "ELSE SUM(DATEDIFF(bk.check_out, bk.check_in)) "
					+ "END) "
					+ "FROM phong p left join booking bk "
					+ "ON p.id_phong = bk.id_phong "
					+ "GROUP BY p.id_phong, p.ten_phong");
		} else {
			monthStr = monthStr.concat("-01");
			System.out.println(monthStr);
			
			q = session.createNativeQuery("SELECT "
					+ "temp.id_phong, "
					+ "temp.ten_phong, "
					+ "SUM(temp.so_ngay) "
					+ "FROM ( "
					+ "SELECT p.id_phong, p.ten_phong, "
					+ "(CASE "
					+ "WHEN bk.check_in < :month "
						+ "AND bk.check_out >= :month "
						+ "AND bk.check_out <= last_day(:month) "
						+ "THEN DATEDIFF(bk.check_out, :month) "
					+ "WHEN bk.check_out > last_day(:month) "
						+ "AND bk.check_in >= :month "
						+ "AND bk.check_in <= last_day(:month) "
						+ "THEN DATEDIFF(last_day(:month), bk.check_in) "
					+ "WHEN bk.check_in >= :month AND bk.check_in <= last_day(:month) "
						+ "AND bk.check_out >= :month AND bk.check_out <= last_day(:month) "
						+ "THEN DATEDIFF(bk.check_out, bk.check_in) "
					+ "ELSE 0 "
					+ "END) AS so_ngay "
					+ "FROM phong p left join booking bk "
					+ "ON p.id_phong = bk.id_phong ) temp "
					+ "GROUP BY temp.id_phong, temp.ten_phong");
			
			q.setParameter("month", monthStr);
		}
		
		List<Object[]> thongKe = (List<Object[]>) q.getResultList();
		
		thongKe.forEach(x -> {
			System.out.printf("%d, %s, %f\n", x[0], x[1], x[2]);
		});
		
		session.close();
		
		return thongKe;
	}
	
	public List<Booking> getAllBooking() {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Booking");//HQL
		
		List<Booking> bookings = q.getResultList();
		
		session.close();
		
		return bookings;
	}
	
	public List<Booking> getAllBookingOffline(int pageNumber) {
		int pageSize = 10;
		
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Booking "
				+ "WHERE datOnline=0 "
				+ "ORDER BY idBooking DESC");//HQL
		
		q.setMaxResults(pageSize);
		q.setFirstResult(pageSize*(pageNumber - 1));
		
		List<Booking> bookings = q.getResultList();
		
		session.close();
		
		return bookings;
	}
	
	public List<Booking> getAllBookingOnline(int pageNumber) {
		int pageSize = 10;
		
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Booking "
				+ "WHERE "
				+ "datOnline=1 "
				+ "ORDER BY idBooking DESC");//HQL
		
		q.setMaxResults(pageSize);
		q.setFirstResult(pageSize*(pageNumber - 1));
		
		List<Booking> bookings = q.getResultList();
		
		session.close();
		
		return bookings;
	}
	
	public Booking getBookingOnlineById(int id) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Booking "
				+ "WHERE idBooking=:id AND datOnline=1");//HQL
		
		q.setParameter("id", id);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<Booking> bookings = q.getResultList();
		
		if (bookings.size() > 0) {
			return bookings.get(0);
		}
		
		session.close();
		
		return null;
	}
	
	public Booking getBookingById(int id) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Booking WHERE idBooking=:id");//HQL
		
		q.setParameter("id", id);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<Booking> bookings = q.getResultList();
		
		if (bookings.size() > 0) {
			return bookings.get(0);
		}
		
		session.close();
		
		return null;
	}
	
	public List<Booking> getBookingsByRoomName(String roomName) {
		Session session = HibernateUtils.getFactory().openSession();
		
		Query q = session.createQuery("FROM Booking "
				+ "WHERE phong.tenPhong LIKE :roomName "
				+ "AND hoaDon is null");//HQL
		
		q.setParameter("roomName", "%" + roomName + "%");
		
		List<Booking> bookings = q.getResultList();
		
		session.close();
		
		return bookings;
	}
	
	public List<Booking> getBookingsByIdUser(int idUser) {
		Session session = HibernateUtils.getFactory().openSession();
		
		Query q = session.createQuery("FROM Booking "
				+ "WHERE user.id LIKE :idUser "
				+ "AND hoaDon is null");//HQL
		
		q.setParameter("idUser",  idUser );
		
		List<Booking> bookings = q.getResultList();		
		session.close();
		
		return bookings;
	}
	
	private boolean checkRoomBooked(int idPhong, Date checkIn, Date checkOut) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Booking WHERE checkIn=:checkin");//HQL
		
		session.close();
		return false;
	}
	
	private boolean checkCheckIn(Date checkin) {
		if (getCheckIn(checkin) != null) {
			return true;
		}
		return false;
	}
	
	public Booking getCheckIn(Date checkin) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Booking WHERE checkIn=:checkin");//HQL
		
		q.setParameter("checkin", checkin);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<Booking> bookings = q.getResultList();
		
		if (bookings.size() > 0) {
			return bookings.get(0);
		}
		
		session.close();
		
		return null;
	}
	
	private boolean checkCheckOut(Date checkout) {
		if (getCheckIn(checkout) != null) {
			return true;
		}
		return false;
	}
	
	public Booking getCheckOut(Date checkout) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Booking WHERE checkOut=:checkout");//HQL
		
		q.setParameter("checkout", checkout);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<Booking> bookings = q.getResultList();
		
		if (bookings.size() > 0) {
			return bookings.get(0);
		}
		
		session.close();
		
		return null;
	}
	
	//dongia
	private boolean checkDonGia(Float checkdongia) {
		if (getCheckDonGia(checkdongia) != null) {
			return true;
		}
		return false;
	}
	
	public Booking getCheckDonGia(Float dongia) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM Booking WHERE donGia=:dongia");//HQL
		
		q.setParameter("dongia", dongia);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<Booking> bookings = q.getResultList();
		
		if (bookings.size() > 0) {
			return bookings.get(0);
		}
		
		session.close();
		
		return null;
	}
	

}
