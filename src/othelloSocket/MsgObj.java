package othelloSocket;

public class MsgObj extends BaseObj{
	private String type;
	private String turn;
	private String msg;

	//コンストラクタ
	public MsgObj() {
		type = "msg";
		turn = "神";
	}
	public MsgObj(String text) {
		type = "msg";
		turn = "神";
		msg = text;
	}

	//セッター
	public void setType(String type) {this.type = type;}
	public void setTurn(String turn) {this.turn = turn;}
	public void setMsg(String msg) {this.msg = msg;}

	//ゲッター
	public String getType() {return type;}
	public String getTurn() {return turn;}
	public String getMsg() {return msg;}
}