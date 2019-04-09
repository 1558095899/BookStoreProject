package com.bookservlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import UUIDUtils.UUIDUtil;

import com.book.Book;
import com.bookservice.BookService;

public class AddBookServlet extends HttpServlet {
	//添加图书
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*request.setCharacterEncoding("utf-8");
		//获取表单信息
		Book book=new Book();
		BookService bs=new BookService();
		try {
			BeanUtils.populate(book, request.getParameterMap());
			//由于提交的表单没有id  需要手动创建一个id 并放入Book对象中
			book.setId(UUIDUtil.getUUID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//调用理业务逻辑
		bs.addBooks(book);
		//分发转向
		request.getRequestDispatcher("lookServlet").forward(request, response);
		*/
//		获取上传图片
		DiskFileItemFactory df=new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(df);
		
		sfu.setHeaderEncoding("utf-8");
		try {
			Map<String,String> map=new HashMap<String, String>();
			List<FileItem> fileItems=sfu.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				if(fileItem.isFormField()){
					//普通表单项
					String name = fileItem.getFieldName();
					String string = fileItem.getString("utf-8");
					map.put(name, string);
				}else{
					//文件表单项
					String filename = fileItem.getName();
					if(filename!=null){
						filename = FilenameUtils.getName(filename);
					}
					String realPath = this.getServletContext().getRealPath("/load");
					File file = new File(realPath);
					if(!file.exists()){
						file.mkdir();
					}
					//创建以日期为文件夹
					String dateDirectory=DateDiretoru(file);
					fileItem.getInputStream();
					String img_url=dateDirectory+file.separator+filename;
					fileItem.write(new File(file,img_url));
					map.put(fileItem.getFieldName(),img_url);
					fileItem.delete();
				}
			}
			
			Book book = new Book();
			BeanUtils.populate(book, map);
			book.setId(UUIDUtil.getUUID());
			BookService bs=new BookService();
			bs.addBooks(book);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	private String DateDiretoru(File file) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateDirectory=df.format(new Date());
		File file2 = new File(file,dateDirectory);
		if(!file2.exists()){
			file2.mkdir();
		}
		return dateDirectory;
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
