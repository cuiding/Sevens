<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" type="text/css" href="css/style.css" /> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
function logout() {
	if (confirm("您确定要退出系统吗？")) {
		window.location.href="User_logout.action";
	}
}

function login(){
	var curPage=window.location.href;
	window.location.href="login.jsp?prePage="+curPage;
}

function checkUserLogin(){
	if ('${currentUser.nickName}'==null||'${currentUser.nickName}'=="") {
		alert("您还未登陆！");
	} else {
		window.location.href="User_userCenter.action";
	}
}
</script>

<style type="text/css">
.span12{
	width:100%;
}

</style>

</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<!-- <div class="span12"> -->
			<div class="navbar">
				<div class="navbar-inner">
					<!-- <div class="container-fluid"> -->
						 <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a> <a href="#" class="brand">七天情侣</a>
						<div class="nav-collapse collapse navbar-responsive-collapse">
							<ul class="nav">
								<li class="current_page_item"><a href="index.jsp">首　页</a></li>
								<li class="current_page_item"><a href="#">关于我们</a></li>
							</ul>
							<ul class="nav pull-right">
							<c:choose>
								<c:when test="${not empty currentUser }">
									<li><a href="#">${currentUser.nickName }</a></li>
											<c:choose>
					                  	  		<c:when test="${fn:length(currentUser.sectionList)==0 && currentUser.type!=2 }">
					                  	  			<li><a href="#">普通用户</a></li>
					                  	  		</c:when>
					                  	  		<c:when test="${fn:length(currentUser.sectionList)!=0 && currentUser.type!=2 }">
					                  	  			<li><a href="#">题主</a></li>
					                  	  		</c:when>
					                  	  		<c:otherwise>
					                  	  			<li><a href="#">管理员</a></li>
					                  	  		</c:otherwise>
					                  	  	</c:choose>
											<li><a href="javascript:logout()">退出</a></li>
											<!-- <li><a href="register.jsp">注册</a></li> -->
											<li><a href="javascript:checkUserLogin()">个人中心</a></li>
								</c:when>
						<c:otherwise>
								<li><a href="javascript:login()">登录</a></li>
								<li><a href="register.jsp">注册</a></li>
								<li><a href="javascript:checkUserLogin()">个人中心</a></li>
						</c:otherwise>
						</c:choose>
							</ul>
						</div>					
					</div>
				<!-- </div>		 -->	
			</div>
		<!-- </div> -->
	</div>
</div>
</body>
</html>