package interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import model.User;

public class ChatWebSocketInterceptor implements HandshakeInterceptor {

	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
//		if (request instanceof ServletServerHttpRequest) {
//			ServletServerHttpRequest servletRequest  = (ServletServerHttpRequest) request;
//			HttpServletRequest httpRequest = servletRequest.getServletRequest();
//			//��ȡ��¼�û�ID
//			User user = (User)httpRequest.getAttribute("user");
//			//����attributes�����ڴ���WebSocketSession
//			attributes.put("user_id",user.getID());
//		}
		return true;
	}

}
