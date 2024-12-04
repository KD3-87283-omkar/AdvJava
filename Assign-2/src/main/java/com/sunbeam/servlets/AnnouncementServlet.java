package com.sunbeam.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/announce")
public class AnnouncementServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 String ann=req.getParameter("announcement");
	 ServletContext ctx=this.getServletContext();
	 ctx.setAttribute("announcement", ann);
       resp.sendRedirect("result");		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  doGet(req, resp);
	}
	

}
