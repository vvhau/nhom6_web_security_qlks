package com.nhom6.qlks.hibernate.daos;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nhom6.qlks.hibernate.HibernateUtils;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.HoaDon;
import com.nhom6.qlks.hibernate.pojo.TrangThai;
import com.nhom6.qlks.hibernate.pojo.User;

public class HoaDonDao {
	public String inserHoaDon(HoaDon hoaDon, List<Booking> billDetail) {
		String err_msg = "";
        
        Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            // save the student object
            session.save(hoaDon);       
            System.out.println("saved user");
            
            BookingDao bookingDao = new BookingDao();
            bookingDao.payBookings(billDetail, hoaDon, session);
            
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
	
	public String updateHoaDon(int id,Date ngaytao) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("UPDATE HoaDon SET ngayTao=:ngaytao"
            		+ " WHERE idHD=:id");
			query.setParameter("ngaytao", ngaytao);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("update HoaDon");
            
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
	
	public String deleteHoaDon(int id) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("DELETE FROM HoaDon WHERE idHD=:id");
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("delete HoaDon");
            
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
	
	public List<HoaDon> getAllHoaDon() {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM HoaDon ORDER BY idHD DESC");//HQL
		
		List<HoaDon> hoadons = q.getResultList();
		
		return hoadons;
	}
	
	public HoaDon getHoaDonById(int id) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM HoaDon WHERE idHD=:id");//HQL
		
		q.setParameter("id", id);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<HoaDon> hoadons = q.getResultList();
		
		if (hoadons.size() > 0) {
			return hoadons.get(0);
		}
		
		return null;
	}
	
	public float tongDoanhThu(String monthStr) {
		Session session = HibernateUtils.getFactory().openSession();
		
		Query q;
		if (monthStr == null) {
			q = session.createNativeQuery("SELECT "
					+ "SUM(lp.don_gia * DATEDIFF(bk.check_out, bk.check_in)) AS tong_tien "
					+ "FROM hoa_don hd, booking bk, phong p, loai_phong lp "
					+ "WHERE  "
					+ "hd.id_HD = bk.id_HD "
					+ "AND bk.id_phong = p.id_phong "
					+ "AND p.id_loai_phong = lp.id_loai_phong");
		} else {
			monthStr = monthStr.concat("-01");
			
			q = session.createNativeQuery("SELECT "
					+ "SUM(lp.don_gia * DATEDIFF(bk.check_out, bk.check_in)) AS tong_tien "
					+ "FROM hoa_don hd, booking bk, phong p, loai_phong lp "
					+ "WHERE "
					+ "hd.ngay_tao >= :month AND hd.ngay_tao <= last_day(:month) "
					+ "AND hd.id_HD = bk.id_HD "
					+ "AND bk.id_phong = p.id_phong "
					+ "AND p.id_loai_phong = lp.id_loai_phong");
			
			q.setParameter("month", monthStr);
		}
		
		Object rs = q.getSingleResult();
		
		float sum = 0.f;
		if (rs != null) {
			sum = ((Double) rs).floatValue();
		}
		
		System.out.println(sum);
		
		session.close();
		
		return sum;
	}
	
	public List<Object[]> thongKeDoanhThuTheoLoaiPhong(String monthStr) {
		Session session = HibernateUtils.getFactory().openSession();
		
		Query q;
		
		if (monthStr == null) {
			q = session.createNativeQuery("SELECT "
					+ "lp.id_loai_phong, "
					+ "lp.ten_loai_phong, "
					+ "(CASE "
					+ "WHEN temp.tong_tien is NULL THEN 0 "
					+ "ELSE temp.tong_tien "
					+ "END) "
					+ "FROM loai_phong lp left join ("
					+ "SELECT "
					+ "lp.id_loai_phong, "
					+ "lp.ten_loai_phong, "
					+ "SUM(lp.don_gia * DATEDIFF(bk.check_out, bk.check_in)) AS tong_tien "
					+ "FROM hoa_don hd, booking bk, phong p, loai_phong lp "
					+ "WHERE  "
					+ "hd.id_HD = bk.id_HD "
					+ "AND bk.id_phong = p.id_phong "
					+ "AND p.id_loai_phong = lp.id_loai_phong "
					+ "GROUP BY lp.id_loai_phong, lp.ten_loai_phong) temp on lp.id_loai_phong = temp.id_loai_phong ");
		} else {
			monthStr = monthStr.concat("-01");
			System.out.println(monthStr);
			
			q = session.createNativeQuery("SELECT "
					+ "lp.id_loai_phong, "
					+ "lp.ten_loai_phong, "
					+ "(CASE "
					+ "WHEN temp.tong_tien is NULL THEN 0 "
					+ "ELSE temp.tong_tien "
					+ "END) "
					+ "FROM loai_phong lp left join ("
					+ "SELECT "
					+ "lp.id_loai_phong, "
					+ "lp.ten_loai_phong, "
					+ "SUM(lp.don_gia * DATEDIFF(bk.check_out, bk.check_in)) AS tong_tien "
					+ "FROM hoa_don hd, booking bk, phong p, loai_phong lp "
					+ "WHERE "
					+ "hd.ngay_tao >= :month AND hd.ngay_tao <= last_day(:month) "
					+ "AND hd.id_HD = bk.id_HD "
					+ "AND bk.id_phong = p.id_phong "
					+ "AND p.id_loai_phong = lp.id_loai_phong "
					+ "GROUP BY lp.id_loai_phong, lp.ten_loai_phong) temp on lp.id_loai_phong = temp.id_loai_phong ");
			
			q.setParameter("month", monthStr);
		}
		
		List<Object[]> thongKe = (List<Object[]>) q.getResultList();
		
		thongKe.forEach(x -> {
			System.out.printf("%d, %s, %f\n", x[0], x[1], x[2]);
		});
		
		session.close();
		
		return thongKe;
	}
	
	private boolean checkNgayTao(Date checkngaytao) {
		if (getNgayTao(checkngaytao) != null) {
			return true;
		}
		return false;
	}
	
	public HoaDon getNgayTao(Date ngaytao) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM HoaDon WHERE ngayTao=:ngaytao");//HQL
		
		q.setParameter("ngaytao", ngaytao);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<HoaDon> hoadons = q.getResultList();
		
		if (hoadons.size() > 0) {
			return hoadons.get(0);
		}
		
		return null;
	}
}
