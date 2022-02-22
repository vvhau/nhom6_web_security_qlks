package com.nhom6.qlks.hibernate.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "khach_hang")
public class KhachHang implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_khach", nullable = false)
	private int idKhach;
	
	@Column(name = "ho_ten", nullable = false)
	private String hoTen;
	
	@Column(name = "cmnd", nullable = false, unique = true)
	private String cmnd;
	
	@Column(name = "dia_chi", nullable = false)
	private String diaChi;
	
	@ManyToOne
	@JoinColumn(name = "id_booking")
	private Booking booking;

	public int getIdKhach() {
		return idKhach;
	}

	public void setIdKhach(int idKhach) {
		this.idKhach = idKhach;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}
