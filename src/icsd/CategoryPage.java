package icsd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CategoryPage")
public class CategoryPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con=null;
	ResultSet rset=null;
	PreparedStatement stmt=null;
	
	public void init(ServletConfig config)
	{
		try {
			super.init(config);
			ServletContext context=getServletContext();
			con=(Connection) context.getAttribute("gcon");
			stmt=con.prepareStatement("select * from category");
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public CategoryPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			
		ICSDCheckAuth.checkAuthenticity(request);
		//valid user
	      try {
	       String strCid,strCname,strCdecs,strCimg;
	    rset=stmt.executeQuery();
	    out.println("<html><head>");
	    out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">");
	    out.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
	    		"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\r\n" + 
	    		"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>");
	    
	    out.println("</head");
	    out.println("<body>");
	    out.println("<table border=1px>");
	    out.println("<tr>");
	    out.println("<th>CategoryId</th>");
	    out.println("<th>CategoryName</th>");
	    out.println("<th>CategoryDesc</th>");
	    out.println("<th>CategoryImgUrl</th>");
	    out.println("</tr>");
	    while(rset.next())
	    {
	     strCid = rset.getString("categoryid");
	     //System.out.println(strCid);
	     strCname = rset.getString("categoryname");
	     //System.out.println(strCname);
	     strCdecs = rset.getString("catergorydesc");
	     //System.out.println(strCdecs);
	     strCimg = rset.getString("categoryimgurl");
	    
	     
	     out.println("<tr>");
	     out.println("<td>"+strCid+"</td>");
	     out.println("<td><a href=ProductDetails?categoryid="+strCid+">"+strCname+"</a></td>");
	     out.println("<td>"+strCdecs+"</td>");
	     out.println("<td><img src='images/"+strCimg+"'/></td>");
	     System.out.println("!!!!!!!@@@@@@@@!!!!!!!!!!");
	     System.out.println(strCimg);
	     out.println("</tr>");
	     
	    }
	    out.println("</table>");
	    out.println("</body></html>");
	   } catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   }
	      
	 }catch(ICSDAuthenticationException e)
		{
		out.println("<html><head><title>Invalid User</title></head><body>");
		out.println("<a href='Login.html'><b>"+e.getMessage()+"</b></a>");
		out.println("</body></html>");
		}
	}
	

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // TODO Auto-generated method stub
	  doGet(request, response);
	 }
	}
