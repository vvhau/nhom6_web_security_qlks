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
@Table(name = "trang_thai")
public class TrangThai implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trang_thai", nullable = false)
	private int idTrangThai;
	
	@Column(name = "ten_trang_thai", nullable = false)
	private String tenTrangThai;
	
	@OneToMany(mappedBy = "trangThai")
	private List<Phong> phongs;

	public int getIdTrangThai() {
		return idTrangThai;
	}

	public void setIdTrangThai(int idTrangThai) {
		this.idTrangThai = idTrangThai;
	}

	public String getTenTrangThai() {
		return tenTrangThai;
	}

	public void setTenTrangThai(String tenTrangThai) {
		this.tenTrangThai = tenTrangThai;
	}

	public List<Phong> getPhongs() {
		return phongs;
	}

	public void setPhongs(List<Phong> phongs) {
		this.phongs = phongs;
	}
	

}
