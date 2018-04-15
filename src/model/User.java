package model;

import java.sql.Timestamp;

public class User {
	private String id;
	private String name;
	private Timestamp yelping_since;
	private String user_name;
	private String password;
	
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
	
	public Timestamp getYelping_since(){
		return this.yelping_since;
	}
	
	public void setYelping_since(Timestamp yelping_since){
		this.yelping_since = yelping_since;
	}
	
	public String getUser_name(){
		return this.user_name;
	}
	
	public void setUser_name(String user_name){
		this.user_name = user_name;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
}
