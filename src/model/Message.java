package model;

import java.sql.Timestamp;

public class Message {
	private String id;
	private String content;
	private String sender;
	private String receiver;
	private Timestamp time;
	
	public String getID(){
		return this.id;
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getSender(){
		return this.sender;
	}
	
	public void setSender(String sender){
		this.sender = sender;
	}
	
	public String getReceiver(){
		return this.receiver;
	}
	
	public void setReceiver(String receiver){
		this.receiver = receiver;
	}
	
	public Timestamp getTime(){
		return this.time;
	}
	
	public void setTime(Timestamp time){
		this.time = time;
	}
}
