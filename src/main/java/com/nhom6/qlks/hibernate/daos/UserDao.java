package com.nhom6.qlks.hibernate.daos;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nhom6.qlks.hibernate.HibernateUtils;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.hibernate.pojo.Quyen;
import com.nhom6.qlks.hibernate.pojo.TrangThai;
import com.nhom6.qlks.hibernate.pojo.User;

public class UserDao {
	public String insertUser(User user) {
		String err_msg = "";
		
		if (checkUsername(user.getTenDangNhap())) {
    		return err_msg = "Username đã tồn tại";
        }
        
        if (checkEmail(user.getEmail())) {
        	return err_msg = "Địa chỉ email đã tồn tại";
        }
        
        if (checkIdCard(user.getCmnd())) {
        	return err_msg = "Số chứng minh nhân dân đã tồn tại";
        }
        
        if (checkPhone(user.getSdt())) {
        	return err_msg = "Số điện thoại đã tồn tại";
        }
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            // save the object
            session.save(user);       
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
	
	public String resetPassword(String username, String newPassword) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("UPDATE User SET matKhau=:newPassword"
            		+ " WHERE tenDangNhap=:username");
			query.setParameter("newPassword", newPassword);
			query.setParameter("username", username);
			int result = query.executeUpdate();
			
            System.out.println("update user");
            
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
	
	public String changePassword(String username, String oldPassword, String newPassword) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("UPDATE User SET matKhau=:newPassword"
            		+ " WHERE tenDangNhap=:username"
            		+ " AND matKhau=:oldPassword");
            
			query.setParameter("newPassword", newPassword);
			query.setParameter("username", username);
			query.setParameter("oldPassword", oldPassword);
			
			int result = query.executeUpdate();
			System.out.println(result);
            System.out.println("update user");
            
            // commit transaction
            transaction.commit();
            System.out.println("commited transaction");
            if(result==1) {            	
            	err_msg = "thành công";
            }
            else {
            	err_msg = "that bai";
            }
        } catch (Exception e) {
            if (transaction != null) {
            	System.out.println("roll back transaction");
                transaction.rollback();
                err_msg = "sai mật khẩu	";
            }
            e.printStackTrace();
        } finally {
        	   session.close();
        }
        return err_msg;
	}
	
	public String deleteUser(int id) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
            
            Query query = session.createQuery("DELETE FROM User WHERE id=:id");
			query.setParameter("id", id);
			int result = query.executeUpdate();
			
            System.out.println("update user");
            
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
	
	private boolean checkUsername(String username) {
		if (getUserByUsername(username) != null) {
			return true;
		}
		return false;
	}
	
	private boolean checkEmail(String email) {
		if (getUserByEmail(email) != null) {
			return true;
		}
		return false;
	}
	
	private boolean checkIdCard(String idCard) {
		if (getUserByIdCard(idCard) != null) {
			return true;
		}
		return false;
	}
	
	private boolean checkPhone(String phone) {
		if (getUserByPhone(phone) != null) {
			return true;
		}
		return false;
	}
	public User getUserByid(int id) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM User WHERE id=:id");//HQL có bảng user đợi chút seảch gg
		
		q.setParameter("id", id);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<User> users = q.getResultList();
		
		if (users.size() > 0) {
			return users.get(0);
		}
		
		return null;
	}
	
	public User getUserByUsername(String username) {
        Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM User WHERE tenDangNhap=:username");//HQL
		
		q.setParameter("username", username);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<User> users = q.getResultList();
		
		if (users.size() > 0) {
			return users.get(0);
		}
		
		return null;
	}
	
	public User getUserByEmail(String email) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM User WHERE email=:email");//HQL
		
		q.setParameter("email", email);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<User> users = q.getResultList();
		
		if (users.size() > 0) {
			return users.get(0);
		}
		
		return null;
	}
	
	public User getUserByIdCard(String idCard) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM User WHERE cmnd=:cmnd");//HQL
		
		q.setParameter("cmnd", idCard);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<User> users = q.getResultList();
		
		if (users.size() > 0) {
			return users.get(0);
		}
		
		return null;
	}
	
	public User getUserByPhone(String phone) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM User WHERE sdt=:sdt");//HQL
		
		q.setParameter("sdt", phone);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<User> users = q.getResultList();
		
		if (users.size() > 0) {
			return users.get(0);
		}
		
		return null;
	}
	
	public List<User> getAllUser() {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM User");
		
		List<User> users = q.getResultList();
		
		return users;
	}

	public boolean loginUser(String username, String password, Quyen quyen) {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.
				createQuery("FROM User WHERE tenDangNhap=:username "
						+ "AND matKhau=:password "
						+ "AND quyen=:quyen "
						+ "AND kichHoat=true");//HQL
		q.setParameter("username", username);
		q.setParameter("password", password);
		q.setParameter("quyen", quyen);
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		List<User> users = q.getResultList();
		
		if (users.size() == 1) {
			return true;
		}
		
		return false;
	}

	public List<User> getAllUserEmployee() {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM User WHERE quyen=2 OR quyen=1");//HQL
		
		List<User> users = q.getResultList();
		
		return users;
	}
	public List<User> getAllUserCustomer() {
		Session session = HibernateUtils.getFactory().openSession();
		Query q = session.createQuery("FROM User WHERE quyen=3");//HQL
		
		List<User> users = q.getResultList();
		
		return users;
	}
	
	public String updateUserQuyen(int id,boolean kichhoat) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
          
			
			User user = (User)session.get(User.class, id);
	
			user.setKichHoat(kichhoat);
		
            session.update(user);
            System.out.println("update user");
            
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
	public String updateUser(int id,String hoten,Date ngaysinh,String gioitinh,
			String cmnd, String email, String sdt, String tenDangNhap, boolean kichhoat, Quyen quyen) {
		String err_msg = "";
		
		Transaction transaction = null;
        Session session = HibernateUtils.getFactory().openSession();
        
        try {
            // start a transaction
            transaction = session.beginTransaction();            
            System.out.println("created transaction");
          
			
			User user = (User)session.get(User.class,id);
			user.setHoTen(hoten);
	
			user.setCmnd(cmnd);
			user.setEmail(email);
			user.setSdt(sdt);
			user.setTenDangNhap(tenDangNhap);			
			user.setGioiTinh(gioitinh);
			user.setNgaySinh(ngaysinh);
			user.setQuyen(quyen);
			user.setKichHoat(kichhoat);
		
            session.update(user);
            System.out.println("update user");
            
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
	
}

