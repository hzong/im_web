<%@page import="cn.hzong.systech.common.mode.SysMode"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript"
	src="<%=im_sdk%>script/chat/plugin/WEB_SDK_IM.js"></script>
<!-- bootstrap -->
<script type="text/javascript"
	src="<%=chat_webroot%>resource/script/jquery/jquery.min.js"></script>
<link rel="stylesheet"
	href="<%=chat_webroot%>resource/script/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=chat_webroot%>resource/script/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=chat_webroot%>resource/script/common/syscom.js"></script>

<script type="text/javascript"
	src="<%=chat_webroot%>resource/script/layer/layer.js"></script>

<link href="<%=chat_webroot%>resource/css/layim-ui.css" rel="stylesheet"
	media="all">
<script type="text/javascript" src="<%=chat_webroot%>resource/script/im/layim/lay-group-set.js"></script>

<script type="text/javascript">
	var act = '${account}';
</script>

</head>
<body>
	<div id="conn" style="width: 100%;" align="center">正在连接。。。。。</div>
	<div class="container-fluid">
		<div class="row" >
			<div class="form-group col-md-6 col-md-offset-3">
			    <label for="group_name">群名称:</label>
			    <input type="text" class="form-control" id="group_name" placeholder="群名称">
			 </div>
			 <!-- <div class="form-group col-md-6 col-md-offset-3">
			    <label for="group_img">群头像：</label>
			    <img align="right" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgdmlld0JveD0iMCAwIDE0MCAxNDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjxkZWZzLz48cmVjdCB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjQ0LjA1NDY4NzUiIHk9IjcwIiBzdHlsZT0iZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQ7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCI+MTQweDE0MDwvdGV4dD48L2c+PC9zdmc+" alt="群头像" class="img-circle">
			    <input type="file"  id="group_img" placeholder="请上传群头像">
			 </div> -->
			 
			 <div class="form-group col-md-6 col-md-offset-3">
			    <label for="group_member">选择群成员：</label>
			    <div class="row" id = "div_members">
			    	<c:forEach var="user"  items="${user_account.data}"> 
					<div class="col-md-4 layim-ui-find_list">
						<div class="media">
							<a class="media-left media-middle" href="#"> <img
								class="img-circle"
								src="${user.headPortrait}"
								style="height: 64px; width: 64px"  alt="${user.nick}">
							</a>
							<div class="media-body">
								<div>${user.nick}</div>
								<div class="layim-ui-remark layim-ui-space">${user.sign }</div>
								<div>
									<label>
								      <input name="ch_member" type="checkbox" value="${user.account}"> 
								    </label>
								</div>
							</div>
						</div>
					</div>
					</c:forEach> 
				</div>
			 </div>
			 <div class="form-group col-md-6 col-md-offset-3">
			    <input type="button" class="form-control" id="but_createRoom" value="创建群" placeholder="Enter email">
			 </div>
		 </div>
	</div>
</body>
</html>