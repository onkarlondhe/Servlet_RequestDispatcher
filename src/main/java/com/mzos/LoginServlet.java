package com.mzos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDispatcherServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://@localhost:3306/mzos","root","Onkar@4721");
			System.out.println("got connection...");
			 
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("select * from details");
			
			String username = request.getParameter("user");
			String password = request.getParameter("pass");
			
			PrintWriter out = response.getWriter();
			
			while(rs.next()) {
				String user = rs.getString(1);
				String pass = rs.getString(2);
				
				if(username.equalsIgnoreCase(user) && password.equals(pass)) {
					out.println("Welcome "+username);
					
					RequestDispatcher rd = request.getRequestDispatcher("InboxServlet");
					rd.include(request, response);
					break;
					
				}else {
					out.print("Sorry wrong credential");
				}
			
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
}
