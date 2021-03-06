package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import interceptor.ChatWebSocketInterceptor;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class ChatController implements WebSocketConfigurer {
	
	@Autowired
	ChatWebSocketHandler handler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addHandler(handler,"/ws.do").addInterceptors(new ChatWebSocketInterceptor());
        registry.addHandler(handler, "/ws/sockjs.do").addInterceptors(new ChatWebSocketInterceptor()).withSockJS();
	}
	
}
