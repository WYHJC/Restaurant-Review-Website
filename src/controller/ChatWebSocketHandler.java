package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ChatWebSocketHandler implements WebSocketHandler {

	private static HashMap<String, ArrayList<WebSocketSession>> chatRooms = new HashMap<String, ArrayList<WebSocketSession>>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("用户已添加");
		//String chatRoomName = (String)session.getAttributes().get("business_name");
		//chatRooms.get(chatRoomName).add(session);
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
		String msg = (String)message.getPayload();
		System.out.println(msg);
		//sendMessageToChatRoom(chatRoomName, tMessage);
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

	private void createChatRoom(){
		
	}
	
	private void sendMessageToChatRoom(String chatRoomName, TextMessage message){
		for(WebSocketSession receiver: chatRooms.get(chatRoomName)){
			if(receiver.isOpen()){
				try {
					receiver.sendMessage(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
