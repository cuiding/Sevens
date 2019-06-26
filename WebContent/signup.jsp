<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>七天情侣注册</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
<link href="bootstrap/css/docs.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.messages_cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadPreview.min.js"></script>
<style type="text/css">
button{cursor:pointer;width:100%;height:44px;padding:0;background:#ef4300;border:1px solid #ff730e;border-radius:6px;font-weight:700;color:#fff;font-size:24px;letter-spacing:15px;margin-top:10px; text-shadow:0 1px 2px rgba(0,0,0,.1)}
span{color:Red;font-size:12px}
</style>
<script type="text/javascript">
$(function () {
	$("#face").uploadPreview({ Img: "ImgPr", Width: 220, Height: 220 });
	$("#regForm").validate(
            {
                /*自定义验证规则*/
                rules: {
                	"user.major":{ required: true }
                },
                /*错误提示位置*/
                errorPlacement: function(error, element) {
                    error.appendTo(element.siblings("span"));
                }
            }
          );
});
function checkForm(){
	var major=$("#major").val();
/* 	var choice=$("#choice").val(); */
	var need=$("#need").val();
	var Content=$("#Content").val();
	if (major=="") {
		$("#error").html("专业不能为空！");
		return false;
	}
	/* if (choice=="") {
		$("#error").html("匹配类型不能为空！");
		return false;
	} */
	if (need=="" && choice=="按要求匹配") {
		$("#error").html("匹配要求不能为空！");
		return false;
	if (Content=="") {
		$("#error").html("个人简介不能为空！");
		return false;
	}
	return true;
}
</script>
</head>
<body>
	<div id="header" class="wrap">
		<jsp:include page="common/top.jsp"/>
	</div>
	<div class="" align="center" style="margin-top: 50px;">
		<h1 style="margin-bottom: 30px;">欢迎报名</h1>
		<form id="regForm" action="User_saveSignUp.action" class="form-horizontal form-inline" method="post" style="width: 700px;" onsubmit="return checkForm()">
			<input type="hidden" name="user.id" value="${currentUser.id }">
			<div class="control-group">
				<label class="control-label" for="major">专业(*)：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="major" name="user.major" onblur="checkNickName(this.value)" value="${user.nickName }"/><span class="pull-left"></span>
					<font id="userErrorInfo" class="pull-right" color="red"></font>
				</div>
			</div>		
			<div class="control-group">
				<label class="control-label" for="choice">匹配要求(*)：</label>
				<div class="controls">
					<label class="radio" style="margin-right: 50px;">
						<select id="choice" name="user.choice"><option value="">请选择...</option>
							<option value="随机匹配" ${user.choice=='随机匹配'?'selected':'' }>随机匹配</option>
							<option value="按要求匹配" ${user.choice=='按要求匹配'?'selected':'' }>按要求匹配</option>
						</select> <span class="pull-left"></span>
					</label>
				</div>
			</div>
			<!-- ？？？？这里要改？？？配对要求？？关键词？？指定专业？？？ -->
			<div class="control-group">
				<label class="control-label" for="need">匹配要求(若随机匹配，此项不需填写)：</label>
				<div class="controls">
					<input class="input-block-level" type="text" id="need" name="user.need"  value="${user.need }"/><span class="pull-left"></span>
				</div>
			</div> 
			<div class="control-group">
				<label class="control-label" for="Content">个人简介(*)</label>
					<div class="controls">
						<textarea name="user.personDesc" id="Content" class="ckeditor" cols="50" style="height:200px;width: 500px;" ></textarea>
					</div>
				</div>
			<div class="control-group" style="margin: 0px;">
				<div style="margin-left: 70px;">
					<button type="submit" tabindex="5">提交报名</button> &nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div>
			<font id="error" color="red"></font>
			
		</form>
	</div>
</body>
</html>