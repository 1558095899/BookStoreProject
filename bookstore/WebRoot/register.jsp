<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>bookStore注册页面</title>
<%--导入css --%>
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
	function changeImage() {

		document.getElementById("img").src = "${pageContext.request.contextPath}/imageCode?time="
				+ new Date().getTime();
	}
	$(function(){
	
		$("#form").validate({
			rules:{
				email:{
					required:true,
					email:true
				},
				username:{
					required:true,
					maxlength:6
				},
				password:{
					required:true,
					digits:true,
					maxlength:10
				},
				repassword:{
					required:true,
					equalTo:"[name='password']"
				},
				gender:{
					required:true
				},
				telephone:{
					required:true
				}
			},
			messages:{
				email:{
					required:"邮箱不能为空",
					email:"邮箱格式不正确"
				},
				username:{
					required:"会员名不能为空",
					maxlength:"会员名长度不能超过6位"
				},
				password:{
					required:"密码不能为空",
					digits:"密码不为数字",
					maxlength:"密码长度不超过10位"
				},
				repassword:{
					required:"重复密码不能为空",
					equalTo:"前后密码不一致"
				},
				gender:{
					required:"性别不能为空"
				},
				telephone:{
					required:"电话不能为空"
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
	/* $(function(){
	
	$("#ck").blur(function(){
		var value=$("#ck").val();
		
		$.ajax({
			url:"${pageContext.request.contextPath}/register",
			asyn:true,
			type:"post",
			data:{"value":value},
			dataType:"text",
			success:function(data){
				var span=$("#span").css("color","red")
				span.html(data);
			}
		});
	});
		
	}); */
</script>
</head>


<body class="main">

	

	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/register"
			method="post" id="form">
			<table width="850px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px">
						<h1 style="display: inline;">新会员注册</h1>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red" size=8>${msg_error}</font>
						
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">会员邮箱：</td>
								<td style="width:40%">
								<input type="text" class="textinput"
									name="email" /></td>
								
							</tr>
							<tr>
								<td style="text-align:right">会员名：</td>
								<td>
									<input type="text" class="textinput" name="username" />
								</td>
								
							</tr>
							<tr>
								<td style="text-align:right">密码：</td>
								<td><input type="password" class="textinput"
									name="password" /></td>
								
							</tr>
							<tr>
								<td style="text-align:right">重复密码：</td>
								<td><input type="password" class="textinput"
									name="repassword" /></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td style="text-align:right">性别：</td>
								<td colspan="2">&nbsp;&nbsp;<input type="radio"
									name="gender" value="男" checked="checked" id="sex"/> 男
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"
									name="gender" value="女" id="sex"/> 女
									<label for="sex" class="error" style="display: none;"></label>
									</td>
								
							</tr>
							<tr>
								<td style="text-align:right">联系电话：</td>
								<td colspan="2"><input type="text" class="textinput"
									style="width:350px" name="telephone" /></td>
							</tr>
							<tr>
								<td style="text-align:right">个人介绍：</td>
								<td colspan="2"><textarea class="textarea" name="introduce"></textarea>
								</td>
							</tr>

						</table>



						<h1>注册校验</h1>
						<table width="80%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">输入校验码：</td>
								<td style="width:50%"><input type="text" name="ckcode" id="ck" class="textinput" />
								</td>
								<td><span id="span"></span></td>
							</tr>
							<tr>
								<td style="text-align:right;width:20%;">&nbsp;</td>
								<td colspan="2" style="width:50%"><img
									src="${pageContext.request.contextPath}/imageCode" width="180"
									height="30" class="textinput" style="height:30px;" id="img" />&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="changeImage()">看不清换一张</a>
								</td>
							</tr>
						</table>



						<table width="70%" border="0" cellspacing="0">
							<tr>
								<td style="padding-top:20px; text-align:center"><input
									type="image" src="images/signup.gif" name="submit" border="0">
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</form>
	</div>



	


</body>
</html>
