package com.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.UserException.UserException;
import com.bookstore.service.OrderService;
import com.bookstore.until.PaymentUtil;

public class Callback extends HttpServlet {
/**
	 * 该Servlet会在支付成功后 进行调用----- 支付公司 、客户
	 * 
	 * @author 
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				// 获得回调所有数据
				String p1_MerId = request.getParameter("p1_MerId");
				String r0_Cmd = request.getParameter("r0_Cmd");
				String r1_Code = request.getParameter("r1_Code");//支付结果   固定值 “1”, 代表支付成功. 
				String r2_TrxId = request.getParameter("r2_TrxId");
				String r3_Amt = request.getParameter("r3_Amt");
				String r4_Cur = request.getParameter("r4_Cur");
				String r5_Pid = request.getParameter("r5_Pid");
				String r6_Order = request.getParameter("r6_Order");//商户订单号
				String r7_Uid = request.getParameter("r7_Uid");
				String r8_MP = request.getParameter("r8_MP");
				String r9_BType = request.getParameter("r9_BType");//交易结果返回类型为“1”: 浏览器重定向;  为“2”: 服务器点对点通讯.
				String rb_BankId = request.getParameter("rb_BankId");
				String ro_BankOrderId = request.getParameter("ro_BankOrderId");
				String rp_PayDate = request.getParameter("rp_PayDate");
				String rq_CardNo = request.getParameter("rq_CardNo");
				String ru_Trxtime = request.getParameter("ru_Trxtime");
				// 身份校验 --- 判断是不是支付公司通知你
				String hmac = request.getParameter("hmac");
				// keyValue = 69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl
				String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
						"keyValue");

				// 自己对上面数据进行加密 --- 比较支付公司发过来hamc
				boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
						r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
						r8_MP, r9_BType, keyValue);
				if (isValid) {//true
					// 响应数据有效
					if(r1_Code.equals("1")){//支付成功
						if (r9_BType.equals("1")) {//交易结果返回类型为“1”: 浏览器重定向;  为“2”: 服务器点对点通讯.
							// 浏览器重定向
							response.setContentType("text/html;charset=utf-8");
							response.getWriter().println("<h1>付款成功！等待商城进一步操作！等待收货...</h1>");
						} else if (r9_BType.equals("2")) {
							// 服务器点对点 --- 支付公司通知你
							System.out.println("付款成功！");
							// 修改订单状态 为已付款
							// 回复支付公司
							response.getWriter().print("success");
						}
						//修改订单状态为已支付
						OrderService os = new OrderService();
						try {
							os.modifyOrderState(r6_Order);
						} catch (UserException e) {
							System.out.println(e.getMessage());
						}
						//重定向到成功页面
						response.sendRedirect(request.getContextPath()+"/paysuccess.jsp");
						//os.modifyPayState(r6_Order);
					}
					
				} else {
					// 数据无效
					System.out.println("数据被篡改！");
				}
				
				
		/*response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String  p1_MerId = request.getParameter("p1_MerId");
		String  r0_Cmd = request.getParameter("r0_Cmd");
		String  r1_Code = request.getParameter("r1_Code");//支付结果，1代表支付成功
		String  r2_TrxId = request.getParameter("r2_TrxId");
		String  r3_Amt = request.getParameter("r3_Amt");
		String  r4_Cur = request.getParameter("r4_Cur");
		String  r5_Pid = request.getParameter("r5_Pid");
		String  r6_Order = request.getParameter("r6_Order");//商户订单号
		String  r7_Uid = request.getParameter("r7_Uid");
		String  r8_MP = request.getParameter("r8_MP");
		String  r9_BType = request.getParameter("r9_BType");//为“2”: 服务器点对点通讯.
		String  hmac = request.getParameter("hmac");
		
		boolean isOK = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
		
		if(!isOK){
			out.print("支付数据有可能被篡改,请联系客服");
		}else{
			
			if("1".equals(r1_Code)){//支付结果，1代表支付成功
				if("2".equals(r9_BType)){
					out.print("success");
				}
				//out.print("hello servlet..");
				//修改定单状态
				OrderService os = new OrderService();
				try {
					os.modifyOrderState(r6_Order);
				} catch (UserException e) {
					System.out.println(e.getMessage());
				}
				//重定向到成功页面
				response.sendRedirect(request.getContextPath()+"/paysuccess.jsp");
			}
			
		}*/
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
