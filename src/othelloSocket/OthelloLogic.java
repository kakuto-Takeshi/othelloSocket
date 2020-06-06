package othelloSocket;

import java.util.ArrayList;

public class OthelloLogic {
	OthelloField  field=null;
	ArrayList<Integer> pointList = new ArrayList<>();

	//コンストラクタ
	public OthelloLogic() {
		field = new OthelloField();
	}

	//メイン的な
	public void logic(IndexObj obj) {
		pointList.clear();
		point(obj.getIndex());
		put();
		obj.setPoints(pointList);
	}

	//xy座標セット
	public void point(int index) {
		int x=index%8;
		int y=index/8;
		field.setX(x);
		field.setY(y);
	}

	//石を置くメソッド
	public void put() {
		String[][] table=field.getTable();			//fieldインスタンスから現在のテーブルを取得

//		for(int i=0;i<8;i++) {
//			for(int j=0;j<8;j++) {
//				System.out.print(table[i][j]+",");
//			}
//			System.out.println();
//		}

		int x=field.getX();								//x,yを取得
		int y=field.getY();
		String turn=field.getTurn();					//白か黒か、どちらが置いたかを取得

		int n=1;												//置いたところからの距離
		int reSum=0;										//全方向でひっくり返す数
		for(int i=-1;i<=1;i++) {						// i はx方向
			for(int j=-1;j<=1;j++) {					// j はy方向
				if(i==0 && j==0)
					continue;
				int re=rule(table,x,y,i,j,n,turn);		//1方向でひっくり返す数を決定
				reSum+=re;								//1方向毎に加算
			}
		}
		//「マス内」かつ「石を置いていない」かつ「reSumが1以上」となるところにしか置けない
		if(y>=0 && y<table.length && x>=0 && x<table.length && table[y][x].equals("no") && reSum>0) {
			table[y][x]=turn;								//置ける場所と判断されたら、置いた場所の要素を置き換える
			addPoint(x , y);
			field.setTable(table);						//テーブルが全て更新され、fieldインスタンスへテーブルを返す
			if(turn.equals("w")) turn="b";			//ターン交代
			else if(turn.equals("b")) turn="w";
		}else {												//置けない場所だった場合
			reSum=0;										//置けないので、ひっくり返した数は0とする
		}
		field.setRe(reSum);								//fieldインスタンスへ値を返す
		field.setTurn(turn);								//この値を用いることで、jspの表示内容が変わる
	}

	//ひっくり返すルール
	public int rule(String[][] table,int x,int y,int i,int j,int n,String turn) {

		int xn=x+i*n;				//x方向への進行
		int yn=y+j*n;			//y方向への進行
		int re=0;					//1方向でひっくり返す数
		if(yn>=0 && yn<table.length && xn>=0 && xn<table.length && table[y][x].equals("no") ){		//「マス内」かつ「石を置いていない」所のみ
			String position=table[yn][xn];
			if (position.equals("no")) {				//進行方向に石が無いなら何もしない
			}else if (position.equals(turn)) {		//進行方向に同じ石があれば、間の石をひっくり返す
				for(int m=1;m<n;m++) {
					table[y+j*m][x+i*m]=turn;
					addPoint(x+i*m , y+j*m);
					re++;									//ひっくり返した数をカウント
				}
				return re;									//カウント数を持ってelseのreへリターン
			}else{											//それ以外(相手の石があれば）さらに進む
				n++;
				re=rule(table,x,y,i,j,n,turn) ;
				return re;									//カウント数を持ってelseまたはputのreへリターン
			}
		}
		return re;
	}

	public void addPoint(int x , int y) {
		int point=x+y*8;
		pointList.add(point);
	}

}
