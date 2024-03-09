package com.jsp.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet("/password")// it is used to configure server and to map url pattern we have to pass the value password to action attribute
public class UpdatePassword extends GenericServlet
{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		String emailId=request.getParameter("emailId");
		String password=request.getParameter("password1");
		String confirmPassword=request.getParameter("password2");
		PrintWriter writer=response.getWriter();
		response.setContentType("text/html");
		if(password.equalsIgnoreCase(confirmPassword))
		{
			String update="update user_information set user_password=? where user_email_id=?";
			
			         try {
	 Class.forName("com.mysql.cj.jdbc.Driver");// to access the static block  which has method called register driver which will give object of driver class to server block present inside the driver class
					 Connection connection=	DriverManager.getConnection("jdbc:mysql://localhost:3306/teca54?user=root&password=12345");
					 PreparedStatement ps= connection.prepareStatement(update);
					 ps.setString(1, password);
					 ps.setString(2, emailId);
					 
					int result=  ps.executeUpdate();
					
					
					if(result!=0)
					{
						//writer.println(" <center> <h1> Updated Successfully </h1> </center>");
						RequestDispatcher rd= request.getRequestDispatcher("LogIn.html");//RequestDispatcher to move to a webpage
						rd.forward(request, response);
					}
					else
					{
						RequestDispatcher rd= request.getRequestDispatcher("UpdatePassword.html");
						rd.include(request, response);
						writer.println(" <center> <h1> Invalid EmailId </h1> </center> ");
						
					}
					} 
			         
			         catch (SQLException e) {
						
						e.printStackTrace();
					}
			         catch (ClassNotFoundException e) {
						
						e.printStackTrace();
					}
		}
		else
		{
			RequestDispatcher rd= request.getRequestDispatcher("UpdatePassword.html");
			rd.include(request, response);
			writer.println(" <center> <h1> Passwords Does not Match </h1> </center>  ");
		}
		
		
	}

}
