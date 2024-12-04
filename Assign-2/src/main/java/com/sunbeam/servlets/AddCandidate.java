package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.entities.Candidate;

@WebServlet("/addCandidate")
public class AddCandidate extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
	}
	public void processRequest(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text.html");
	    PrintWriter out=res.getWriter();
	      String name=req.getParameter("name");
	      String party=req.getParameter("party");
	    Candidate c=new Candidate(0,name,party,0);
	    out.print("<html>");
			out.print("<head>");
			out.print("<title>");
			out.print("</title>");
			out.print("</head>");
			out.print("<body>");
	    try(CandidateDao cd=new CandidateDaoImpl())
	    {
	    	int cnt=cd.save(c);
	    	if(cnt==1)
	    	{
	    		out.print("<p>Successfully added</p>");
	    		res.sendRedirect("result");
	    	}
	    	else {
	    		out.print("<p>failed adding</p>");
	    	}
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	throw new ServletException(e);
	    }
	    
		out.print("</body>");
		out.print("<html>");
		
	    
	}
	

}
