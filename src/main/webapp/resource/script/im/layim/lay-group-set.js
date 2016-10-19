/**
 * 查询好友、群
 */
var Group_imSDK = null;
LayIM_Group_Set = function(){
	var ligs = this;
	
	Group_imSDK = new IMClient(true);
	
	Group_imSDK = new IMClient(true);
	
	//要在建立连接执行
	Group_imSDK.initialize(function(status){
		if(status == 'CONNECTING'){
			$("#conn").html("正在连接。。。。。。");
			
		}else if(status == 'DISCONNECTED'){
			$("#conn").html("断开连接。。。。。。<a id='con_cl' href='#'>重新连接</a>");
			$("#con_cl").click(function(){
//				IMConnection();
			});
		}else if(status == 'CONNECTED'){
			$("#conn").html("连接成功。。。。。。");
		}else{//错误
			$("#conn").html("连接IM异常");
		}
	});
	
	Group_imSDK.onConnect({
		"account":act,
		"password":"123",
		"source":"hz_chatim"
	});
	
	Group_imSDK.RoomListen = {
			onCreateRoom :function(_error,obj){
			if(_error){
				layer.msg(_error.message);
				return;
			}
			layer.msg("创建群成功");
		}
	};
	
	var node =$("#div_members");
	
	
	var but_sum = function(){
		var a_member = [];
		var group_name = $("#group_name").val();
		$("input[name='ch_member']:checked").each(function(){
		    	a_member[a_member.length] = {account : $(this).val()};
		});
		console.log(a_member);
		console.log(group_name);
		Group_imSDK.createRoom({groupName:group_name,members:a_member});
		
		$("#but_createRoom").click(function(){layer.msg('请不要重复提交，请关闭弹出窗');});
	}
	
	$("#but_createRoom").one('click',but_sum);
}

$(function(){
	LayIM_Group_Set = new LayIM_Group_Set();
});
