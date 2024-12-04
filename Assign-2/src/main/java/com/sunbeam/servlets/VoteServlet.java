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

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.entities.User;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {

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
		
                int cid=Integer.parseInt(req.getParameter("candidate"));
		        out.println("<html>");
				out.println("<head>");
				out.println("<title>Result</title>");
				out.println("</head>");
				ServletContext app=this.getServletContext();
				String color=app.getInitParameter("color");
				out.print("<body bgcolor="+color+">");
				String title=app.getInitParameter("app.title");
				out.printf("<h1>%s</h1>",title);
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
		
				out.println("<h2>Voting Status</h2>");
		       HttpSession s=req.getSession(false);
		       if(s==null)
		       {
		    	   res.sendError(419);
		    	   return;
		       }
		        User u=(User) s.getAttribute("curUser");
		        if(u.getStatus()==0) {
				try(CandidateDao candDao = new CandidateDaoImpl()) {
					int count = candDao.incrementVote(cid);
					if(count == 1) {
						out.println("<h4>You have successfully casted your vote.</h4>");
						try(UserDao ud=new UserDaoImpl())
						{
							int cnt=ud.update(u);
							if(cnt==1)
							{
								out.println("<h4>You are marked as voted.</h4>");
							}
						}
						
					}  
					else
						out.println("<h4>Your voting failed.</h4>");
				}
				
				catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
		        }else {

					out.println("<h4>You are already voted.</h4>");
		        }
				
				
				out.println("<p><a href='logout'>Sign Out</a></p>");
				out.println("</body>");
				out.println("</html>");
	    
		
		
           
          
	}
	

}
