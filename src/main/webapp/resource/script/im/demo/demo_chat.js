/**
 * 渲染XMPP
 */
var imSDK = null;


function init(){
	
	$("#grouplist").html("");
	imSDK.getRoomList(function(obj,i){
		$("#grouplist").append("<span groupid = '"+obj.roomId+"' ><a href='#'>"+obj.name+"</a>&nbsp;&nbsp;<span style='cursor: pointer;'>删除</span></span><br/>");
		var span = $("#grouplist span[groupid="+obj.roomId+"]");
		span.find("a").click(function(){
			$("#to").val(obj.roomId);
			$("#zjyqrq_id").val(obj.roomId);
			$("#jjyqrq_id").val(obj.roomId);
			$("#tq_id").val(obj.roomId);
			$("#hqqcy_id").val(obj.roomId);
		});
			
		
		
	});
	$("#cjq_member").html("");
	$("#rosterlist").html("");
	imSDK.getRostersList(function(_error,s){
		if(_error){
			layer.msg(_error.message);
			return;
		}
		$.each(s,function(i,obj){
			$("#rosterlist").append("<span jid = '"+obj.account+"'><a href='#'>"+obj.group+'-'+(obj.account == undefined ? "":obj.account)+"</a>&nbsp;&nbsp;<span style='cursor: pointer;'>删除</span></span><br/>");
			if(obj.account == undefined){
				return;
			}
			
			var span =  $("#rosterlist span[jid="+obj.account+"]");
			span.find("a").click(function(){
				$("#to").val(obj.account);
				$("#sqhy_text").val(obj.account);
			});
			span.find("span").click(function(){
				var jid = $(this).parent().attr("jid");
				imSDK.removeRoster({account:jid});
			});
			
			var str = "<input name = 'cjq_member_box'  type='checkbox' xm='"+obj.account+"' value='"+obj.account+"' />"+obj.account+" <br/>";
			$("#cjq_member").append(str);
			$("#zjyqrq_member").append(str);
			$("#jjyqrq_member").append(str);
		});
		
		
	});
	
//	imSDK.removeRosters(imAccount, cbSuccess, cbError)
	
	$("#sqhy").unbind('click');//解除绑定
	$("#sqhy").click(function(){
		var imAccount = $("#sqhy_text").val();
		var val=prompt("请输入理由：","");//将输入的内容赋给变量 name ，
//        //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值
        if(val)//如果返回的有内容
        {
        	imSDK.applyRoster({ account:imAccount,
		  		 reason : val
  		  });
        }
//		alert("申请好友成功");
	});
	
	$("#zjyqrq").unbind('click');//解除绑定
	$("#zjyqrq").click(function(){
		var cjq_member = $("#zjyqrq_member").find("input:checked");
		
		var member =[]; 
		$.each(cjq_member,function(i,ele){
			member[member.length] = {"account":$(ele).val()};
		});
		
		imSDK.directInvitationMembers({roomId:$("#zjyqrq_id").val(),members: member});
	});
	
	$("#jjyqrq").unbind('click');//解除绑定
	$("#jjyqrq").click(function(){
		var cjq_member = $("#jjyqrq_member").find("input:checked");
		
		$.each(cjq_member,function(i,ele){
			var obj = {
					roomId:$("#jjyqrq_id").val(),
					account:$(ele).val(),
					reason:"速度拉进群"
			};
			imSDK.mediatedInvitationMember(obj);
		});
		
	});
	$("#tq").unbind('click');//解除绑定
	$("#tq").click(function(){
			imSDK.exitRoom({roomId :$("#tq_id").val()});
		
	});
	$("#tcy").unbind('click');//解除绑定
	$("#tcy").click(function(){
			var member = [];
			var hqqcy_member = $("#hqqcy_member").find("input:checked");
			$.each(hqqcy_member,function(i,ele){
				member[member.length] = $(ele).val();
			});
		
			imSDK.removeRoomMembers({"roomId":$("#tq_id").val(),"members":member,"reason":"非法"});
		
	});
	
	$("#xhq").unbind('click');//解除绑定
	$("#xhq").click(function(){
			imSDK.destroyRoom({"roomId":$("#tq_id").val(),"reason":"非法"});
	});
	
	
	$("#hqqcy").unbind('click');//解除绑定
	$("#hqqcy").click(function(){
			imSDK.getRoomMember({roomId:$("#hqqcy_id").val()});
		
	});
	
	$("#sqq").unbind('click');//解除绑定
	$("#sqq").click(function(){
			imSDK.applyRoomInvite({"roomId":$("#sqq_id").val(),"reason":"求进"});
		
	});
	
	
	$("#cjq").unbind('click');//解除绑定
	$("#cjq").click(function(){
		var cjq_name = $("#cjq_name").val();
		var cjq_ms = $("#cjq_ms").val();
		var cjq_member = $("#cjq_member").find("input:checked");
		var member =[]; 
		$.each(cjq_member,function(i,ele){
			member[member.length] = {"account":$(ele).val()};
		});
		var o = {
				groupName:	cjq_name,
				members :member,
				describe:cjq_ms
		};
		
		imSDK.createRoom(o);
//		alert("申请好友成功");
	});
	
	
	$("#send").unbind('click');//解除绑定
	$("#send").click(function(){//发送消息
		var msgb = new imSDK.MsgTextBuilder({"content":$("#content").val()});
		var sesstionType = $('input[name="sessionType"]:checked').val();
		
		var cbError = function(){
			
		};
		
		
		if(sesstionType == 0)
			imSDK.sendMsg(imSDK.sessionType.chat,$("#to").val(),msgb,cbError);
		else
			imSDK.sendMsg(imSDK.sessionType.groupchat,$("#to").val(),msgb,cbError);
	});
	
	
}

