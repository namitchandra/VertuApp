package com.example.tabdemo;

public class Weather {
	public String servicename;
	public String Service_Status;
	public String Dealer_Status;
	public String Address;
	public int distance;
	double lat;
	double longi;
	String merchandising;
	
	

	public Weather() {
		super();
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getService_Status() {
		return Service_Status;
	}

	public void setService_Status(String service_Status) {
		Service_Status = service_Status;
	}

	public String getDealer_Status() {
		return Dealer_Status;
	}

	public void setDealer_Status(String dealer_Status) {
		Dealer_Status = dealer_Status;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLongi() {
		return longi;
	}

	public void setLongi(double longi) {
		this.longi = longi;
	}

	public String getMerchandising() {
		return merchandising;
	}

	public void setMerchandising(String merchandising) {
		this.merchandising = merchandising;
	}

	public Weather(String servicename, String Service_Status,String Address,String Dealer_Status,int distance,
			double lat, double longi,String merchandising) {
		super();
		this.servicename = servicename;
		this.Service_Status = Service_Status;
		this.Address = Address;
		this.Dealer_Status= Dealer_Status;
		this.distance=distance;
		this.lat=lat;
		this.longi=longi;
		this. merchandising= merchandising;
	}
}