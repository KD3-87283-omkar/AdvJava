package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.entities.User;
@WebServlet("/login")
public class LoginSevlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    processRequest(req, resp);
	}
	
	public void processRequest(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException 
	{
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		String email=req.getParameter("uname");
		String password=req.getParameter("passwd");
		
		try(UserDao ud=new UserDaoImpl())
		{
			User u= ud.findByEmail(email);
			Cookie c1=new Cookie("name", u.getFirstName());
			c1.setMaxAge(3600);
			res.addCookie(c1);
			Cookie c2=new Cookie("role", u.getRole());
			c2.setMaxAge(3600);
			res.addCookie(c2);
			HttpSession s=req.getSession();
			s.setAttribute("curUser", u);
			if(u!=null && u.getPassword().equals(password))
			{
				if(u.getRole().equals("admin"))
				{
					res.sendRedirect("result");
					//go to resultServlet
				}else  {
					//go to candidateList
					res.sendRedirect("candlist");
					
				}
			}else {
				out.print("<html>");
				out.print("<head>");
				out.print("<title>");
				out.print("</title>");
				out.print("</head>");
				out.print("<body>");
				out.print("<h2>Login Failed Email or Password ids wrong</h2>");
				out.print("<p><a href='index.html'>Login again</a> </p>");
				out.print("</body>");
				out.print("</html>");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

}
