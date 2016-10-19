/**
 * 渲染XMPP
 */
var imSDK = null;
function IMConnection(){
	imSDK = new IMClient(true);
	
	imSDK.OnlineStatus = function(obj){
		if(obj.type == 'online'){
			UserLineStatusOnline(obj.fromJid);
		}else{
			UserLineStatusOffline(obj.fromJid);
		}
	} 
	
	imSDK.RosterListen = {//好友消息监听
			/**
			 * 申请好友事件
			 * @param _error 错误状态
			 * @param obj {
			 * 	fromJid 发送方IM账号,
			 *  from 平台账号
			 * 	reason 发送方-描述
			 * }
			 */
			onApplyRoster:function(_error,obj){//申请好友
				if(_error){
					layer.msg(_error.message);
					return;
				}
				var html = "用户:"+obj.from+"，申请理由："+obj.reason;
				layer.confirm(html, {
					title:"用户申请",
				  btn: ['同意','拒绝'] //按钮
				}, function(index){
					imSDK.agreeRoster({account:obj.from});
					layer.close(index);
				}, function(){
					var hid= obj.from+"refuseRostertext";
					html = "拒绝理由："+'<div class="layim-chat-textarea" style="margin-left:0px;">'+
					'<textarea style="border:1px solid gray;width:100%;margin-top:5px;" maxlength="15" id="'+hid+'"></textarea></div>';;
					layer.alert(html, {
						  skin: 'layui-layer-molv' //样式类名
						  ,closeBtn: 0
						}, function(index){
							imSDK.refuseRoster({account:obj.from,reason:$("#"+hid).val()});
							 layer.close(index);
						});
					
					
				});
				
			},
			/**
			 * 同意好友
			 * @param _error  错误
			 * @param obj {
						fromJid:"",
						from:"",
					}
			 */
			onAgreeRoster:function(_error,obj){//同意好友
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				
				var rid = 0;
				$.each(layui.layim.cache()['friend'],function(i,item){
					if(item.groupname == '好友'){
						rid = item.id;
						return false;
					}
				});
				
				var v = {
						  type: 'friend' //列表类型，只支持friend和group两种
							  ,avatar: "https://dn-kdoss.qbox.me/FoF8S6o6OOt_7cgUvzz5pWME5Yeo?imageView2/1/w/150/h/150&e=1476602455&token=BDlsCI9C9xGl-aBysVoFl7-eu9c2j5JLredsogNl:0Q_3HC_X4lvpc7Zi-Mz5RWixoFg=" //好友头像
							  ,username: obj.from //好友昵称
							  ,groupid: rid //所在的分组id
							  ,id: obj.from //好友id
							  ,sign: "本人冲田杏梨将结束AV女优的工作" //好友签名
							};
				
				layui.layim.addList(v);
			},
			/**
			 * 拒绝好友
			 * @param _error 错误
			 * @param obj {
				fromJid:"",
				from:"",
				reason:"",
			}
			 */
			onRefuseRoster:function(_error,obj){//拒绝好友
				if(_error){
					layer.msg(_error.message);
					return;
				}
				layer.msg(obj.from+"拒绝您的申请,原因："+obj.reason);
			},
			/**
			 * 删除好友事件
			 * @param _error:{}
			 * @param obj {
							"from":localpart,
							"fromJid":jid
				}
			 */
			onRemoveRoster:function(_error,obj){//删除好友
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				layui.layim.removeList({
					  type: 'friend' //或者group
					  ,id: obj.fromJid //好友或者群组ID
					});
			},
	};
	
	imSDK.RoomListen = {
//			{
//				fromJid:""//邀请人JID
//				from:""//邀请人ID
//				roomId:""//房间id
//				name：""//房间名字
//				members:[
//					{
//						localpartJid:"进去成员jid"//可能包含自己
//						localpart:""//成员Id
//					}
//				}
//			}
			onDirectInvitation :function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				layui.layim.addList({
					  type: 'group' //或者group
					  ,avatar:'http://qzapp.qlogo.cn/qzapp/101235792/B72D406AD53F713A6D008D47752F5AB3/100'
					  ,groupname:obj.name
					  ,id: obj.roomId //好友或者群组ID
					});
			},
//			{
//				 * 	roomId ： //房间Id
//					localpartJid：//退出群成员jid
//					localpart ： //退出群成员id
//				 * }
			onExitRoom: function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				var a_g =layui.layim.cache()['group'];
				var g = null;
				for(var i = 0;i<a_g.length;i++){
					if(a_g[i].id == obj.roomId){
						g = a_g[i] ;
						break;
					}
				}
				
				
				if(obj.localpart == account){
					layer.msg('退群'+g.groupname+'成功');
				}else{
					layer.msg('有人退群了');
				}
				layui.layim.removeList({
					  type: 'group' //或者group
					  ,id: obj.roomId //好友或者群组ID
					});
			
				
			},
			/**
			 * 解散群
			 * @param obj
			  	{
			  	localpartJid : ;//操作人jid
				localpart : "";//操作人id
				roomId :  //群id
				reason ："" //描述
				}
			 */
			onDestroyRoom:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				var a_g =layui.layim.cache()['group'];
				var g = null;
				for(var i = 0;i<a_g.length;i++){
					if(a_g[i].id == obj.roomId){
						g = a_g[i] ;
						break;
					}
				}
				
				if(obj.localpart == account){
					layer.msg('解散'+g.groupname+'成功');
				}else{
					layer.msg('群解散了');
				}
				layui.layim.removeList({
					  type: 'group' //或者group
					  ,id: obj.roomId //好友或者群组ID
					});
			}
			
		};
	
	//要在建立连接执行
	imSDK.initialize(function(status){
		if(status == 'CONNECTING'){
			$("#conn").html("正在连接。。。。。。");
			
		}else if(status == 'DISCONNECTED'){
			$("#conn").html("断开连接。。。。。。<a id='con_cl' href='#'>重新连接</a>");
			$("#con_cl").click(function(){
				IMConnection();
				init();
			});
			
		}else if(status == 'CONNECTED'){
			$("#conn").html("连接成功。。。。。。");
//			init();
		}else{//错误
			$("#conn").html("连接IM异常");
		}
	});
	
	imSDK.onConnect({
		"account":account,
		"password":"123",
		"source":"hz_chatim"
	});
	
}

