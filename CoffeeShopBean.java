package homework3;

import java.text.DecimalFormat;
 
public class CoffeeShopBean {
	public int Storeno;
	public String Name;
	public String Phone;
	public String PhoneNumber;
	public String Street;
	public String Street2;
	public String Street3;
	public String City;
	public String State;
	public String Country;
	public int Zipcode;
	public String Latitude;
	public String Longitude;
	public float dst; 
	public int size;

	public CoffeeShopBean(float dst,int Storeno, String Name, String Phone, String Street,
			String City, int Zipcode, String Latitude, String Longitude) {
		this.dst=dst;
		this.Storeno=Storeno;
		this.Name=Name;
		this.Phone=Phone;
		this.Street=Street;
		this.City=City;
		this.Zipcode=Zipcode;
		this.Latitude=Latitude;
		this.Longitude=Longitude;
	}
	
	public double getSize() {
		return size;
	}
	
	public float getDst() {
		return dst;
	}
	
	public int getStoreno() {
		return Storeno;
	}
	
	public String getName() {
		return Name;
	}
	
	public String getPhone() {
		return Phone;
	}
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	
	public String getStreet() {
		return Street;
	}
	
	public String getStreet2() {
		return Street2;
	}
	
	public String getStreet3() {
		return Street3;
	}
	
	public String getCity() {
		return City;
	}
	
	public String getState() {
		return State;
	}
	
	public String getCountry() {
		return Country;
	}
	
	public int getZipcode() {
		return Zipcode;
	}
	
	public String getLatitude() {
		return Latitude;
	}
	
	public String getLongitude() {
		return Longitude;
	}
	
	
}
