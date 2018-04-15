package model;

public class Business {
	private String id;
	private String name;
	private String address;
	private String city;
	private float stars;
	private int review_count;
	private int is_open;
	
	public String getID(){
		return this.id;
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getCity(){
		return this.city;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public float getStars(){
		return this.stars;
	}
	
	public void setStars(float stars){
		this.stars = stars;
	}
	
	public int getReview_count(){
		return this.review_count;
	}
	
	public void setReview_count(int review_count){
		this.review_count = review_count;
	}
	
	public int getIs_open(){
		return this.is_open;
	}
	
	public void setIs_open(int is_open){
		this.is_open = is_open;
	}
}
