package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.entities.User;

@WebServlet("/signin")
public class RegisterServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	     processRequest(req, resp);
	     
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	public void processRequest(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String name=req.getParameter("fname");
		String lastName=req.getParameter("lname");
		String email=req.getParameter("email");
		String password=req.getParameter("passwd");
	      String date=req.getParameter("d");
	      Date dob=Date.valueOf(date);
	      out.println("<html>");
		    out.println("<head>");
			out.println("<title>Logout</title>");
			out.println("</head>");
		    out.println("<body>");
		try(UserDao ud=new UserDaoImpl())
		{
			User u =new User(0,name,lastName,email,password,dob,0,"voter");
			int cnt=ud.save(u);
			if(cnt==1)
			{
				out.print("<p>Successfully added</p>");
			}else {
				out.print("<p>failed to Register</p>");
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
		out.print("<a href='index.html'>login here</a>");
		out.print("</body>");
		out.println("</html>");
	  
		
		
	}

}
