package othelloSocket;

import java.util.ArrayList;

public class IndexObj extends BaseObj{
	private String type;
	private int turnNum;
	private int whiteNum;
	private int blackNum;
	private int noNum;
	private int index;
	private ArrayList<Integer> points = new ArrayList<>();

	//コンストラクタ
	public IndexObj() {
		type = "index";
		whiteNum = 2;
		blackNum = 2;
		noNum = 60;
	}

	//セッター
	public void setType(String type) {this.type = type;}
	public void setTurnNum(int turnNum) {this.turnNum = turnNum;}
	public void setWhiteNum(int whiteNum) {this.whiteNum = whiteNum;}
	public void setBlackNum(int blackNum) {this.blackNum = blackNum;}
	public void setNoNum(int noNum) {this.noNum = noNum;}
	public void setIndex(int index) {this.index = index;}
	public void setPoints(ArrayList<Integer> points) {this.points = points;}

	//ゲッター
	public String getType() {return type;}
	public int getTurnNum() {return turnNum;}
	public int getWhiteNum() {return whiteNum;}
	public int getBlackNum() {return blackNum;}
	public int getNoNum() {return noNum;}
	public int getIndex() {return index;}
	public ArrayList<Integer> getPoints() {return points;}
}
