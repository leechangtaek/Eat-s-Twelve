package chatroom.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;

@ServerEndpoint(value ="/chat/helloWebSocket",
	configurator=HelloWebSocketConfigurator.class)
public class HelloWebSocket {

	//접속 사용자를 관리하기 위한 map<userId, websocketSession>
	public static Map<String, Session> clients = new HashMap<>();
	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		try {
			Map<String, Object> configUserProp = config.getUserProperties();
			String userId = (String)configUserProp.get("userId");
			
			
			//접속 사용자 관리를 위해서 clients에 저장
			clients.put(userId, session);
			
			//session의 UserProperties에 사용자 아이디 저장 -> @Onclose 에서 사용
			Map<String,Object> sessionUserProp = session.getUserProperties();
			sessionUserProp.put("userId", userId);
			
			//다른 사용자에게 접속메세지 보냄.
			JSONObject msg = new JSONObject();
			msg.put("type", "welcome");
			msg.put("msg", "["+userId+"]님이 입장했습니다.");
			msg.put("sender", userId);
			msg.put("time", System.currentTimeMillis()); //unix time으로 시각관리
			
			//전송
			onMessage(msg.toJSONString(),session);
		}catch(Exception e) {
			throw e;
		}
		
	}
	
	@OnMessage
	public void onMessage(String msg, Session session) {
//		System.out.println("msg@onMessage="+msg);
		
		//동기화처리: message전송 업무중 clients 객체의 변형이 일어나선 안된다.
		//다른 쓰레드로부터 객체 보호. lock(획득!)
		synchronized (clients) {
			Collection<Session> sessionList = clients.values();
			
			for(Session client : sessionList) {
				
				try {
					//클라이언트 전송담당 객체
					Basic basic = client.getBasicRemote();
					basic.sendText(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onclose(Session session) {
		
		Map<String,Object> sessionUserProp = session.getUserProperties();
		String userId = (String)sessionUserProp.get("userId");
		sessionUserProp.put("userId", userId);
		
		//1.clients에서 해당 session제거
		clients.remove(userId);
		//2.다른 사용자에게 퇴장메세지 보냄.
		JSONObject msg = new JSONObject();
		msg.put("type", "adieu");
		msg.put("msg", "["+userId+"]님이 퇴장했습니다.");
		msg.put("sender", userId);
		msg.put("time", System.currentTimeMillis()); //unix time으로 시각관리
		
		//전송
		onMessage(msg.toJSONString(),session);
		
		
//		System.out.println("현재접속자수: "+clients.size());
	}
	
}
