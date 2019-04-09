<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ajax.js">
	
</script>
<script type="text/javascript">
	function searchName(){
		var name=document.getElementById("name");
		//获取异步对象
		var xhr=getXMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var div=document.getElementById("div");
				var s=eval(xhr.responseText);//["疯狂Java讲义","Java web","javaee","java"]遍历数组即可
				//分割字符串
				//var s=str.split(",");
				var paDiv="";
				if(name.value==""){//当搜索框为空时   隐藏
					div.style.display="none";
					return;
				}
				for(var i=0;i<s.length;i++){
					paDiv+="<div onmouseover='over(this)' onmouseout='out(this)' onclick='cl(this)'>"+s[i]+"</div>";
				}
				div.innerHTML=paDiv;
				div.style.display="block";//当获取到数据时  块级显示
			}
		}
		//异步处理请求
		xhr.open("get", "${pageContext.request.contextPath}/searchAJAXInfoServlet?name="+name.value+"&time="+new Date().getTime());
		xhr.send(null);//将请求发送到服务器
	}
	function over(div){
		div.style.backgroundColor="gray";
	}
	function out(div){
		div.style.backgroundColor="";
	}
	function cl(div){
		var name=document.getElementById("name");
		name.value=div.innerHTML;//向搜索框给值
		var d=document.getElementById("div");
		d.style.display="none";
	}

</script>
	<form action="${pageContext.request.contextPath}/findProductBySearch"
		method="post">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td style="text-align:right; padding-right:220px" >
				Search <input
					type="text" name="name" class="inputtable" onkeyup="searchName();"
					id="name" /> 
					<input type="image" src="images/serchbutton.gif"
					border="0" style="margin-bottom:-4px">
				</td>
			</tr>
		</table>

	</form>
</div>
<div id="div" style=";width:145;background-color: yellow;border: 1px solid red;margin:-20 830" >
	
</div>