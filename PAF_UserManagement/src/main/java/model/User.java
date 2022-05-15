package model;

import java.sql.*;

public class User {

	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userm", "root", "admin");
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }

	 return con;
	}
	
	//Read function
	public String readPay()
	{
	 String output = "";
	 
	 try
	 {
	  Connection con = connect();
	  if (con == null)
	  {
		  return "Error while connecting to the database for reading.";
	  }
	 
	// Prepare the HTML table to be displayed
	 output = "<table border='1' class='table table-striped'><tr>"
	 + "<th>User ID</th>"
	 + "<th>User Name</th>"
	 + "<th>Birth Day</th>"
	 + "<th>Email</th>"
	 + "<th>Contact Number</th>"
	 + "<th>Update</th><th>Delete</th></tr>";
	 
	 String query = "select * from userx";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // Iterate through the rows in the result set
	 while (rs.next())
	 {
		 String noticeId = Integer.toString(rs.getInt("noticeId"));
		 String userID = rs.getString("userID");
		 String username = rs.getString("username");
		 String birthday = rs.getString("birthday");
		 String email = rs.getString("email");
		 String cnumber = rs.getString("cnumber");
	 
		 // Add a row into the HTML table
//		 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + noticeId
//				 + "'>" + phone + "</td>"; 
		 output += "<tr><td><input id=\'hidItemIDUpdate\' name=\'hidItemIDUpdate\' type=\'hidden\' value=\'"
					+ noticeId + "'>" + userID + "</td>";
		 output += "<td>" + username + "</td>";
		 output += "<td>" + birthday + "</td>";
		 output += "<td>" + email + "</td>";
		 output += "<td>" + cnumber + "</td>";
		
		 
		 // Buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-success' data-noticeid='" + noticeId +"'></td>"
		 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-noticeid='" + noticeId + "'></td></tr>";
	 }
	 con.close();
	 
	 // Complete the HTML table
	 output += "</table>";
	}
	 
	catch (Exception e)
	{
		output = "Error while reading the payments.";
		System.err.println(e.getMessage());
	}
	 
	return output;
	}
	
	//Insert function
	public String insertPay(String userID, String username, String birthday, String email, String cnumber)
	{
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 
	 // Prepared statement
	 String query = " insert into userx(`noticeId`,`userID`,`username`,`birthday`, `email`, `cnumber`)" + " values (?, ?, ?, ?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // Binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, userID);
	 preparedStmt.setString(3, username);
	 preparedStmt.setString(4, birthday); 
	 preparedStmt.setString(5, email);
	 preparedStmt.setString(6, cnumber);
	 
	 //Execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 String newPay = readPay();
	 output = "{\"status\":\"success\", \"data\": \"" + 
			 newPay + "\"}"; 
	 }
	
	catch (Exception e)
	{
	 //output = "Error while inserting the payment";
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment.\"}"; 
	 System.err.println(e.getMessage());
	 }
	return output; 
	}
	
	//Update function
	public String updatePay(String noticeId, String userID, String username, String birthday, String email, String cnumber) 
	{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // Create a prepared statement
			 String query = "update userx set userID=?, username=?, birthday=?, email=?, cnumber=? where noticeId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // Binding values
			 preparedStmt.setString(1, userID);
			 preparedStmt.setString(2, username);
			 preparedStmt.setString(3, birthday);
			 preparedStmt.setString(4, email);
			 preparedStmt.setString(5, cnumber);
			 preparedStmt.setInt(6, Integer.parseInt(noticeId));
			 
			 // Execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 String newPay = readPay();
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newPay + "\"}"; 
     		 //output = "Updated successfully";
			 }
			 
			 catch (Exception e)
			 {
			 //output = "Error while updating the payment.";
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the payment.\"}";
			 System.err.println(e.getMessage());
			 }
			 return output;
	}
	
	//Delete function
	public String deletePay(String noticeId)
	{
	 String output = "";
	 try
	  {
	  Connection con = connect();
	  if (con == null)
	  {
	  return "Error while connecting to the database for deleting.";
	  }
	  
	  // Create a prepared statement
	  String query = "delete from userx where noticeId=?";
	  PreparedStatement preparedStmt = con.prepareStatement(query);
	  
	  // Binding values
	  preparedStmt.setInt(1, Integer.parseInt(noticeId));

	  // Execute the statement
	  preparedStmt.execute();
	  con.close();
	  
	  String newPay = readPay();
	  output = "{\"status\":\"success\", \"data\": \"" + newPay + "\"}"; 
	  //output = "Deleted successfully";
	  }
	 catch (Exception e)
	  {
	  //output = "Error while deleting the payment.";
	  output = "{\"status\":\"error\", \"data\": \"Error while deleting the payment.\"}";
	  System.err.println(e.getMessage());
	  }
	 return output; 
	}
}
