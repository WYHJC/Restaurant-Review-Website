package model;

import java.sql.Timestamp;

public class Message {
	private String chatRoomName;
	private String type;          //判断客户端发送来的消息是初始登录还是聊天消息
	private String content;
	private String sender;
	private String receiver;
	private Timestamp time;
	
	public String getChatRoomName(){
		return this.chatRoomName;
	}
	
	public void setChatRoomName(String chatRoomName){
		this.chatRoomName = chatRoomName;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
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
