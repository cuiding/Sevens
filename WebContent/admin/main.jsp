<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/unicorn.main.css" />
<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.ui.custom.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.uniform.js"></script>
<!-- <script src="js/select2.min.js"></script> -->
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/unicorn.js"></script>
<script src="js/unicorn.tables.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadPreview.min.js"></script>
<script type="text/javascript">
$(function(){
	var sectionPage="section.jsp";
	var topicPage="topic.jsp";
	var userPage="user.jsp";
	var zonePage="zone.jsp";
	var curPage='${mainPage}';
	if(sectionPage.indexOf(curPage)>=0&&curPage!=""){
		$("#sectionLi").addClass("active");
	} else if(topicPage.indexOf(curPage)>=0&&curPage!=""){
		$("#topicLi").addClass("active");
	} else if(userPage.indexOf(curPage)>=0&&curPage!=""){
		$("#userLi").addClass("active");
	} else if(zonePage.indexOf(curPage)>=0&&curPage!=""){
		$("#zoneLi").addClass("active");
	}
})
</script>
</head>
<%
if(session.getAttribute("currentUser")==null){
	response.sendRedirect("login.jsp");
	return;
}
%>
<body>
	<div id="header">
		<!-- <h1 style="margin-left: 0px;padding-left: 0px;"><a href="#">七天情侣</a></h1>	 -->
		<!-- <h2 style="padding: 0px; margin-top: 10px; margin-bottom: 0px;">
			<a href="#"><font color="#cccccc">七天情侣活动</font></a>
		</h2>
		<h3 style="margin: 0px 0px 0px 40px;">
			<a href="#"><font color="#cccccc">后台管理</font></a>
		</h3> -->
	</div>
	<div id="sidebar">
		<ul>
			<!-- <li id="userMatch"><a href="User_listMatch.action"><i class="icon icon-home"></i> <span>情侣匹配</span></a></li> -->
			<li id="zoneLi"><a href="Zone_list.action"><i class="icon icon-home"></i> <span>更新版块</span></a></li>
			<li id="sectionLi"><a href="Section_list.action"><i class="icon icon-home"></i> <span>发布话题</span></a></li>
			<li id="topicLi"><a href="Topic_listAdmin.action"><i class="icon icon-home"></i> <span>论坛管理</span></a></li>
			<li id="userLi"><a href="User_list.action"><i class="icon icon-home"></i> <span>用户管理</span></a></li>
			<!-- <li class="submenu"><a href="#"><i class="icon icon-th-list"></i>
					<span>系统管理</span> <span class="label">3</span></a>
				<ul>
					<li><a href="#">修改密码</a></li>
					<li><a href="#">安全退出</a></li>
					<li><a href="#">刷新系统缓存</a></li>
				</ul></li> -->
		</ul>

	</div>
	<div id="content">
		<div id="content-header">
			<h1>后台管理</h1>
			<!-- <div class="btn-group">
				<a class="btn btn-large tip-bottom" title="Manage Files"><i
					class="icon-file"></i></a> <a class="btn btn-large tip-bottom"
					title="Manage Users"><i class="icon-user"></i></a> <a
					class="btn btn-large tip-bottom" title="Manage Comments"><i
					class="icon-comment"></i><span class="label label-important">5</span></a>
				<a class="btn btn-large tip-bottom" title="Manage Orders"><i
					class="icon-shopping-cart"></i></a>
			</div> -->
		</div>
		<div id="breadcrumb">
			<a href="#" title="首页" class="tip-bottom">
			<i class="icon-home"></i> 首页</a> <a href="#" class="current">${crumb1 }</a>
		</div>
		<jsp:include page="${mainPage }"></jsp:include>
		<div class="row-fluid">
			<div id="footer" class="span12">
			<!-- 这里要改？？ -->
				2019 &copy; 七天情侣报名网.作者：小崔&nbsp;&nbsp;&nbsp;&nbsp; <a href="../index.jsp">七天情侣报名网</a>
			</div>
			
		</div>
	</div>
</body>
</html>