package com.sunbeam.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.entities.Candidate;

@WebServlet("/candlist")
public class CandidateServlets extends HttpServlet {

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
		List<Candidate> l=new ArrayList<>();
		
		try(CandidateDao cd=new CandidateDaoImpl())
		{
			l=cd.findAll();
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		    throw new ServletException(e);
		}
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		ServletContext ctx=this.getServletContext();
		 String ann=(String) ctx.getAttribute("announcement");
		 
		out.print("<html>");
		out.print("<head>");
		out.print("<title>");
		out.print("</title>");
		out.print("</head>");
		ServletContext app=this.getServletContext();
		String color=app.getInitParameter("color");
		out.print("<body bgcolor="+color+">");
		String title=app.getInitParameter("app.title");
		out.printf("<h1>%s</h1>",title);
		if(ann!=null)
		{
			out.print("<p>"+ann+"</p>");
		}
		String username="",role="";
		Cookie []arr=req.getCookies();
		for(Cookie c:arr)
		{
			if(c.getName().equals("name"))
			{
				username=c.getValue();
			}
			if(c .getName().equals("role"))
			{
				role=c.getValue();
			}
		}
		out.printf("Hello, %s (%s)<hr/>\n", username, role);
		
		out.print("<form method='post' action='vote'>");
		for(Candidate c:l)
		{
			out.printf("<input type='radio' name='candidate' value='%d'/>%s </br>\n",c.getId(),c.getName());
		}
		out.print("<input type='submit' value='Vote'/>");
		out.print("</form>");
		out.print("</body>");
		out.print("</html>");
	}
	
	

}
