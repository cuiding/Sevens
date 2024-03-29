<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadPreview.min.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
$(function () {
	$("#logo").uploadPreview({ Img: "ImgPr", Width: 220, Height: 220 });
});
function openAddDlg(){
	$("#myModalLabel").html("增加话题");
}
function saveSection(){
	 if ($("#sectionName").val()==null||$("#sectionName").val()=='') {
		 $("#error").html("请输入话题名称！");
		 return false;
	 }
	 if ($("#zone").val()==null||$("#zone").val()=='') {
		 $("#error").html("请选择所属版块！");
		 return false;
	 }
	 if ($("#masterId").val()==null||$("#masterId").val()=='') {
		 $("#error").html("请输入题主昵称！");
		 return false;
	 }
	 /* $.post("Section_save.action", $("#fm").serialize()); */
	 $("#fm").submit();
	 alert("保存成功！");
	 resetValue();
	 location.reload(true);
 }
 function modifySection(id,name,zone,masterNickName,logo){
	 $("#myModalLabel").html("修改话题");
	 $("#id").val(id);
	 $("#sectionName").val(name);
	 $("#ImgPr").attr("src","${pageContext.request.contextPath}/"+logo);
	 $("#zone").val(zone);
	 $("#masterNickName").val(masterNickName);
 }
function sectionDelete(sectionId){
	if(confirm("确定要删除这条数据吗?")){
		$.post("Section_delete.action",{sectionId:sectionId},
				function(result){
					var result=eval('('+result+')');
					if(result.error){
						alert(result.error);
					}else{
						alert("删除成功！");
						window.location.reload(true);
					}
				}
			);
	}
}
function deleteSections(){
	var selectedSpan=$(".checked").parent().parent().next("td");
	if(selectedSpan.length==0){
		alert("请选择要删除的数据！");
		return;
	}
	var strIds=[];
	for(var i=0;i<selectedSpan.length;i++){
		strIds.push(selectedSpan[i].innerHTML);
	}
	var ids=strIds.join(",");
	if(confirm("您确定要删除这"+selectedSpan.length+"条数据吗？")){
		$.post("Section_delete1.action",{ids:ids},function(result){
			if(result.success){
				alert("数据已成功删除！");
				location.reload(true);
			}else{
				alert("数据删除失败！");
			}
		},"json");
	}else{
		return;
	}
}
function resetValue(){
	 $("#id").val("");
	 $("#sectionName").val("");
}
function searchUserByNickName(userNickName){
	$.post("Section_getUserByNickName.action",{nickName:userNickName},function(result){
		var result=eval('('+result+')');
		$("#info").html(result.info);
		$("#masterId").val(result.masterId);
	});
}
</script>
</head>
<body>
	<div class="container-fluid">
		<div id="tooBar" style="padding: 10px 0px 0px 10px;">
			<button class="btn btn-primary" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return openAddDlg()">添加话题</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" role="button" class="btn btn-danger" onclick="javascrip:deleteSections()">批量删除</a>
			<form action="Section_list.action" method="post" class="form-search" style="display: inline;">
	          &nbsp;话题名称：
			  <input name="s_section.name" value="${s_section.name }" type="text" class="input-medium search-query" placeholder="输入话题名称..."/>
			  &nbsp;所属版块：
			  <select name="s_section.zone.id"><option value="">请选择...</option>
				<c:forEach var="zone" items="${zoneList }">
					<option value="${zone.id }" ${s_section.zone.id==zone.id?'selected':'' }>${zone.name }</option>
				</c:forEach>
			  </select>
			  &nbsp;题主：
			  <select name="s_section.master.id"><option value="">请选择...</option>
				<c:forEach var="master" items="${masterList }">
					<option value="${master.id }" ${s_section.master.id==master.id?'selected':'' }>${master.nickName }</option>
				</c:forEach>
			  </select>
			  &nbsp;
			  <button type="submit" class="btn btn-primary" title="Search">查询&nbsp;<i class="icon  icon-search"></i></button>
			</form>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<!-- <span class="icon"> <input type="checkbox"
							id="title-checkbox" name="title-checkbox" />
						</span> -->
						<h5>话题列表</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th><i class=""></i></th>
									<th>编号</th>
									<th>话题名称</th>
									<th>话题logo</th>
									<th class="th">所属版块</th>
									<th>题主</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${sectionList }" var="section">
									<tr>
										<td><input type="checkbox" /></td>
										<td style="text-align: center;vertical-align: middle;">${section.id }</td>
										<td style="text-align: center;vertical-align: middle;">${section.name }</td>
										<td style="text-align: center;vertical-align: middle;width: 110px;vertical-align: middle;">
											<img style="width: 100px;" src='${pageContext.request.contextPath}/${section.logo }'></img>
										</td>
										<td style="text-align: center;vertical-align: middle;">${section.zone.name }</td>
										<td style="text-align: center;vertical-align: middle;">${section.master.nickName }</td>
										<td style="text-align: center;vertical-align: middle;">
											<button class="btn btn-info" type="button" data-backdrop="static" data-toggle="modal" data-target="#dlg" onclick="return modifySection(${section.id},'${section.name }',${section.zone.id },'${section.master.nickName }','${section.logo }')">修改
											</button>&nbsp;&nbsp;<button class="btn btn-danger" type="button" onclick="javascript:sectionDelete(${section.id})">删除</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="pagination alternate">
					<ul class="clearfix">${pageCode }
					</ul>
				</div>


			</div>
		</div>
		<div id="dlg" class="modal hide fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="return resetValue()">×</button>
				<h3 id="myModalLabel">增加话题</h3>
			</div>
			<div class="modal-body">
				<form id="fm" action="Section_save.action" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<td>
								<label class="control-label" for="sectionName">请输入话题名称：</label>
							</td>
							<td>
								<input id="sectionName" type="text" name="section.name" placeholder="请输入…">
							</td>
						</tr>
						<tr>
							<td>
								<img id="ImgPr" class="pull-left" style="width: 120px; height: 120px;" src="${pageContext.request.contextPath}/${section.logo }" />
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="logo">上传logo：</label>
							</td>
							<td>
								<input type="file" id="logo" name="logo">
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="zone">请选择所属版块：</label>
							</td>
							<td>
								<select id="zone" name="section.zone.id"><option value="">请选择...</option>
									<c:forEach var="zone" items="${zoneList }">
										<option value="${zone.id }">${zone.name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label" for="masterNickName">题主：</label>
							</td>
							<td>
								<input id="masterNickName" type="text" name="section.master.nickName" onkeydown="javascript:searchUserByNickName(this.value)" placeholder="请输入昵称回车">
								<font id="info" style="color: red;"></font>
							</td>
						</tr>
					</table>
					<input id="id" type="hidden" name="section.id">
					<input id="masterId" type="hidden" name="section.master.id">
				</form>
			</div>
			<div class="modal-footer">
				<font id="error" style="color: red;"></font>
				<button class="btn" data-dismiss="modal" aria-hidden="true"
					onclick="return resetValue()">关闭</button>
				<button class="btn btn-primary" onclick="javascript:saveSection()">保存</button>
				<!-- <button class="btn btn-primary" type="submit">保存</button> -->
			</div>
		</div>
	</div>
</body>
</html>