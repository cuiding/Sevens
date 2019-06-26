<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>七天情侣首页</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<style>
.carousel-inner {
    height: 350px;
  }
.zyl {
    width: 100%;
    height: 350px;
  }
</style>
</head>
<body>
<div id="header" class="wrap" style="width: 1200px; margin: 0 auto;">
	<jsp:include page="common/top.jsp"/>
	<div id="1" class="container-fluid">
			<div class="carousel slide" id="carousel-796084">
				<ol class="carousel-indicators">
					<li data-slide-to="0" data-target="#carousel-796084">
					</li>
					<li data-slide-to="1" data-target="#carousel-796084">
					</li>
					<li data-slide-to="2" data-target="#carousel-796084">
					</li>
				</ol>
				<div class="carousel-inner">
					<div class="item active left">
						<img alt="" class="zyl" src="images/top_1.jpg" />
						<!-- <div class="carousel-caption"> -->
							<div id="img">
							<!-- 	<img id="top_img" src="images/top.jpg"> -->
								<div id="logo" class="logo">
									<h1><a href="#"><strong>Seven-day Couple</strong></a></h1>
									<p> <a href="#" rel="nofollow"><strong>七天恋爱体验</strong></a></p>
								</div>
							</div>
						<!-- </div> -->
					</div>
					<div class="item next left">
						<img alt="" class="zyl" src="images/top_2.jpg" />
						<!-- <div class="carousel-caption"> -->
							<div id="img">
							<!-- 	<img id="top_img" src="images/top.jpg"> -->
								<div id="logo" class="logo">
									<h1><a href="#"><strong>Seven-day Couple</strong></a></h1>
									<p> <a href="#" rel="nofollow"><strong>七天恋爱体验</strong></a></p>
								</div>
							</div>
						<!-- </div> -->
					</div>
					<div class="item">
						<img alt="" class="zyl" src="images/top.jpg" />
						<!-- <div class="carousel-caption"> -->
							<div id="img">
							<!-- 	<img id="top_img" src="images/top.jpg"> -->
								<div id="logo" class="logo">
									<h1><a href="#"><strong>Seven-day Couple</strong></a></h1>
									<p> <a href="#" rel="nofollow"><strong>七天恋爱体验</strong></a></p>
								</div>
							</div>
						<!-- </div> -->
					</div>
				</div> <a data-slide="prev" href="#carousel-796084" class="left carousel-control">‹</a> <a data-slide="next" href="#carousel-796084" class="right carousel-control">›</a>
			</div>
	</div>
</div>
<div class="container-fluid">
<div id="content" style="width: 1200px; margin: 0 auto;">
	<jsp:include page="common/default.jsp"></jsp:include>
</div>
</div>
<div class="container-fluid">
<div id="footer" style="width: 1200px; margin: 0 auto;">
	<jsp:include page="common/footer.jsp"/>
</div>
</div>
</body>
</html>