package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import model.Message;
import util.MessageDecodeUtil;
import util.MessageEncodeUtil;

@Component
public class ChatWebSocketHandler implements WebSocketHandler {

	//存储登录用户各聊天室以及每个聊天室的客户端
	private static HashMap<String, ArrayList<WebSocketSession>> chatRooms = new HashMap<String, ArrayList<WebSocketSession>>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("用户已添加");
		String chatRoomName = (String)session.getAttributes().get("chatRoomName");
		if(chatRooms.get(chatRoomName) == null){
			createChatRoom(chatRoomName);
		}
		//System.out.println(chatRoomName);
		chatRooms.get(chatRoomName).add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		if(session.isOpen()){
			session.close();
		}
//		String chatRoomName = (String)session.getAttributes().get("business_name");
//		chatRooms.get(chatRoomName).remove(session);
//		System.out.println("用户已退出");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("收到消息");
		//String chatRoomName = (String)session.getAttributes().get("business_name");
		//String msg = (String)message.getPayload();
		Message msg = MessageDecodeUtil.decode((String)message.getPayload());
		String chatRoomName = msg.getChatRoomName();
		
		msg.setTime(new Timestamp(System.currentTimeMillis()));
		String json = MessageEncodeUtil.encode(msg);		
		TextMessage tm = new TextMessage(json);
//		if(msg != null){
//			System.out.println(msg.getChatRoomName() + "\n" + msg.getContent() + "\n" + msg.getSender() + "\n" + msg.getReceiver() + "\n" + msg.getTime());
//		}
		sendMessageToChatRoom(chatRoomName, tm);
	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

	//用户点击拼桌按钮后，创建新聊天室
	private void createChatRoom(String chatRoomName){
		ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
		chatRooms.put(chatRoomName, users);
	}
	
	private void sendMessageToChatRoom(String chatRoomName, TextMessage message){
		for(WebSocketSession receiver: chatRooms.get(chatRoomName)){
			if(receiver.isOpen()){
				try {
					//String json = MessageEncodeUtil.encode(message);
					receiver.sendMessage(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
