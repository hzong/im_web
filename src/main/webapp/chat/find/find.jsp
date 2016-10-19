<%@page import="cn.hzong.systech.common.mode.SysMode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String chat_webroot = SysMode.getWebRoot(request);
	String im_sdk = "http://192.168.56.1:8888/im-service/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询好友/群</title>
<!-- BOSH核心包 -->
<script type="text/javascript" src="<%=im_sdk%>script/chat/plugin/WEB_SDK_IM.js"></script>
<!-- bootstrap -->
<script type="text/javascript" src="<%=chat_webroot%>resource/script/jquery/jquery.min.js"></script>
<link rel="stylesheet" href="<%=chat_webroot%>resource/script/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="<%=chat_webroot%>resource/script/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=chat_webroot%>resource/script/common/syscom.js"></script>

<script type="text/javascript" src="<%=chat_webroot%>resource/script/layer/layer.js"></script>
<script type="text/javascript" src="<%=chat_webroot%>resource/script/im/layim/lay-find.js"></script>

<link href="<%=chat_webroot%>resource/css/layim-ui.css" rel="stylesheet" media="all">

<script type="text/javascript">
	var act = '${account}';
</script>

</head>
<body>
	<div id="conn" style="width: 100%;" align="center">正在连接。。。。。</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
				<input class="form-control" type="text" placeholder="模糊查询 IM账号、群昵称">
			</div>
			&nbsp;&nbsp;
			<button type="button" id="btn_find" class="btn btn-warning">查询</button>
			&nbsp;
        	<button type="button" id="btn_createRoom" class="btn btn-info">建群</button>
			&nbsp;&nbsp;
			<input type="radio" name="findtype" value="0">找用户
        	<input type="radio" name="findtype" value="1">找群 
        	
		</div>
		<!-- 此处是渲染 列表 -->
		</br>
		<div id="find-list" class="row layim-ui-space">
			<div class="col-md-4 layim-ui-find_list" >
				<div class="media">
					<a class="media-left media-middle" href="#"> 
					<img class="img-circle" src="https://apic.douyucdn.cn/upload/avatar/face/201605/21/dc5317a637af107e478c44514298ca9f_middle.jpg?rltime" style="height:64px; width: 64px" data-src="" alt="性感美女">
					</a>
					<div class="media-body">
						<div >曹操</div>
						<div class="layim-ui-remark layim-ui-space">这个人很懒</div>
						<div>&nbsp;&nbsp; <button type="button" class="btn btn-primary btn-xs">添加好友</button></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>