function setLineStatus(type,id,status){
	if(status == 0){
		$("#layim-"+type+id).find("img").removeClass("layim-ui_offline");
	}else{
		$("#layim-"+type+id).find("img").addClass("layim-ui_offline");
	}
}
function UserLineStatusOnline(id){
	setLineStatus(List_Type.friend, id,User_Online.online);
	setLineStatus(List_Type.history, id,User_Online.online);
}
function UserLineStatusOffline(id){
	setLineStatus(List_Type.friend, id,User_Online.offline);
	setLineStatus(List_Type.history, id,User_Online.offline);
}
var List_Type = {
		friend :'friend',
		history :'history',
}
var User_Online = {
		online :0,
		offline :1,
}


function initLayUI(layim){
	
	 layim.on('ready', function(res){
//		 alert($(".layim-list-friend + .layim-tab-content").html());
	 });
	 
	 layim.on('menu_group_destroy', function(data){
//		 reason
		 imSDK.destroyRoom({roomId:data});
	 });
	 layim.on('menu_delete', function(data){
		 imSDK.removeRoster({account:data});
	 });
	 
	 layim.on('menu_group_exit',function(data){
		 imSDK.exitRoom({roomId:data});
	 });
	 
	 layim.on('menu_delete', function(data){
		 imSDK.removeRoster({account:data});
	 });
	
	 //监听发送消息 imSDK.sendMsg(imSDK.sessionType.groupchat,$("#to").val(),msgb,cbError);
	  layim.on('sendMessage', function(data){
		    var to = data.to;
		    var mine = data.mine;
		    var msgb = new imSDK.MsgTextBuilder({"content":mine.content});
		    if(to.type == 'group'){
		    	imSDK.sendMsg(imSDK.sessionType.groupchat,to.id,msgb);
		    }else{
		    	imSDK.sendMsg(imSDK.sessionType.chat,to.id,msgb);
		    }
		  }
	  );
	  
	  
	  
	  
	//聊天监听必须使用
		imSDK.ChatMessageListen = function(sessionType,msg){
			var type = "";
			var id = msg.fromJid;
			if(sessionType == 'groupchat'){
				type = 'group';
				id = msg.room;
			}else{
				type = "friend";
			}
			if(msg.from == mine_obj.id){
				return;
			}
			
			layim.getMessage({
				username: msg.from //消息来源用户名
					,avatar: getImg() //消息来源用户头像
						,id: id //聊天窗口来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
							,type: type //聊天窗口来源类型，从发送消息传递的to里面获取
								,content: $.evalJSON(msg.body).content //消息内容
									,mine: false //是否我发送的消息，如果为true，则会显示在右方
									,timestamp: 1467475443306 //服务端动态时间戳
			});
		};
	  
	  
}

