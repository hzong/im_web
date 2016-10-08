/**
 * Lay 页面面板
 */
var LayPanel = function(mine) {
	var user_mine = mine;
	
	var List_Type = {
		friend : 'friend',
		history : 'history',
	}
	var User_Online = {
		online : 0,
		offline : 1,
	}

	var SetLineStatus = function(type,id,status){
		if(status == 0){
			$("#layim-"+type+id).find("img").removeClass("layim-ui_offline");
		}else{
			$("#layim-"+type+id).find("img").addClass("layim-ui_offline");
		}
	}
	
	var UserLineStatusOnline = function(id){
		SetLineStatus(List_Type.friend, id,User_Online.online);
		SetLineStatus(List_Type.history, id,User_Online.online);
	}
	var UserLineStatusOffline = function(id){
		SetLineStatus(List_Type.friend, id,User_Online.offline);
		SetLineStatus(List_Type.history, id,User_Online.offline);
	}
	
	
	
	var IMConnection = function() {
		imSDK = new IMClient(true);

		imSDK.OnlineStatus = function(obj) {
			if (obj.type == 'online') {
				UserLineStatusOnline(obj.fromJid);
			} else {
				UserLineStatusOffline(obj.fromJid);
			}
		}

		imSDK.initialize(function(status) {
			if (status == 'CONNECTING') {
				$("#conn").html("正在连接。。。。。。");

			} else if (status == 'DISCONNECTED') {
				$("#conn").html("断开连接。。。。。。<a id='con_cl' href='#'>重新连接</a>");
				$("#con_cl").click(function() {
					IMConnection();
					init();
				});
			} else if (status == 'CONNECTED') {
				$("#conn").html("连接成功。。。。。。");
			} else {//错误
				$("#conn").html("连接IM异常");
			}
		});

		imSDK.onConnect({
			"account" : account,
			"password" : "123",
			"source" : "hz_chatim"
		});
	}

	IMConnection();
}

LayPanel.prototype.ChatBox = function(lay){
	 //监听发送消息
	  layim.on('sendMessage', function(data){
		    var to = data.to;
		    var mine = data.mine;
		    var msgb = new imSDK.MsgTextBuilder({"content":mine.content});
		    imSDK.sendMsg(imSDK.sessionType.chat,to.id,msgb);
		  }
	  );
	  
	//聊天监听必须使用
		imSDK.ChatMessageListen = function(sessionType,msg){
			layim.getMessage({
				username: msg.from //消息来源用户名
					,avatar: getImg() //消息来源用户头像
						,id: msg.fromJid //聊天窗口来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
							,type: "friend" //聊天窗口来源类型，从发送消息传递的to里面获取
								,content: $.evalJSON(msg.body).content //消息内容
									,mine: false //是否我发送的消息，如果为true，则会显示在右方
									,timestamp: 1467475443306 //服务端动态时间戳
			});
		};
}

LayPanel.prototype.RefreshUI = function() {
//	mine_obj = {
//		"username" : account //我的昵称
//		,
//		"id" : 'hzong' //我的ID
//		,
//		"status" : "online" //在线状态 online：在线、hide：隐身
//		,
//		"sign" : "在深邃的编码世界，做一枚轻盈的纸飞机" //我的签名
//		,
//		"avatar" : getImg()
//	//我的头像
//	};

	layui.use('layim', function(layim) {
		//先来个客服模式压压精
		var config = layim.config({
			init : {
				url : 'http://192.168.56.106:8888/im-service/layim/LayInit' //接口地址（返回的数据格式见下文）
				,
				type : 'post' //默认get，一般可不填
				,
				data : {
					account : 'hz_chatim_' + account
				}
			//额外参数
			},
			title : "微聊",
			mine : user_mine,
			maxLength : 200,//最长发送的字符长度，默认3000
			find : '/find/', //查找好友/群的地址（如果未填则不显示）
			brief : false, //是否简约模式（如果true则不显示主面板）
			copyright : true
		});

		//	  initLayUI(layim);
	});
}


var a_imgs = [
		"https://apic.douyucdn.cn/upload/avatar/face/201605/21/dc5317a637af107e478c44514298ca9f_middle.jpg?rltime",
		"http://tva4.sinaimg.cn/crop.0.0.178.178.50/a6f2d05bgw1e73wpxoh1vj204z04z3yt.jpg",
		"http://qzapp.qlogo.cn/qzapp/101235792/DD20A35A00B0F6013EBDE1101AD5771A/100",
		"http://tva4.sinaimg.cn/crop.0.85.681.681.180/005ugfj2jw8f77fed97tej30j60y30wc.jpg",
		"http://qzapp.qlogo.cn/qzapp/101235792/B4664B3C8B24F6E22214016679052F50/100",
		"http://qzapp.qlogo.cn/qzapp/101235792/B72D406AD53F713A6D008D47752F5AB3/100",
		"http://tse4.mm.bing.net/th?id=OIP.M6f72b5db30aecbbfaea906201b7377a3o2&w=90&h=147&c=7&rs=1&qlt=90&o=4&pid=1.1" ];
function getImg() {
	var index_img = parseInt(a_imgs.length * Math.random(), 10);
	return a_imgs[index_img];
}