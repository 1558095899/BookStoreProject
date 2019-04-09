<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="p" uri="http://www.itcast.cn/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<style type="text/css">
	label.error{
				background:url(images/unchecked.gif) no-repeat 10px 3px;
				padding-left: 30px;
				font-family:georgia;
				font-size: 15px;
				font-style: normal;
				color: red;
			}
			
			label.success{
				background:url(images/checked.gif) no-repeat 10px 3px;
				padding-left: 30px;
			}
</style>
<script type="text/javascript" src="js\jquery-1.8.3.js"></script>
<script type="text/javascript" src="js\jquery.validate.min.js"></script>
<script type="text/javascript" src="js\messages_zh.js"></script>
<script type="text/javascript">
	$(function(){
		$("#form").validate({
			rules:{
				password:{
					required:true,
					digits:true,
				},
				repassword:{
					required:true,
					digits:true,
					equalTo:"[name='password']"
				}
			},
			messages:{
				password:{
					required:"密码不能为空",
					digits:"密码不为数字",
				},
				repassword:{
					required:"确认密码不能为空",
					digits:"密码不为数字",
					equalTo:"前后密码不一致"
				}
			},
			errorElement: "label", //用来创建错误提示信息标签
			success: function(label) { //验证成功后的执行的回调函数
				//label指向上面那个错误提示信息标签label
				label.text(" ") //清空错误提示消息
				.addClass("success"); //加上自定义的success类
			}
		});
	});
	
</script>
</head>

<body class="main">
	<jsp:include page="head.jsp" />

	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%">
					<table width="100%" border="0" cellspacing="0"
						style="margin-top:30px">
						<tr>
							<td class="listtitle">我的帐户</td>
						</tr>
						<tr>
							<td class="listtd"><img src="images/miniicon.gif" width="9"
								height="6" />&nbsp;&nbsp;&nbsp;&nbsp;  <a
								href="${pageContext.request.contextPath }/finduserinfo?id=${u.id}">用户信息修改</a></td>
						</tr>

						<tr>
							<td class="listtd"><img src="images/miniicon.gif" width="9"
								height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath }/orderFindByUser">订单查询</a>
							</td>
						</tr>

						<tr>
							<td class="listtd"><img src="images/miniicon.gif" width="9"
								height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath }/logout">用戶退出</a>
							</td>
						</tr>
					</table></td>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
							href="myAccount.jsp">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;用户信息修改
					</div>





					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<form action="${pageContext.request.contextPath }/modifyuserinfo" method="post" id="form">
									<input type="hidden" name="id" value="${u.id}">
									<table width="100%" border="0" cellspacing="2" class="upline">
										<tr>
											<td style="text-align:right; width:20%">会员邮箱：</td>
											<td style="width:40%; padding-left:20px">${u.getEmail() }</td>
											<td>&nbsp;</td>


										</tr>
										<tr>
											<td style="text-align:right">会员名：</td>
											<td style="padding-left:20px">${u.getUsername() }</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td style="text-align:right">修改密码：</td>
											<td><input type="password" name="password"
												class="textinput" />
											</td>
											
										</tr>
										<tr>
											<td style="text-align:right">重复密码：</td>
											<td><input type="password" class="textinput" name="repassword"/>
											</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td style="text-align:right">性别：</td>
											<td colspan="2">&nbsp;&nbsp;<input type="radio"
												name="gender" value="男" ${u.getGender()=="男" ? "checked='checked'":"" } /> 男
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
												type="radio" name="gender" value="女" ${u.getGender()=="女" ? "checked='checked'":"" }/> 女</td>
										</tr>
										<tr>
											<td style="text-align:right">联系方式：</td>
											<td colspan="2"><input name="telephone" type="text"
												value="${u.getTelephone() }" class="textinput" />
											</td>
										</tr>

										<tr>
											<td style="text-align:right">&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
									</table>





									<p style="text-align:center">

										<input type="image" src="images/botton_gif_025.gif" border="0">

									</p>
									<p style="text-align:center">&nbsp;</p>
								</form></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
