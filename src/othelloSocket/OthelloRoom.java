package othelloSocket;

import javax.websocket.Session;

public class OthelloRoom {
	private Session session1;
	private Session session2;
	private OthelloLogic logic;

	public OthelloRoom() {
		OthelloLogic othellologic = new OthelloLogic();
		setLogic(othellologic);
	}

	//セッター
	public void setSession1(Session session) { this.session1=session; }
	public void setSession2(Session session) { this.session2=session; }
	public void setLogic(OthelloLogic logic) { this.logic=logic; }

	//ゲッター
	public Session getSession1() { return session1; }
	public Session getSession2() { return session2; }
	public OthelloLogic getLogic() { return logic; }

	//メッセージ送信
	public void sendMsg(MsgObj obj) {
		if(session1 != null) { session1.getAsyncRemote().sendObject(obj);}
		if(session2 != null) { session2.getAsyncRemote().sendObject(obj);}
	}

	//ポイント送信
	public void sendIndex(IndexObj obj) {
		if(session1 != null) { session1.getAsyncRemote().sendObject(obj);}
		if(session2 != null) { session2.getAsyncRemote().sendObject(obj);}
	}

	//ロジック実行
	public boolean logic(IndexObj obj) {
		boolean isJudgment = logic.logic(obj);
		return isJudgment;
	}

	//ターン情報取得
	public int getNum() {
		return logic.field.getNum();
	}

}