var a_imgs = [
              "https://apic.douyucdn.cn/upload/avatar/face/201605/21/dc5317a637af107e478c44514298ca9f_middle.jpg?rltime",
              "http://tva4.sinaimg.cn/crop.0.0.178.178.50/a6f2d05bgw1e73wpxoh1vj204z04z3yt.jpg",
              "http://qzapp.qlogo.cn/qzapp/101235792/DD20A35A00B0F6013EBDE1101AD5771A/100",
              "http://tva4.sinaimg.cn/crop.0.85.681.681.180/005ugfj2jw8f77fed97tej30j60y30wc.jpg",
              "http://qzapp.qlogo.cn/qzapp/101235792/B4664B3C8B24F6E22214016679052F50/100",
              "http://qzapp.qlogo.cn/qzapp/101235792/B72D406AD53F713A6D008D47752F5AB3/100",
              "http://tse4.mm.bing.net/th?id=OIP.M6f72b5db30aecbbfaea906201b7377a3o2&w=90&h=147&c=7&rs=1&qlt=90&o=4&pid=1.1"
              ];
function getImg(){
	var index_img = parseInt(a_imgs.length * Math.random(),10);
	return a_imgs[index_img];
}

var mine_obj = null;
$(function(){
	IMConnection();
	
	
	var index_img = parseInt(a_imgs.length * Math.random(),10);
	var user_obj = jQuery.parseJSON(user_act);
	mine_obj = {
			      "username": user_obj.nick //我的昵称
			      ,"id": user_obj.account //我的ID
			      ,"status": "online" //在线状态 online：在线、hide：隐身
			      ,"sign":user_obj.sign //我的签名
			      ,"avatar": user_obj.headPortrait //我的头像
			 };
	
//	http://192.168.56.106:8888/im-service/layim/LayInit
	
	layui.use('layim', function(layim){
		  //先来个客服模式压压精
		  var config = layim.config({
			    init: {
			      url: req_uri+'layim/LayInit' //接口地址（返回的数据格式见下文）
			      ,type: 'post' //默认get，一般可不填
			      ,data: {account:'hz_chatim_'+account} //额外参数
			    },
			   title:"微聊",
			   members: { 
				   url : 'layim/getLayImMember',
				   type: 'post' //默认get，一般可不填
			   },
			  mine: mine_obj,
			  maxLength: 200,//最长发送的字符长度，默认3000
			  find: req_uri+'hz/toFindMain', //查找好友/群的地址（如果未填则不显示）
			  chatLog: '/chat/log/', //如果未填， 则不在聊天面板显示该icon
			  groupOpt:req_uri+'/hz/toRoomSetMain',//群操作
		    brief: false, //是否简约模式（如果true则不显示主面板）
		    copyright: true
		  });
		  
		  initLayUI(layim);
		  
//		  //聊天界面
//		  config.chat({
//		    name: '客服姐姐'
//		    ,type: 'chat'
//		    ,avatar: 'http://tp1.sinaimg.cn/5619439268/180/40030060651/1'
//		    ,id: 'hz_chatim_caocao'
//		  });
		});
	
});