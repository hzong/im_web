/**
 * 查询好友、群
 */
var Find_imSDK = null;
LayIM_Find = function(){
	var lif = this;
	Find_imSDK = new IMClient(true);
	
	//要在建立连接执行
	Find_imSDK.initialize(function(status){
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
	
	Find_imSDK.onConnect({
		"account":act,
		"password":"123",
		"source":"hz_chatim"
	});
	
	var node ="#find-list";
	this.FindType = {
			User:{code:0,msg:'用户'},
			Group:{code:1,msg:'群'}
	}
	
	var loadFind = function(type,list){
		var l = list ||[];
		$(node).html("");
		for(var i =0; i < list.length;i++){
			addNode(type,l[i]);
		}
	}
	
	this.init= function(){
		var type = $(".container-fluid").find('input:radio');
		
		var findData = function(val){
			if(val == undefined){
				val = $('input[name="findtype"]:checked').val();
			}
			var url = req_uri+req_uri_root+'find';
			var ft =lif.FindType.User;
			var data ={code:ft.code,text:$('.form-control').val()};
			if(val == lif.FindType.Group.code){
				ft = lif.FindType.Group;
				url = req_uri+'hz/find';
				data.type = ft.code;;
			}
			
			$.ajax({
				type: 'POST',
				url: url ,
				data: data ,
				dataType: 'json',
				success: function(res){
					if(!res.result){
						layer.msg(res.message);
					}
					console.log(res);
					if(res.code == 0){
						loadFind(lif.FindType.User, res.data);
					}
				} ,
				error : function(e){
					console.log('查询'+ft.msg+"失败：原因："+e.responseText);
					layer.msg('查询'+ft.msg+"失败");
				}
			});
		}
		
		$('#btn_find').click(findData);
		
		type.click(function(){
			findData($(this).val());
		});
		$('input[type=radio][value="'+lif.FindType.User.code+'"]').attr("checked",'checked').click();
		
	}
	
	var addNode = function(type,obj){
		var h_tag = "";
//		obj = obj || {};
//		obj.img = 'https://apic.douyucdn.cn/upload/avatar/face/201605/21/dc5317a637af107e478c44514298ca9f_middle.jpg?rltime';
//		obj.nick = '曹操';
//		obj.remark = '这个人很懒';
//		obj.account = 'caocao';

		var but_name = "";
		var call_fun = null;
		if(type== lif.FindType.User){
			but_name = "添加好友";
			
			call_fun = function(){
				var hid = obj.account+'extinfo';
				var html = '<div>您将添加(' + obj.account + ')为好友，'+
				'附加消息:</div><div class="layim-chat-textarea" style="margin-left:0px;">'+
				'<textarea style="border:1px solid gray;width:100%;margin-top:5px;" maxlength="15" id="'+hid+'">你好,我是</textarea></div>';
				layer.confirm(html, {
						title:"申请好友",
					  btn: ['发送','取消'] //按钮
					}, function(){
						Find_imSDK.applyRoster(obj.account, $('#'+hid).val());
					  layer.msg('申请好友请求已发送', {icon: 1});
					}, function(){
					});
			}
		}else if(type== lif.FindType.Group){
			but_name = "加群";
		}
		
		h_tag = '<div class="col-md-3 layim-ui-find_list" ><div class="media">'+
					'<a class="media-left media-middle" href="#">' +
					'<img class="img-circle" src="'+obj.img+'" style="height:64px; width: 64px" data-src="" alt="'+obj.nick+'">'+
					'</a>'+
					'<div class="media-body">'+
						'<div>'+obj.nick+'('+obj.account+')'+'</div>'+
						'<div class="layim-ui-remark layim-ui-space">'+obj.remark+'</div>'+
						'<div><button type="button" class="btn btn-primary btn-xs layim-ui-space">'+but_name+'</button></div>'+
					'</div>'+
				'</div><div>';

		
		$(node).append(h_tag);
		$(node).find(':button :last').click(function(){
//			alert(Math.random()*10);
			call_fun();
		});
	}
	
}


$(function(){
	LayIM_Find = new LayIM_Find();
	LayIM_Find.init();
});
