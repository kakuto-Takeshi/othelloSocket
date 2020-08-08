# othelloSocket

WebSocket通信を用いた双方向非同期通信による対戦オセロゲーム。  
複数ブラウザでのリアルタイム対戦が可能。

## Demo

![対戦オセロ](https://github.com/kakuto-Takeshi/othelloSocket/blob/master/WebContent/othelloSocket.gif)

## 実装項目

-   ルーム機能 (2人1組)
-   チャット機能
-   オセロ機能
-   勝敗機能

リクエスト順に2人1組となりルーム作成。  
各ルームごとにチャット及びオセロ対戦が可能。  

## 使用機能

-   Java, JavaScript
-   Eclipse
-   WebSocket (双方向非同期通信)
-   セッション (ルーム管理)
-   JSON (データ通信)

## Note

Java、JavaScript間のWebSocket通信について、下記URL(Qiita)へ学習内容をまとめ投稿。  
[JavaとJavaScriptでwebブラウザとのソケット通信①](https://qiita.com/take4eng/items/d0b009c48ee8c3fe420a)  
[WebSocket通信におけるJSONデータの利用方法 (Java , JavaScript)](https://qiita.com/take4eng/items/2f71a54f11e47d627041)
