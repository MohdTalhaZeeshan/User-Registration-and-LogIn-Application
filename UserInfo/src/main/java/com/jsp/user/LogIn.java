package com.jsp.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LogIn extends GenericServlet
{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		     String emailId=request.getParameter("emailId");
		     String  password=request.getParameter("password");
		     
		     String select="select * from user_information where user_email_id=? and user_password=?";
		     
		                   try {
		                	   
		               Class.forName("com.mysql.cj.jdbc.Driver");// to access the static block which has method called register driver which will give object of driver class to server block present inside the driver class
						Connection	connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/teca54?user=root&password=12345");
						     PreparedStatement   ps=  connection.prepareStatement(select);
						     ps.setString(1, emailId);
						     ps.setString(2, password);
						     
						    ResultSet resultSet=ps.executeQuery();
						    PrintWriter writer=response.getWriter();
						    response.setContentType("text/html");
						    if(resultSet.isBeforeFirst())
						    {
						    	writer.println("<center> <h1> Log In Successfull </h1> </center>");
						    }
						    else
						    {
						    	writer.println("<center> <h1> Invalid Details </h1> </center>");
						    }
						}
		                   
		                   catch (SQLException e) {
							
							e.printStackTrace();
						} 
		                   catch (ClassNotFoundException e) {
							
							e.printStackTrace();
						}
		
	}
	
	

}
