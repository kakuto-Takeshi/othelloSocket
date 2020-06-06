package othelloSocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/othello" , decoders = {MsgDecoder.class , IndexDecoder.class} , encoders = {MsgEncoder.class , IndexEncoder.class})
public class OthelloSocket {
	private static List<OthelloRoom> room = new CopyOnWriteArrayList<>();

	@OnOpen//クライアントと接続したとき
    public void onOpen(Session mySession) {
        System.out.println("connect ID:"+mySession.getId());
        if(Integer.parseInt(mySession.getId() , 16)%2 == 0) {
        	OthelloRoom myRoom = new OthelloRoom();
        	myRoom.setSession1(mySession);
        	room.add(myRoom);
        } else {
        	int roomIndex=Integer.parseInt(mySession.getId() , 16)/2;
        	OthelloRoom myRoom=room.get(roomIndex);
        	myRoom.setSession2(mySession);
        }
    }

    @OnMessage//クライアントからデータを受信したとき
    public void onMessage(BaseObj obj , Session mySession) {
    	int roomIndex=Integer.parseInt(mySession.getId() , 16)/2;
    	OthelloRoom myRoom = room.get(roomIndex);
    	if(obj instanceof MsgObj) {
	    	myRoom.sendMsg((MsgObj)obj);
    	} else if (obj instanceof IndexObj){
	    	myRoom.logic((IndexObj)obj);
	    	myRoom.sendIndex((IndexObj)obj);
    	}
    }

    @OnClose//クライアントが切断したとき
    public void onClose(Session mySession) {
    	//インスタンス内のsessionを削除
    	int roomIndex=Integer.parseInt(mySession.getId() , 16)/2;
    	OthelloRoom myRoom = room.get(roomIndex);
    	if(Integer.parseInt(mySession.getId() , 16)%2 == 0) {
    		myRoom.setSession1(null);
    	} else {
    		myRoom.setSession2(null);
        }
    	//通信を切断
    	System.out.println("disconnect ID:"+mySession.getId());
        try {
			mySession.close();
		} catch (IOException e) {
			System.err.println("エラーが発生しました: " + e);
		}
    }

}
