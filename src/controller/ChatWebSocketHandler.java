package controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class ChatWebSocketHandler implements WebSocketHandler {

	private static ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("用户已添加");
		users.add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		if(session.isOpen()){
			session.close();
		}
		users.remove(session);
		System.out.println("用户已退出");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		String id = (String)session.getAttributes().get("user_id");
		TextMessage tMessage = (TextMessage)message.getPayload();
		sendMessageToUser(id, tMessage);
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

	
	private void sendMessageToUser(String id, TextMessage message){
		for(WebSocketSession receiver: users){
			if(receiver.getAttributes().get("user_id").equals(id)){
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
}
