package othelloSocket;

import java.util.ArrayList;

public class IndexObj extends BaseObj{
	private String type;
	private int num;
	private int index;
	private ArrayList<Integer> points = new ArrayList<>();

	//コンストラクタ
	public IndexObj() {
		type = "index";
	}

	//セッター
	public void setType(String type) {this.type = type;}
	public void setNum(int num) {this.num = num;}
	public void setIndex(int index) {this.index = index;}
	public void setPoints(ArrayList<Integer> points) {this.points = points;}

	//ゲッター
	public String getType() {return type;}
	public int getNum() {return num;}
	public int getIndex() {return index;}
	public ArrayList<Integer> getPoints() {return points;}
}
