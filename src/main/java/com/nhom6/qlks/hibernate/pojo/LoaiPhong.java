package com.nhom6.qlks.hibernate.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "loai_phong")
public class LoaiPhong implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_loai_phong", nullable = false)
	private int idLoaiPhong;
	
	@Column(name = "ten_loai_phong", nullable = false)
	private String tenLoaiPhong;
	
	@Column(name = "hinh_anh", nullable = false)
	private String hinhAnh;
	
	@Column(name = "don_gia")
	private float donGia;
	
	@Column(name = "so_nguoi")
	private int soNguoi;
	
	@Column(name = "ghi_chu")
	private String ghiChu;
	
	@OneToMany(mappedBy = "loaiPhong")
	private List<Phong> phongs;
	
	
	public LoaiPhong() {}
	
	public LoaiPhong(String roomTypeName, String roomTypeImage, Float roomTypeUnitPrice, Integer roomTypeNumPeople, String roomTypeNote) {
		super();
		this.tenLoaiPhong = roomTypeName;
		this.hinhAnh = roomTypeImage;
		this.donGia = roomTypeUnitPrice;
		this.soNguoi = roomTypeNumPeople;
		this.ghiChu = roomTypeNote;
	}

	
	public int getIdLoaiPhong() {
		return idLoaiPhong;
	}
	public void setIdLoaiPhong(int idLoaiPhong) {
		this.idLoaiPhong = idLoaiPhong;
	}
	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}
	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public float getDonGia() {
		return donGia;
	}
	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}
	public int getSoNguoi() {
		return soNguoi;
	}
	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public List<Phong> getPhongs() {
		return phongs;
	}
	public void setPhongs(List<Phong> phongs) {
		this.phongs = phongs;
	}

}
