package othelloSocket;

public class OthelloField {
	private String[][] table=new String[8][8];//8*8のテーブル
	private int x;//x座標
	private int y;//y座標
	private int w;//白の数
	private int b;//黒の数
	private int no;//空白の数
	private int re;//ひっくり返した数
	private String turn;//白のターンか黒のターンか

	//コンストラクタ
	public OthelloField() {
		//tableの初期化を行う
		for(int i=0;i<table.length;i++)
			for(int j=0;j<table[i].length;j++) table[i][j]="no";
		table[3][3]="w";
		table[3][4]="b";
		table[4][3]="b";
		table[4][4]="w";
		setTable(table);//初期化したtableをセットする
		setTurn("b");
	}

	//セッター
	public void setTable(String table[][]) {this.table=table;}
	public void setX(int x) {this.x=x;}
	public void setY(int y) {this.y=y;}
	public void setW(int w) {this.w=w;}
	public void setB(int b) {this.b=b;}
	public void setNo(int no) {this.no=no;}
	public void setRe(int re) {this.re=re;}
	public void setTurn(String turn) {this.turn=turn;}

	//ゲッター
	public String[][] getTable() {return table;}
	public int getX() {return x;}
	public int getY() {return y;}
	public int getW() {return w;}
	public int getB() {return b;}
	public int getNo() {return no;}
	public int getRe() {return re;}
	public String getTurn() {return turn;}
}
