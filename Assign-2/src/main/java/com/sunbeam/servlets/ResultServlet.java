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

@WebServlet("/result")
public class ResultServlet extends HttpServlet {

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
		ServletContext ctx=this.getServletContext();
		String ann=(String) ctx.getAttribute("announcement");
		if(ann !=null)
		{
			out.print("<p style=color:red> Note: "+ ann+"</p>" );
		}
		Cookie[] arr = req.getCookies();
				String userName = "", role = "";
				if(arr != null) {
					for (Cookie c : arr) {
						if(c.getName().equals("name"))
							userName = c.getValue();
						if(c.getName().equals("role"))
							role = c.getValue();
					}
				}
				out.printf("Hello, %s (%s)<hr/>\n", userName, role);
		out.print("<h2>Voting Result</h2>");
		out.print("<table border='1'>");
		out.print("<thead>");
		out.print("<tr>");
		out.print("<td>No</td>");
		out.print("<td>Name</td>");
		out.print("<td>Vote</td>");
		out.print("<td>Party</td>");
		out.print("<td>Action</td>");
		out.print("</thead>");
		out.print("<tbody>");
		for(Candidate c:l)
		{
			out.print("<tr>");
			out.printf("<td>%d</td>",c.getId());
			out.printf("<td>%s</td>",c.getName());
			out.printf("<td>%d</td>",c.getVotes());
			out.printf("<td>%s</td>",c.getParty());
			out.printf("<td>\n");
			out.printf("<a href='editcand?id=%d'>Edit</a>\n", c.getId());
			out.printf("<a href='delcand?id=%d'>Delete</a>\n", c.getId());
			out.printf("</td>\n");
			out.print("</tr>");
		}
		out.print("</tbody>");
		String message=(String) req.getAttribute("mes");
		if(message!=null)
		{
			out.print("<p>"+message+"</p>");
		}
		out.print("<a href='Announce.html'>Make Announcement</a><br>");
		out.print("<a href='addCandidate.html'>Add new Candidate</a>");
		out.print("<p><a href='logout'>Sign Out</a>");
		out.print("</body>");
		out.print("</html");
		
	}
	
	

	
}
