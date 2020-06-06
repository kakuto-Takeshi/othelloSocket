package othelloSocket;

import java.util.ArrayList;

public class IndexObj extends BaseObj{
	private String type;
	private String turn;
	private int index;
	private ArrayList<Integer> points = new ArrayList<>();

	//コンストラクタ
	public IndexObj() {}

	//セッター
	public void setType(String type) {this.type = type;}
	public void setTurn(String turn) {this.turn = turn;}
	public void setIndex(int index) {this.index = index;}
	public void setPoints(ArrayList<Integer> points) {this.points = points;}

	//ゲッター
	public String getType() {return type;}
	public String getTurn() {return turn;}
	public int getIndex() {return index;}
	public ArrayList<Integer> getPoints() {return points;}
}
