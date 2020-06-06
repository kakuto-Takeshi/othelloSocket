
var n = 1;//石を置いた回数
var turn = n%2;//どちらのターンか white:0 , black:1
var enemy = (n+1)%2;//相手の数字
var color =  ["白", "黒"];//白:color[0] 黒:color[1]
var colorSet = ["white", "black"];//cssクラス配列 white:colorSet[0] , black:colorSet[1]
var table;//マスの配列

window.onload = function () {
	//クリックしたときのindex番号を取得
table = Array.from(document.getElementsByTagName("td"));
table.forEach(position => {
	position.addEventListener('click', function(){
			var index = [].slice.call(table).indexOf(position);
		    console.log(index);
		    console.log(n);
		    wSck.send(index);
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
	console.log(e);
	if(e.data==""){
		document.getElementById('show').innerHTML += "その場所には置けません。<br/>";
	} else {
//			document.getElementById('show').innerHTML += e.data + "<br/>";
		var points=e.data.split(':');
		points.forEach(point => {
			console.log(point);
			//cssクラスがついていれば追加。相手のクラスがあれば変更する。
			table[point].classList.add(colorSet[turn]);
			table[point].classList.replace(colorSet[enemy] , colorSet[turn]);
		});
		n++;
		turn = n%2;
		document.getElementById('show').innerHTML += color[turn] + "の番です。<br/>";
	}
};

//メッセージを送信するときのアクション
var sendMsg = function(val) {
	var line = document.getElementById('msg');//入力内容を取得
	wSck.send(line.value);//ソケットに送信
	console.log(line.value);
	line.value = "";//内容をクリア
};

//エラーが発生した場合
wSck.onerror = function(error) {
	console.log("エラーが発生しました。");
	console.log(error);
};
