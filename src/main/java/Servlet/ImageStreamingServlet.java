package Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image/stream.hw")
public class ImageStreamingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String folderPath = application.getInitParameter("image");
		File folder = new File(folderPath);
		String fileName = request.getParameter("imaGe");
		File file = new File(folder, fileName);
		
		String mime = application.getMimeType(fileName);
		response.setContentType(mime);
		
		try(
				InputStream is = new FileInputStream(file);
				OutputStream os = response.getOutputStream();
			) {
			byte[] buffer = new byte[1024];
			
			int length = -1;
			while((length=is.read(buffer))!= -1) {
				os.write(buffer, 0, length);
			}
		}
		
	}

}
