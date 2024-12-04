package com.sunbeam.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;

@WebServlet("/delcand")
public class DeleteCandidate extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
	}
	public void processRequest(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String candId = req.getParameter("id");
		int id = Integer.parseInt(candId);
		// delete cand from database
		try(CandidateDao candDao = new CandidateDaoImpl()) {
			int count = candDao.deleteById(id);
			String mess = "Candidates Deleted: " + count;
			req.setAttribute("msg", mess);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		RequestDispatcher rd=req.getRequestDispatcher("result");
		rd.forward(req, res);
		
	}
	
	

}