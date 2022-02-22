package com.nhom6.qlks.hibernate.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "phong")
public class Phong implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_phong", nullable = false)
	private int idPhong;
	
	@Column(name = "ten_phong", nullable = false)
	private String tenPhong;
	
	@ManyToOne
	@JoinColumn(name = "id_loai_phong", nullable = false)
	private LoaiPhong loaiPhong;
	
	@ManyToOne
	@JoinColumn(name = "id_trang_thai", nullable = false)
	private TrangThai trangThai;
	
	@OneToMany(mappedBy = "phong")
	private List<Booking> bookings;

	
	public Phong() {}
	
	public Phong(String roomName, LoaiPhong roomType, TrangThai roomStatus) {
		super();
		this.tenPhong = roomName;
		this.loaiPhong = roomType;
		this.trangThai = roomStatus;
	}

	public int getIdPhong() {
		return idPhong;
	}

	public void setIdPhong(int idPhong) {
		this.idPhong = idPhong;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public TrangThai getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThai trangThai) {
		this.trangThai = trangThai;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
}
