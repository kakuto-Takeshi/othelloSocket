package othelloSocket;

import java.util.ArrayList;

public class IndexObj extends BaseObj{
	private String type;
	private int turnNum;
	private int wNum;
	private int bNum;
	private int noNum;
	private int index;
	private ArrayList<Integer> points = new ArrayList<>();

	//コンストラクタ
	public IndexObj() {
		type = "index";
		wNum = 2;
		bNum = 2;
		noNum = 60;
	}

	//セッター
	public void setType(String type) {this.type = type;}
	public void setTurnNum(int turnNum) {this.turnNum = turnNum;}
	public void setWNum(int wNum) {this.wNum = wNum;}
	public void setBNum(int bNum) {this.bNum = bNum;}
	public void setNoNum(int noNum) {this.noNum = noNum;}
	public void setIndex(int index) {this.index = index;}
	public void setPoints(ArrayList<Integer> points) {this.points = points;}

	//ゲッター
	public String getType() {return type;}
	public int getTurnNum() {return turnNum;}
	public int getWNum() {return wNum;}
	public int getBNum() {return bNum;}
	public int getNoNum() {return noNum;}
	public int getIndex() {return index;}
	public ArrayList<Integer> getPoints() {return points;}
}
