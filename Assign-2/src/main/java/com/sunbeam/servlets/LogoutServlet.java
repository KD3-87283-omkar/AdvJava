package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	public void processRequest(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		     res.setContentType("text/html");
            PrintWriter out=res.getWriter();
		
            
                 Cookie c1=new Cookie("name","");
                 c1.setMaxAge(-1);
                 res.addCookie(c1);
                 Cookie c2=new Cookie("role", "");
                 c1.setMaxAge(-1);
                 
                 
                 res.addCookie(c2);
                 HttpSession session = req.getSession();
         		 session.invalidate();
                 
                       out.println("<html>");
					    out.println("<head>");
						out.println("<title>Logout</title>");
						out.println("</head>");
						ServletContext app=this.getServletContext();
						String color=app.getInitParameter("color");
						out.print("<body bgcolor="+color+">");
						String title=app.getInitParameter("app.title");
						out.printf("<h1>%s</h1>",title);
						out.println("<h2>Thank you</h2>");
						out.println("<p>See you after 5 years.</p>");
						out.println("<p><a href='index.html'>Login Again</a></p>");
						out.println("</body>");
						out.println("</html>");	
	}
	

}
