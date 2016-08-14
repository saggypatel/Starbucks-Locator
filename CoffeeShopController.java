package homework3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

@WebServlet("/homework3/CoffeeShopController")
public class CoffeeShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	public void init() throws ServletException {

	}
	
	public double GreatCircle(double lat1, double lat2, double lon1, double lon2) { 
	     
	        double x1 = Math.toRadians(lat1);
	        double y1 = Math.toRadians(lon1);
	        double x2 = Math.toRadians(lat2);
	        double y2 = Math.toRadians(lon2);
	        double angle1 = Math.acos(Math.sin(x1) * Math.sin(x2)
	                      + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
	        angle1 = Math.toDegrees(angle1);
	        double distance1 = 69.1105 * angle1;
	        
	        return distance1;
	   }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		/*if(latitude.toString().length() == 0  || longitude.toString().length() == 0 || radius == 0){
			response.sendRedirect("CoffeeShopView.jsp");
		}
		*/
		// Construct the URL	
			
		String username = "cs320stu18";
		String password = "D.!qqisB";
		String host 	    = "cs3.calstatela.edu";
		String port   	= "3306";
		String dbName 	= "cs320stu18";

		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
		int rd;
		try{
			
			// Dynamically include the MySQL Driver
			System.out.println("Class.forName");
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();

			// Instantiate the Driver
			Driver driver = new org.gjt.mm.mysql.Driver();

			// Connect to the database
			System.out.println("Creating Connection");
			Connection connection = 
					DriverManager.getConnection(url, username, password);
			
			// Create Statement
			System.out.println("Creating Statement");			
			Statement statement = connection.createStatement();
			String latt = request.getParameter("latitude");
			String longg = request.getParameter("longitude");
			String radius = request.getParameter("radius");
			
			if(latt != null && longg != null && radius.trim().length() != 0){
				double latitude = Double.parseDouble(latt);
				double longitude = Double.parseDouble(longg);
				int r = Integer.parseInt(radius);
				request.setAttribute("rad",r);
				
			// Create SQL query
			String query = "SELECT * FROM CoffeeShop";
			//String query = "SELECT * FROM `CoffeeShop` WHERE `Latitude`=34.04916";
			
			System.out.println("Executing Query: " + query);
			ResultSet resultSet = statement.executeQuery(query);
			
			ArrayList<CoffeeShopBean> users = new ArrayList<CoffeeShopBean>();
			//users.add(new CoffeeShopBean(1223,"saggy","6265867766","1529 prospect","san gabriel",91776,"34.125654","-118.25896"));
			System.out.println("Iterating Over Results");
			while(resultSet.next() ){

				Double lat = Double.parseDouble(resultSet.getString("Latitude"));
			    Double lon = Double.parseDouble(resultSet.getString("Longitude"));
			    String name = resultSet.getString("Name");
			    String phone = resultSet.getString("Phone");
			    String street = resultSet.getString("Street");
			    String city = resultSet.getString("City");
			    int zip = resultSet.getInt("Zipcode");
			    
			    double dist = GreatCircle(latitude, lat, longitude, lon);
			   float l =  (float)dist;
			    if(dist < r){
			    	users.add(new CoffeeShopBean(l,resultSet.getInt("Storeno")
			    			,resultSet.getString("Name")
			    			,resultSet.getString("Phone")
			    			,resultSet.getString("Street")
			    			,resultSet.getString("City")
			    			,resultSet.getInt("Zipcode")
			    			,resultSet.getString("Latitude")
			    			,resultSet.getString("Longitude")));
			    	
			    	//System.out.print(street+"####"+lat+"<--->"+lon+">>>>"+dist+"\n");
			    }
			   
			}
			// Tidy Up
			connection.close();
			request.setAttribute("CoffeeShopBean", users);
			}
			System.out.println("Dispatching");
			request.getRequestDispatcher("../WEB-INF/homework3/CoffeeShopView.jsp").forward(request,response);
			
		}catch (SQLException sqle){
			System.out.println(sqle.getMessage());
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			
		}finally{			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
}
