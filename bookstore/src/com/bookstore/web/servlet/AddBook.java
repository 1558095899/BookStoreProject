package com.bookstore.web.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.bookstore.service.ProductsService;
import com.bookstore.user.Book;

public class AddBook extends HttpServlet {
//添加图书
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//区分上传是普通表单还是图片
		DiskFileItemFactory df=new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(df);
		//处理上传表单的编码问题
		sfu.setHeaderEncoding("utf-8");
		
		//限制上传文件大小
		try {
			
			HashMap<String, String> map = new HashMap<String,String>();
			sfu.setSizeMax(1024*1024*6);
			
			List<FileItem> fileItem1 = sfu.parseRequest(request);
			for (FileItem fileItem : fileItem1) {
				if(fileItem.isFormField()){
					//普通表单项
					String name = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					map.put(name, value);
				}else{
					if(fileItem.getName()==null || "".equals(fileItem.getName())){
						response.getWriter().write("添加图书失败，图片未上传！！");
						return;
					}else{
						//上传表单项
						String filename = fileItem.getName();
						//处理文件名
						if(filename!=null){
							filename=FilenameUtils.getName(filename);
						}
						String path = this.getServletContext().getRealPath("/load");
						File file=new File(path);
						if(!file.exists()){
							file.mkdir();
						}
						String timefile=CreateTimeFile(file);
						fileItem.getInputStream();
						String imgurl=timefile+file.separator+filename;
						fileItem.write(new File(file,imgurl));
						map.put(fileItem.getFieldName(),imgurl);
						fileItem.delete();
					}
				}
			}
			Book book=new Book();
			ProductsService ps = new ProductsService();
			BeanUtils.populate(book, map);
			book.setId(UUID.randomUUID().toString());
			
			//调用业务逻辑
			ps.addBook(book);
			//分发转向
			request.getRequestDispatcher("findAllServlet").forward(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	//创建当前时间的文件夹
	private String CreateTimeFile(File file) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		File f=new File(file,format);
		if(!f.exists()){
			f.mkdir();
		}
		return format;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
