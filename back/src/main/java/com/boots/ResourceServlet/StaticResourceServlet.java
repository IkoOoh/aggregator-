package com.boots.ResourceServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet({"/res/*" , "/dropMenu"})
public class StaticResourceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getRequestURI().equals(request.getContextPath()+"/dropMenu")) {
    		request.getRequestDispatcher("/WEB-INF/views/DropForm.jsp").forward(request, response);
    		return;
    	}
    	
    	
    		String filePath =  request.getPathInfo();
            InputStream fileStream = getClass().getClassLoader().getResourceAsStream(filePath);

            if (fileStream != null) {
                response.setContentType(getServletContext().getMimeType(filePath));
                response.setHeader("Content-Disposition", "attachment; filename=\"" + new File(filePath).getName() + "\"");

                try (OutputStream os = response.getOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileStream.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                } finally {
                    fileStream.close();
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            
            	}
    	
    }
}
