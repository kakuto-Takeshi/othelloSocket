package othelloSocket;

public class MsgObj extends BaseObj{
	private String type;
	private String turn;
	private String msg;

	//コンストラクタ
	public MsgObj() {}

	//セッター
	public void setType(String type) {this.type = type;}
	public void setTurn(String turn) {this.turn = turn;}
	public void setMsg(String msg) {this.msg = msg;}

	//ゲッター
	public String getType() {return type;}
	public String getTurn() {return turn;}
	public String getMsg() {return msg;}
}