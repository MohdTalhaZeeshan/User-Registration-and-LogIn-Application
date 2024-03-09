package com.jsp.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class UserRegistration extends GenericServlet// to make Application MultiThreaded.
{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
	 String fname	=request.getParameter("fname");// getParameter is used to get the request Object or inputs from input boxes
	 String lname=request.getParameter("lname");
	 String emailId= request.getParameter("emailId");
	 String password=request.getParameter("password");
	 String  mobileNumber  =request.getParameter("mobileNumber"); 
	 String address=request.getParameter("address");
	
	
	 
	 String insert="insert into user_information(user_first_name, user_second_name, user_email_id, user_password, user_mobile_number, user_address) values(?,?,?,?,?,?)";
	          try {
	        	  
 Class.forName("com.mysql.cj.jdbc.Driver"); // to access the static block which has method called register driver which will give object of driver class to server block present inside the driver class
			   Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/teca54?user=root&password=12345");
			       PreparedStatement  ps =connection.prepareStatement(insert);
			       ps.setString(1, fname);
			       ps.setString(2, lname);
			       ps.setString(3, emailId);
			       ps.setString(4, password);
			       ps.setString(5, mobileNumber);
			       ps.setString(6, address);
			       
			      int result=ps.executeUpdate();
			      PrintWriter writer=response.getWriter();
			      response.setContentType("text/html");
			       if(result!=0)
			       {
			    	   writer.println(" <center> <h1>Registration successfull</h1> </center>");
			       }
			       else
			       {
			    	   writer.println("Registration Failed");
			       }
			        
			                             
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	          catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
	}
	

}
