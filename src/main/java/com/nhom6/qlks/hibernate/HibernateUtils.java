package com.nhom6.qlks.hibernate;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.HoaDon;
import com.nhom6.qlks.hibernate.pojo.KhachHang;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.hibernate.pojo.Quyen;
import com.nhom6.qlks.hibernate.pojo.TrangThai;
import com.nhom6.qlks.hibernate.pojo.User;

public class HibernateUtils {
	private static final SessionFactory FACTORY;

	static {
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		
//		Properties props = new Properties();
//		props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//		props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//		props.put(Environment.URL, "jdbc:mysql://localhost/tuan9");
//		props.put(Environment.USER, "root");
//		props.put(Environment.PASS, "1234");
//		props.put(Environment.SHOW_SQL, "true");
//		
//		conf.setProperties(props);
		conf.addAnnotatedClass(User.class);
		conf.addAnnotatedClass(LoaiPhong.class);
		conf.addAnnotatedClass(Booking.class);
		conf.addAnnotatedClass(HoaDon.class);
		conf.addAnnotatedClass(KhachHang.class);
		conf.addAnnotatedClass(Phong.class);
		conf.addAnnotatedClass(Quyen.class);
		conf.addAnnotatedClass(TrangThai.class);
		
		ServiceRegistry registry =  new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties()).build();
		FACTORY = conf.buildSessionFactory(registry);
	}
	
	public static SessionFactory getFactory() {
		return FACTORY;
	}
	
}