var Dialog = function(){
	this.handleRoomApply = function(type){
		var tz  = $("#tsk").attr("tz");
		var tsk = "已被其他管理员操作！";
		
		
		if(tz != 'AcceptRoomInvite'){
			return;
		}
		$("#tsk").find("dt").html(tsk);
		$("#tsk").attr("tz",type);
		$("#tsk").find("dd").hide();
		
		$("#tsk").show();
	};
	this.ApplyRoomInviteShow=function(obj){
		var req_obj = {};
		req_obj.roomId = obj.roomId;
		req_obj.from = obj.from;
		req_obj.id = obj.id;

		var tsk = obj.from+"申请入群群，描述："+obj.reason;
		$("#tsk").find("dt").html(tsk);
		$("#tsk").attr("tz","AcceptRoomInvite");

		$("#but_ty").unbind('click');
		$("#but_ty").click(function(){
			imSDK.passRoomApply(req_obj);
			$("#tsk").hide();
		});
		
		$("#but_jj").unbind('click');
		$("#but_jj").click(function(){
			req_obj.reason = "就不";
			imSDK.rejectRoomApply(req_obj);
			$("#tsk").hide();
		});
		$("#tsk").find("dd").show();
		$("#tsk").show();
	}
}


function IMConnection(){
	imSDK = new IMClient(true);
	
	imSDK.OnlineStatus = function(obj){
		
		var a = $("#rosterlist").find("span[jid="+obj.fromJid+"]").find("a");
		if(obj.type == 'online'){
			a.css("color","#78EE47");
		}else{
			a.css("color","#B6B6B6");
		}
	} 
	
	//聊天监听必须使用
	imSDK.ChatMessageListen = function(sessionType,msg){
		var ele = "";
		ele = "<br/><div>"+msg.sendtime+(sessionType == 'chat' ? "  ":"  房间为："+msg.room)+"-"+msg.from+"对你说话<br/> <div>内容："+$.evalJSON(msg.body).content+"</div><br/></div>";
		$("#msg_chat").prepend(ele);
	};
	
	
	var addRoom = function(obj){
		$("#grouplist").append("<span groupid = '"+obj.roomId+"' ><a href='#'>"+obj.name+"</a>&nbsp;&nbsp;<span style='cursor: pointer;'>删除</span></span><br/>");
		var span = $("#grouplist span[groupid="+obj.roomId+"]");
		span.find("a").click(function(){
			$("#to").val(obj.roomId);
			$("#zjyqrq_id").val(obj.roomId);
			$("#jjyqrq_id").val(obj.roomId);
			$("#tq_id").val(obj.roomId);
			$("#hqqcy_id").val(obj.roomId);
		});
	};
	
	imSDK.RoomListen = {
			onCreateRoom:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				addRoom(obj);
			},
			onDirectInvitation:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				var str = "";
				var isexe = false;
				$.each(obj.members,function(i,member){
					str+=","+member.account;
					if(member.account == account){//成员包含自己则 添加群列表告知
						isexe = true;
					}
				});
				if(isexe){
					addRoom(obj);
				}
				alert("用户"+obj.from+"拉入:"+str.substring(1)+"入群"+obj.name);
			},
			onMediatedInvitation:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				var req_obj = {};
				req_obj.roomId = obj.roomId;
				req_obj.from = obj.from;
				if(confirm(obj.from+"邀请你进入"+obj.roomId+"群，描述："+obj.reason)){
					imSDK.acceptRoomInvite(req_obj);
				}else{
					req_obj.reason = "就不进";
					imSDK.rejectRoomInvite(req_obj);
				}
			},
			onExitRoom:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				if(obj.from == account){
					$("#grouplist span[groupid = '"+obj.roomId+"']").remove();
					alert("退群成功");
				}else{
					alert(obj.from+"退出"+obj.roomId+"群");
				}
			},
			onRoomMember:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				$.each(obj,function(i,member){
					var str = "<input name = 'hqqcy_member_box'  type='checkbox' xm='"+member.account+"' value='"+member.account+"' />"+member.account+" <br/>";
					$("#hqqcy_member").append(str);
				});
			},
			onRemoveRoomMembers:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				if(obj.from == account){
					alert("踢群成员成功");
					return;
				}
				$.each(obj.members,function(i,item){
					if(item.account == account){
						$("#grouplist span[groupid = '"+obj.roomId+"']").remove();
						alert("退群成功");
					}
					$("#hqqcy_member input[value="+item.account+"]").remove();
				});
				
			},
			onDestroyRoom:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				$("#grouplist span[groupid = '"+obj.roomId+"']").remove();
				alert("房间:"+obj.roomId+"解散了");
			},
			onAcceptRoomInvite:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				if(obj.from == account){
					obj.name = obj.name;
					addRoom(obj);
				}else{
					$("#jjyqrq_member input[value="+obj.from+"]").remove();
					alert("用户:"+obj.from +"同意入群邀请进群");
				}
			},
			onRejectRoomInvite:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
					alert("用户:"+obj.from +"拒绝入群邀请进群，原因："+obj.reason);
			},
			onApplyRoomInvite:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				Dialog.ApplyRoomInviteShow(obj);
			},
			onRejectRoomApply:function(_error,obj){
//				if(error){
//					alert("处理"+obj.roomId+"中的成员"+obj.localpartJid+"已被其他管理处理了");
//					return;
//				}
				if(_error){
					layer.msg(_error.message);
					return;
				}
				Dialog.handleRoomApply("RejectRoomApply");
				alert("被拒绝:"+obj.from +"，原因："+obj.reason);
			},
			onPassRoomApply:function(_error,obj){
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
//				if(error){
//					alert("处理"+obj.roomId+"中的成员"+obj.localpartJid+"已被其他管理处理了");
//					return;
//				}
				
				Dialog.handleRoomApply("PassRoomApply");
				if(obj.from == account){
					obj.name = obj.name;
					addRoom(obj);
				}else{
					alert("用户:"+obj.from +"同意入群邀请进群");
				}
			}
			
			
	}
	
	
	imSDK.RosterListen = {
			onApplyRoster:function(_error,o){//申请好友
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				if(confirm("来自"+o.from+"用户-请求加你为好友："+o.reason))
			    {
					imSDK.agreeRoster({account:o.from});
			    }
			    else
			    {//否则说明下了
			    	var val=prompt("请输入拒绝理由：","");//将输入的内容赋给变量 name ，
			        //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值
			        if(val)//如果返回的有内容
			        {
			        	imSDK.refuseRoster({
			        		 		account:o.from,
			        		  		reason:val
			        		  });
			        }
			    }
			},
			onRemoveRoster:function(_error,obj){//删除好友
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				$("#rosterlist span[jid = '"+obj.from+"']").remove()
//				if(account == username ){
//					alert("好友已删除");
//				}
			},
			onAgreeRoster:function(_error,obj){//同意好友
				if(_error){
					layer.msg(_error.message);
					return;
				}
				
				$("#rosterlist").append("<span jid = '"+obj.from+"'><a href='#'>"+obj.from+"</a>&nbsp;&nbsp;<span style='cursor: pointer;'>删除</span></span><br/>");
				var span =  $("#rosterlist span[jid="+obj.from+"]");
				span.find("a").click(function(){
					$("#to").val(obj.from);
					$("#sqhy_text").val(obj.from);
				});
				span.find("span").click(function(){
					var jid = $(this).parent().attr("jid");
					imSDK.removeRoster({account:jid});
				});
//				alert("用户"+username +"已是你好友");
			},
			onRefuseRoster:function(_error,obj){//拒绝好友
				if(_error){
					layer.msg(_error.message);
					return;
				}
				alert("用户"+obj.from +"已是拒绝你为好友原因："+obj.reason);
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
			init();
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


$(function(){
	IMConnection();
	Dialog = new Dialog();
});