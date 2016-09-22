package com.example.silpy.entity;

public class PerguruanTinggi {
	private int		id;
	private String	nama_pt;
	//private String	alamat;
	private double	lat;
	private double	lng;

	public PerguruanTinggi()
	{
		// do nothing
	}

	//public PerguruanTinggi(int id, String nama, String alamat, double lat, double lng)
	public PerguruanTinggi(int id, String nama_pt,  double lat, double lng)
	{
		super();
		this.id = id;
		this.nama_pt = nama_pt;
		//this.alamat = alamat;
		this.lat = lat;
		this.lng = lng;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNama()
	{
		return nama_pt;
	}

	public void setNama(String nama_pt)
	{
		this.nama_pt = nama_pt;
	}

//	public String getAlamat()
//	{
//		return alamat;
//	}
//
//	public void setAlamat(String alamat)
//	{
//		this.alamat = alamat;
//	}

	public double getLat()
	{
		return lat;
	}

	public void setLat(double lat)
	{
		this.lat = lat;
	}

	public double getLng()
	{
		return lng;
	}

	public void setLng(double lng)
	{
		this.lng = lng;
	}
}
