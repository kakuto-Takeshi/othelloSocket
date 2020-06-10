package othelloSocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/othello" ,
	decoders = {Decoders.MsgDecoder.class , Decoders.IndexDecoder.class} ,
	encoders = {Encoders.MsgEncoder.class , Encoders.IndexEncoder.class})
public class OthelloSocket {
	private static List<OthelloRoom> room = new CopyOnWriteArrayList<>();

	@OnOpen//クライアントと接続したとき
    public void onOpen(Session mySession) {
        System.out.println("connect ID:"+mySession.getId());
        if(Integer.parseInt(mySession.getId() , 16)%2 == 0) {
        	OthelloRoom myRoom = new OthelloRoom();
        	myRoom.setSession1(mySession);
        	room.add(myRoom);
        	MsgObj msg = new MsgObj("対戦相手を探しています。");
        	mySession.getAsyncRemote().sendObject(msg);
    		msg.setTurn("myTurn");
    		msg.setMsg("black");
    		mySession.getAsyncRemote().sendObject(msg);
        } else {
        	int roomIndex=Integer.parseInt(mySession.getId() , 16)/2;
        	OthelloRoom myRoom=room.get(roomIndex);
        	myRoom.setSession2(mySession);
        	if(myRoom.getSession1() != null && myRoom.getSession2() != null) {
        		MsgObj msg = new MsgObj("対戦相手が見つかりました。");
        		myRoom.sendMsg(msg);
        		msg.setTurn("myTurn");
        		msg.setMsg("white");
        		mySession.getAsyncRemote().sendObject(msg);
        	} else if(myRoom.getSession1() == null) {
        		MsgObj msg = new MsgObj("対戦相手がいません。リロードしてください。");
    			mySession.getAsyncRemote().sendObject(msg);
        	}
        }
    }

    @OnMessage//クライアントからデータを受信したとき
    public void onMessage(BaseObj obj , Session mySession) {
    	int roomIndex=Integer.parseInt(mySession.getId() , 16)/2;
    	OthelloRoom myRoom = room.get(roomIndex);
    	if(obj instanceof MsgObj) {
    		MsgObj msgObj = (MsgObj) obj;
    		if(msgObj.getMsg() == "") {
    			msgObj.setMsg("入力されていません。");
    			mySession.getAsyncRemote().sendObject(msgObj);
    		} else {
    			if(Integer.parseInt(mySession.getId() , 16)%2 == 0) {msgObj.setTurn("black");}
        		else {msgObj.setTurn("white");}
    	    	myRoom.sendMsg(msgObj);
    		}

    	} else if (obj instanceof IndexObj){
    		IndexObj indexObj = (IndexObj) obj;
    		if(myRoom.getSession1() != null && myRoom.getSession2() != null) {
	    		if(Integer.parseInt(mySession.getId() , 16)%2 == myRoom.getNum()%2) {
	    			boolean isJudgment = myRoom.logic(indexObj);
	    	    	if(isJudgment) {
	    	    		myRoom.sendIndex(indexObj);
	    	    		if(indexObj.getNoNum() == 0) {
	    	    			MsgObj msg = new MsgObj("Game Set !!");
	    	        		myRoom.sendMsg(msg);
	    	        		if(indexObj.getBlackNum() > indexObj.getWhiteNum()) {
	    	        			msg.setMsg("黒の勝ち！");
	    	        		} else if (indexObj.getBlackNum() < indexObj.getWhiteNum()) {
	    	        			msg.setMsg("白の勝ち！");
	    	        		} else {
	    	        			msg.setMsg("引き分け！");
	    	        		}
	    	        		myRoom.sendMsg(msg);
	    	    		}
	    	    	} else {
	    	    		MsgObj msg = new MsgObj("その場所には置けません。");
	        			mySession.getAsyncRemote().sendObject(msg);
	    	    	}
	    		} else {
	    			MsgObj msg = new MsgObj("相手のターンです。");
	    			mySession.getAsyncRemote().sendObject(msg);
	    		}
    		} else {
    			MsgObj msg = new MsgObj("対戦相手がいません。");
    			mySession.getAsyncRemote().sendObject(msg);
    		}
    	}
    }

    @OnClose//クライアントが切断したとき
    public void onClose(Session mySession) {
    	//インスタンス内のsession情報を削除
    	int roomIndex=Integer.parseInt(mySession.getId() , 16)/2;
    	OthelloRoom myRoom = room.get(roomIndex);
    	if(Integer.parseInt(mySession.getId() , 16)%2 == 0) {
    		myRoom.setSession1(null);
    	} else {
    		myRoom.setSession2(null);
        }
    	MsgObj msg = new MsgObj("対戦相手が切断しました。");
    	myRoom.sendMsg(msg);
    	//通信を切断
    	System.out.println("disconnect ID:"+mySession.getId());
        try {
			mySession.close();
		} catch (IOException e) {
			System.err.println("エラーが発生しました: " + e);
		}
    }

}
