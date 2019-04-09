<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script type="text/javascript">
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/admin/products/add.jsp";
	}
	//删除提示
	function del(id,name){
		if(confirm("是否删除"+name+"?")){
			location.href="${pageContext.request.contextPath}/deleteBookServlet?id="+id;	
		}
			
	}
	//复选框全选
	function checkAll(){
	var cke=document.getElementById("cke").checked;//获取cke状态   当点击复选框时 返回true
	var c=document.getElementsByName("c");//通过获取name来    获取具有相同name的节点   返回一个节点集合
		for(var i=0;i<c.length;i++){
			c[i].checked=cke;
		}
	}
	//批量删除
	function deleteAll(){
		str="";
		var c=document.getElementsByName("c");//获取所有的checkbox节点
		for(var i=0;i<c.length;i++){//遍历
			if(c[i].checked){//当复选框被选中时
					str+="id="+c[i].value+"&";//获取第i个的value值即id值        id=1&id=2&id=3&
			}
		}
		str=str.substring(0, str.length-1);//减掉最后多出来的&
		if(str!=null){//不为空
			location.href="${pageContext.request.contextPath}/deleteAllServlet?"+str;//跳转
		}
		
	}
</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/findProductByManyCondition"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>查
							询 条 件</strong>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									商品编号</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="id" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									类别：</td>
								<td class="ta_01" bgColor="#ffffff"><select name="category"
									id="category">
										<option value="" selected="selected">--选择商品类加--</option>
										<option value="文学">文学</option>
										<option value="生活">生活</option>
										<option value="计算机">计算机</option>
										<option value="外语">外语</option>
										<option value="经营">经营</option>
										<option value="励志">励志</option>
										<option value="社科">社科</option>
										<option value="学术">学术</option>
										<option value="少儿">少儿</option>
										<option value="艺术">艺术</option>
										<option value="原版">原版</option>
										<option value="科技">科技</option>
										<option value="考试">考试</option>
										<option value="生活百科">生活百科</option>
								</select></td>
							</tr>

							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									商品名称：</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="name" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									价格区间(元)：</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="minprice" size="10" value="" />- <input type="text"
									name="maxprice" size="10" value="" /></td>
							</tr>

							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe"
									class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff"><font face="宋体"
									color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01"><br>
									<br></td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<button type="submit" id="search" name="search"
										value="&#26597;&#35810;" class="button_view">
										&#26597;&#35810;</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
									type="reset" name="reset" value="&#37325;&#32622;"
									class="button_view" />
								</td>
							</tr>
						</table>
					</td>

				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
					</TD>
				</tr>
				<tr>
				
					<td class="ta_01" align="right">
						<button type="button" id="del" name="delete" value="批量删除" onclick="deleteAll()">批量删除
						</button>
						<button type="button" id="add" name="add" value="&#28155;&#21152;"
							class="button_add" onclick="addProduct()">&#28155;&#21152;
						</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="8%">
									<input type="checkbox" name="ck" id="cke" onclick="checkAll()">全选/全不选
								</td>
								<td align="center" width="24%">商品编号</td>
								<td align="center" width="18%">商品名称</td>
								<td align="center" width="9%">商品价格</td>
								<td align="center" width="9%">商品数量</td>
								<td width="8%" align="center">商品类别</td>
								<td width="8%" align="center">编辑</td>
								<td width="8%" align="center">删除</td>
							</tr>

					<c:forEach items="${book }" var="b">			
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td align="center" width="8%">
									<input type="checkbox" name="c" value="${b.id }";>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="23">${b.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${b.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${b.price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${b.pnum }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">${b.category }</td>
									<td align="center" style="HEIGHT: 22px" width="7%"><a
										href="${pageContext.request.contextPath}/findBookByIdServlet?id=${b.id }">
											<img
											src="${pageContext.request.contextPath}/admin/images/i_edit.gif"
											border="0" style="CURSOR: hand"> </a>
									</td>

									<td align="center" style="HEIGHT: 22px" width="7%"><a
										onclick="del('${b.id}','${b.name }')"><%-- <a href="javascript:delBook('${b.id }','${b.name }')"> --%>
											<img
											src="${pageContext.request.contextPath}/admin/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a>
									</td>
								</tr>
					</c:forEach>	
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

