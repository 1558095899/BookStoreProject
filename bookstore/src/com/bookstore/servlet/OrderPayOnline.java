package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.until.PaymentUtil;

public class OrderPayOnline extends HttpServlet {
//订单网上支付功能
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderid = request.getParameter("orderid");
		String money = request.getParameter("money");
		String pd_FrpId=request.getParameter("yh");// 银行
		//第三方支付请求参数        参考易宝接口文档
		String p0_Cmd="Buy";//业务类型  固定Buy
		String p1_MerId="10001126856";//商户编号
		String p2_Order=orderid;//商户订单号
		String p3_Amt=money;//支付金额
		String p4_Cur="CNY";//交易币种
		String p5_Pid="unknow";//
		String p6_Pcat="unknow";//
		String p7_Pdesc="unknow";//
		// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
		// 第三方支付可以访问网址
		String p8_Url="http//localhost/bookstore/callback";
		String p9_SAF="0";//送货地址  默认0
		String pa_MP="unknow";//商户扩展信息
		String pr_NeedResponse="1";// 应答机制   固定值1
		// 加密hmac 需要密钥
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
		//将数据传入confirm.jsp
		request.setAttribute("p0_Cmd",p0_Cmd);
		request.setAttribute("p1_MerId",p1_MerId);
		request.setAttribute("p2_Order",p2_Order);
		request.setAttribute("p3_Amt",p3_Amt);
		request.setAttribute("p4_Cur",p4_Cur);
		request.setAttribute("p5_Pid",p5_Pid);
		request.setAttribute("p6_Pcat",p6_Pcat);
		request.setAttribute("p7_Pdesc",p7_Pdesc);
		request.setAttribute("p8_Url",p8_Url);
		request.setAttribute("p9_SAF",p9_SAF);
		request.setAttribute("pa_MP",pa_MP);
		request.setAttribute("pd_FrpId",pd_FrpId);
		request.setAttribute("pr_NeedResponse",pr_NeedResponse);
		request.setAttribute("hmac",hmac);
		
		request.getRequestDispatcher("confirm.jsp").forward(request, response);
		/*//获取用户提交的数据
				String orderid = request.getParameter("orderid");
				String money = request.getParameter("money");
				String pd_FrpId = request.getParameter("yh");
				
				String p0_Cmd ="Buy";
				String p1_MerId ="10001126856";
				String p2_Order =orderid;
				String p3_Amt =money;
				String p4_Cur = "CNY";
				String p5_Pid ="unknow";
				String p6_Pcat ="unknow";
				String p7_Pdesc ="unknow";
				String p8_Url ="http://localhost/bookstore/callBack";
				String p9_SAF ="1";
				String pa_MP ="unknow";
				String pr_NeedResponse ="1";
				String  hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
			
				//response.sendRedirect("https://www.yeepay.com/app-merchant-proxy/node?p0_Cmd="+p0_Cmd+"&p1_MerId"+"");
				request.setAttribute("pd_FrpId", pd_FrpId);
				request.setAttribute("p0_Cmd", p0_Cmd);
				request.setAttribute("p1_MerId", p1_MerId);
				request.setAttribute("p2_Order", p2_Order);
				request.setAttribute("p3_Amt", p3_Amt);
				request.setAttribute("p4_Cur", p4_Cur);
				request.setAttribute("p5_Pid", p5_Pid);
				request.setAttribute("p6_Pcat", p6_Pcat);
				request.setAttribute("p7_Pdesc", p7_Pdesc);
				request.setAttribute("p8_Url", p8_Url);
				request.setAttribute("p9_SAF", p9_SAF);
				request.setAttribute("pa_MP", pa_MP);
				request.setAttribute("pr_NeedResponse", pr_NeedResponse);
				request.setAttribute("hmac", hmac);
				//https://www.yeepay.com/app-merchant-proxy/node?pd_FrpId=CCB-NET-B2C&p0_Cmd=Buy&p1_MerId=10001126856&p2_Order=e1fefa11-c639-470c-bbc2-9a320e6885a9&p3_Amt=10.0&p4_Cur=CNY&p5_Pid=unknow&p6_Pcat=unknow&p7_Pdesc=unknow&p8_Url=http%3A%2F%2Flocalhost%2Fbookstore%2FcallBack&p9_SAF=1&pa_MP=unknow&pr_NeedResponse=1&hmac=08d85cb26b489368665d282cd07e90a3
				
				request.getRequestDispatcher("/confirm.jsp").forward(request, response);*/
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
