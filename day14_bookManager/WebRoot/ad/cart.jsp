<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css" />

<script type="text/javascript">
	function change(id,num,pnum){
		var num=parseInt(num);
		var pnum=parseInt(pnum);
		if(num<1){
			if(confirm("是否要删除？")){
				num=0;
			}else{
				num=1;
			}
		}
		if(num>pnum){
			alert("数量超过库存");
			num=pnum;
		}
		location.href="${pageContext.request.contextPath}/changeCountServlet?id="+id+"&num="+num;
	}
	
</script>

</head>

<body class="main">

	<jsp:include page="head.jsp" />

	<jsp:include page="menu_search.jsp" />


	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车
					</div>

					<table cellspacing="0" class="infocontent">
						<tr>
							<td><img src="ad/page_ad.jpg" width="666" height="89" />
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td><img src="images/buy1.gif" width="635" height="38" />
										</td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">序号</td>
													<td width="30%">商品名称</td>
													<td width="10%">价格</td>
													<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</td>
													<td width="10%">库存</td>
													<td width="10%">小计</td>
													<td width="10%">取消</td>
												</tr>
											</table> 
										<c:set var="sum" value="0" > </c:set>
									<c:forEach items="${cart }" var="cart" varStatus="c">
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td width="10%">${c.count }</td>
														<td width="30%">${cart.key.name }</td>

														<td width="10%">${cart.key.price }</td>
														<td width="20%"><input type="button" value='-'
															style="width:20px"
															onclick="change('${cart.key.id}','${cart.value-1}','${cart.key.pnum }')">

															<input name="text" type="text" value="${cart.value}"
															style="width:40px;text-align:center" /><input
															type="button" value='+' style="width:20px"
															onclick="change('${cart.key.id}','${cart.value+1}','${cart.key.pnum }')">

														</td>
														<td width="10%">${cart.key.pnum }</td>
														<td width="10%">${cart.value*cart.key.price }</td>

														<td width="10%"><a href="${pageContext.request.contextPath}/changeCountServlet?id=${cart.key.id}&num=0"
															style="color:#FF0000; font-weight:bold">X</a></td>
													</tr>
												</table>
												<c:set var="sum" value="${sum+cart.value*cart.key.price }"> </c:set>
									</c:forEach >
									
											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align:right; padding-right:40px;"><font
														style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;${sum}元</font>
													</td>
												</tr>
											</table>
											<div style="text-align:right; margin-top:10px">
												<a
													href=""><img
													src="images/gwc_jx.gif" border="0" /> </a>

												&nbsp;&nbsp;&nbsp;&nbsp;<a
													href="${pageContext.request.contextPath}/order.jsp"><img
													src="images/gwc_buy.gif" border="0" /> </a>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
