<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>七天情侣论坛</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/uniform.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/jquery.ui.custom.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/unicorn.tables.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<div id="header" class="wrap" style="width: 1200px; margin: 0 auto;">
	<jsp:include page="../common/top.jsp"/>
</div>
<div style="width: 1200px; margin: 0 auto;">
	<h2>报名信息</h2>
	<div style="margin-bottom: 10px;">
		<div class="pagination alternate pull-right" align="center" style="margin: 0px;">
			<ul class="clearfix">${pageCode}</ul>
		</div>
	</div>
	<table border="0" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 8;">
		<tr>
			<td>
				<table class="table table-bordered" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 8;">
					<tr>
						<th style="text-align: center;vertical-align: middle; width: 100px;">
							昵称
						</th>
						<th style="text-align: center;vertical-align: middle; width: 100px;">
							性别
						</th>
						<th style="text-align: center;vertical-align: middle;width: 100px;">
							照片
						</th>
						<th style="text-align: center;vertical-align: middle; width: 100px;">
							注册时间
						</th>
						<th style="text-align: center;vertical-align: middle; width: 100px;">
							邮箱
						</th>
						<th style="text-align: center;vertical-align: middle; width: 100px;">
							电话
						</th>
						<th style="text-align: center;vertical-align: middle; width: 100px;">
							专业
						</th>
						<th style="text-align: center;vertical-align: middle; width: 100px;">
							学号
						</th>
						<th style="text-align: center;vertical-align: middle; width: 100px;">
							个人简介
						</th>
					</tr>
					<c:forEach items="${userList }" var="user">
					<tr>
						<td style="text-align: center;vertical-align:middle;">
							${user.nickName}
						</td>
						<td style="text-align: center;vertical-align:middle;">
							${user.sex}
						</td>
						<td style="text-align: center;vertical-align: middle;">
						<c:choose>
						<c:when test="${(user.face==null||user.face=='')&&user.sex=='男'}">
							<img alt=""
								src="${pageContext.request.contextPath}/images/user/user0.gif"
								style="width: 100px; height: 100px;">
						</c:when>
						<c:when test="${(user.face==null||user.face=='')&&user.sex=='女'}">
							<img alt=""
								src="${pageContext.request.contextPath}/images/user/female.gif"
								style="width: 100px; height: 100px;">
						</c:when>
						<c:otherwise>
							<img alt="" src="${pageContext.request.contextPath}/${user.face}" style="width: 100px; height: 100px;">
						</c:otherwise>
						</c:choose>
						</td>
						<td style="text-align: center;vertical-align:middle;">
							${user.regTime}
						</td>
						<td style="text-align: center;vertical-align:middle;">
							${user.email}
						</td>
						<td style="text-align: center;vertical-align:middle;">
							${user.mobile}
						</td>
						<td style="text-align: center;vertical-align:middle;">
							${user.major}
						</td>
						<td style="text-align: center;vertical-align:middle;">
							${user.stuId}
						</td>
						<td style="text-align: center;vertical-align:middle;">
							${user.personDesc}
						</td>
					</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
</div>
<div id="footer" style="width: 1200px; margin: 0 auto;">
	<jsp:include page="../common/footer.jsp"/>
</div>
</body>
</html>