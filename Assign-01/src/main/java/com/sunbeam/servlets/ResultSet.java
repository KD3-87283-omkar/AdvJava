package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.Entity.Marks;

@WebServlet("/result")
public class ResultSet extends HttpServlet {
 
	ArrayList<Marks> result;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	 super.init(config);
	 result = new ArrayList<>();
	 result.add(new Marks("Java programming", 80.0));
	 result.add(new Marks("Web programming", 85.0));
	 result.add(new Marks("Database technologies", 83.0));
	 // ...
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		processRequest(req, resp);
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.print("<html>");
		out.print("<head>");
		out.print("<title>hii</title>");
		out.print("</head>");
		out.print("<body bgcolor='pink'>");
		out.print("<table>");
		out.print("<thead>");		
		out.print("<tr>");
		out.print("<td>Subject</td>");
		out.print("<td>Marks</td>");
		out.print("</tr>");
		out.print("</thead>");
		out.print("<tbody>");
		for(Marks m:result)
		{
			out.print("<tr>");
			out.print("<td>"+m.getSubject()+"</td>");
			out.print("<td>"+m.getMark()+"</td>");
			out.print("</tr>");
			
		}
		out.print("</tbody>");
		out.print("</table>");
		out.print("</body>");
		out.print("</html>");
	}

}
