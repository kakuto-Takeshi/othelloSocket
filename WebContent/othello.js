
var n = 1;//石を置いた回数
var turn = n%2;//どちらのターンか white:0 , black:1
var enemy = (n+1)%2;//相手の数字
var color =  ["白", "黒"];//白:color[0] 黒:color[1]
var colorSet = ["white", "black"];//cssクラス配列 white:colorSet[0] , black:colorSet[1]
var table;//マスの配列
var msgObj = { type : 'msg' , msg : null };
var indexObj = { type : 'index' , index : null };

window.onload = function () {
	//クリックしたときのindex番号を取得
table = Array.from(document.getElementsByTagName("td"));
table.forEach(position => {
	position.addEventListener('click', function(){
			var index = [].slice.call(table).indexOf(position);
			//エンコード
		    indexObj.index = index;
		    var indexJson = JSON.stringify(indexObj);
		    wSck.send(indexJson);
		}, false);
	});
}

	// WebSocketオブジェクト生成
var wSck= new WebSocket("ws://localhost:8080/othelloSocket/othello");

//ソケット接続時のアクション
wSck.onopen = function() {
	document.getElementById('show').innerHTML += "接続しました。" + "<br/>" + color[turn] + "の番です。<br/>";
};

//メッセージを受け取ったときのアクション
wSck.onmessage = function(e) {
	console.log(e.data);
	var obj = JSON.parse(e.data);
	if(obj.type == 'msg'){
		document.getElementById('show').innerHTML += obj.msg + "<br/>";
	} else if(obj.type == 'index') {
		if(obj.points.length == 0){
			document.getElementById('show').innerHTML += "その場所には置けません。<br/>";
		} else {
			obj.points.forEach(point => {
				//cssクラスがついていれば追加。相手のクラスがあれば変更する。
				table[point].classList.add(colorSet[turn]);
				table[point].classList.replace(colorSet[enemy] , colorSet[turn]);
			});
			n++;
			turn = n%2;
			document.getElementById('show').innerHTML += color[turn] + "の番です。<br/>";
		}
	}
};

//メッセージを送信するときのアクション
var sendMsg = function(val) {
	var line = document.getElementById('msg');//入力内容を取得
	//エンコード
	msgObj.msg = line.value;
	var msgJson = JSON.stringify(msgObj);
	wSck.send(msgJson);//ソケットに送信
	line.value = "";//内容をクリア
};

//エラーが発生した場合
wSck.onerror = function(error) {
	console.log("エラーが発生しました。");
	console.log(error);
};
