package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/profileServlet")
public class ProfileServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req,res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		processRequest(req, resp);
	}
    public void processRequest(HttpServletRequest req,HttpServletResponse res) throws IOException
    {
    	
    	res.setContentType("text/html");
      	PrintWriter out=res.getWriter();
      	out.print("<html>");
    	out.print("<head>");
    	out.print("<title>hii</title>");
    	out.print("</head>");
    	out.print("<body bgcolor='pink'>");
    	out.println("<h2>FirstName: omkar</h2>");
    	out.println("<h2>LastName: yadav</h2>");
    	out.println("<h2>Qualification:  Bachelor Of Computer Engineer</h2>");
    	out.println("<h2>DOB: 27/01/2003 </h2>");
    	out.println("<h2>University: Savitribai Phule Pune</h2>");
    	out.print("</body>");
    	 out.print("</html>");
    	
    	
    }
	
}