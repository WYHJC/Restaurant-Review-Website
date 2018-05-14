package model;

import java.sql.Timestamp;

public class Comment {
	private String id;
	private String business_id;
	private String user_id;
	private float stars;
	private Timestamp date;
	private String text;
	private String user_name;
	
	public String getID(){
		return this.id;
	}
	
	public void setID(String id){
		this.id = id;
	}
	public String getUser_id(){
		return this.user_id;
	}
	
	public void setUser_id(String user_id){
		this.user_id = user_id;
	}
	
	public String getbusiness_id(){
		return this.business_id;
	}
	
	public void setbusiness_id(String business_id){
		this.business_id = business_id;
	}

	public float getStars(){
		return this.stars;
	}
	
	public void setStars(float stars){
		this.stars = stars;
	}
	public Timestamp getDate(){
		return this.date;
	}
	
	public void setDate(Timestamp date){
		this.date = date;
	}
	public String getText(){
		return this.text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	public String getUser_name(){
		return this.user_name;
	}
	
	public void setUser_name(String user_name){
		this.user_name = user_name;
	}
}
