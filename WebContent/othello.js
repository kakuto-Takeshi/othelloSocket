var n = 0;//石を置いた回数
var turn = n%2;//どちらのターンか white:0 , black:1
var enemy = (n+1)%2;//相手の数字
var color = ["black" , "white"];//cssクラス配列 black:colorSet[0] , white:colorSet[1]
var table;//マスの配列
var msgObj = { type : 'msg' , msg : null };
var indexObj = { type : 'index' , index : null };
var myTurn = document.getElementById('myTurn');
var showChat = document.getElementById('showChat');
var showTurn = document.getElementById('showTurn');
var showNum = document.getElementById('showNum');
var showGod = document.getElementById('showGod');
var bStone = document.getElementById('bStone');
var wStone = document.getElementById('wStone');
var empty = document.getElementById('empty');

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
	document.getElementById('showGod').innerHTML += "ルームに入りました。" + "<br>";
	showTurn.innerHTML = "black";
};

//メッセージを受け取ったときのアクション
wSck.onmessage = function(e) {
	console.log(e.data);
	var obj = JSON.parse(e.data);
	if (obj.type == 'msg'){
		if (obj.turn == "神"){
			showGod.innerHTML += obj.turn + "：" + obj.msg + "<br>";
			showGod.scrollTop = showGod.scrollHeight;
		} else if (obj.turn == "black" || obj.turn == "white"){
			showChat.innerHTML += obj.turn + "：" + obj.msg + "<br>";
			showChat.scrollTop = showChat.scrollHeight;
		} else if (obj.turn == "myTurn") {
			myTurn.innerHTML = obj.msg;
			if(obj.msg == "black"){
				myTurn.style.color = "white";
				myTurn.style.backgroundColor = "black";
			} else {
				myTurn.style.color = "black";
				myTurn.style.backgroundColor = "white";
			}
		}
	} else if (obj.type == 'index') {
		n = obj.turnNum;
		turn = n%2;
		enemy = (n+1)%2;
		obj.points.forEach(point => {
			//cssクラスがついていれば追加。相手のクラスがあれば変更する。
			table[point].classList.add(color[turn]);
			table[point].classList.replace(color[enemy] , color[turn]);
		});
		bStone.innerHTML = obj.blackNum;
		wStone.innerHTML = obj.whiteNum;
		empty.innerHTML = obj.noNum;
		showTurn.innerHTML = color[enemy];
		showTurn.style.color = color[turn];
		showTurn.style.backgroundColor = color[enemy];
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
