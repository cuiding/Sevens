<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/unicorn.login.css" />

<script src="/js/bootstrap.js"></script>
<script src="js/jquery.min.js"></script>  
<script src="js/unicorn.login.js"></script> 
</head>
<body>
    <div id="loginbox">
		<form id="loginform" class="form-vertical" action="User_loginAdmin.action" method="post">
			<p>输入用户昵称和密码进入后台.</p>
			<div class="control-group">
				<div class="controls">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-user"></i></span><input
							type="text" name="user.nickName" value="${user.nickName }" placeholder="昵称" />
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-lock"></i></span><input
							type="password" name="user.password" value="${user.password }" placeholder="密码" />
					</div>
				</div>
			</div>
			<div class="form-actions">
				 <span class="pull-right">
				 	<font id="error" style="font-size: 20px;" color="red">${error }</font>
				 	<input type="submit" class="btn btn-inverse" value="进入后台" />
				 </span>
			</div>
		</form>
	</div>
</body>
</html>