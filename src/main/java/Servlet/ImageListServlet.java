package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image/formUI.hw")
public class ImageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext application;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		String pattern = "<option>%s</option>";
		
		String folderPath = application.getInitParameter("image");
		File folder = new File(folderPath);
		
		StringBuffer option = new StringBuffer();
		String[] filenames = folder.list();
		for(String name : filenames) {
			option.append(String.format(pattern, name));
		}
		
		try(
				PrintWriter out = response.getWriter();
		){
			out.print(option);
		}
	}

}
