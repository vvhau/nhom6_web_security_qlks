package com.nhom6.qlks.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.HoaDon;
import com.nhom6.qlks.hibernate.pojo.KhachHang;
import com.nhom6.qlks.hibernate.pojo.User;

public class Utils {
	public String strToMD5(String str) {
		String hashtext = null;
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(str.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			hashtext = bigInt.toString(16);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return hashtext;
	}
	
	public String checkRegister(User user) {
		String err_msg = null;
		
		if (user.getHoTen().length() == 0) {
			return err_msg = "Thiếu họ tên";
		}
		
		if (user.getGioiTinh() == null) {
			return err_msg = "Thiếu giới tính";
		}
		
		if (user.getCmnd().length() == 0) {
			return err_msg = "Thiếu số chứng mình nhân dân";
		}
		
		if (user.getEmail().length() == 0) {
			return err_msg = "Thiếu địa chỉ email";
		}
		
		if (user.getSdt().length() == 0) {
			return err_msg = "Thiếu số điện thoại";
		}
		
		if (user.getTenDangNhap().length() == 0) {
			return err_msg = "Thiếu tên username";
		}
		
		if (user.getMatKhau().length() == 0) {
			return err_msg = "Thiếu password";
		}
		
		return err_msg;
	}

	public static int getRentalDays(Date checkIn, Date checkOut) {
		int sub = 0;
		long difference_In_Time = checkOut.getTime() - checkIn.getTime(); 
		sub = (int) (difference_In_Time / (1000 * 60 * 60 * 24));
		return sub;
	}
	
	public static float calcTotalPriceBooking(Booking booking) {
		float donGia = booking.getPhong().getLoaiPhong().getDonGia();
		int soNgayThue = getRentalDays(booking.getCheckIn(), booking.getCheckOut());
		
		float totalPrice = donGia * soNgayThue;
		
		return totalPrice;
	}
	
	public static float calcTotalPriceBill(HoaDon hoaDon) {
		float totalPrice = 0;
		
		for (Booking bk : hoaDon.getBookings()) {
			totalPrice += calcTotalPriceBooking(bk);
		}
		
		return totalPrice;
	}
}
