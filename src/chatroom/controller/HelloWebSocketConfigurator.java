package chatroom.controller;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class HelloWebSocketConfigurator extends Configurator {
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		
	HttpSession session = (HttpSession)request.getHttpSession();
	String userId = (String)session.getAttribute("userId");
	
	//@serverEndpoint 클래스에서 접근할 수 있도록 UserProperties객채(map)에 저장
	Map<String,Object> userProp = sec.getUserProperties();
	userProp.put("userId", userId);
	
	}
}
