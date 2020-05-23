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


@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	Connection con=null;
	ResultSet rset=null;
	PreparedStatement stmt=null;
	
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		ServletContext context=getServletContext();
		con=(Connection) context.getAttribute("gcon");
		try
		{
			stmt=con.prepareStatement("select * from products where categoryid=?");
		}catch(SQLException e)
		{
			System.out.println("Exception occured");
		}
		
		
	}
    public ProductDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String strCatid=request.getParameter("categoryid");
		System.out.println(strCatid);
		try {
		ICSDCheckAuth.checkAuthenticity(request);
		//check for valid user
		try {
			stmt.setString(1, strCatid);
			rset=stmt.executeQuery();
			
			out.println("<html><head>");
			out.println("<title>Products</title>");
			
			out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">");
			out.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n" + 
					"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\r\n" + 
					"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>");
			out.println("</head><body>");
			out.println("<table border=1px>");
		    out.println("<tr>");
		    out.println("<th>Categoryid</th>");
		    out.println("<th>Productid</th>");
		    out.println("<th>ProductName</th>");
		    out.println("<th>ProductDesc</th>");
		    out.println("<th>ProductImgUrl</th>");
		    out.println("<th>qty</th>");
		    out.println("<th>Price</th>");
		    out.println("<th>Add to Cart</th>");
		    out.println("</tr>");
		    
//		    create table products
//		    (
//		      categoryid varchar(20),
//		      prodid varchar(10) primary key,
//		      productname varchar(20),
//		      proddesc varchar(50),
//		      prodimgurl varchar(20),
//		      qty number(20),
//		      PRICE NUMBER(15,2)
//		    )
//
		    
		    while(rset.next())
		    {
		    	String strProdid,strProdname,strProdDesc,strProdimg,strPrice,strQty;
		    	strProdid=rset.getString("prodid");
		    	System.out.println(strProdid);
		    	strProdname=rset.getString("productname");
		    	System.out.println(strProdname);
		    	strProdDesc=rset.getString("proddesc");
		    	System.out.println(strProdDesc);
		    	strProdimg=rset.getString("prodimgurl");
		    	System.out.println(strProdimg);
		    	strPrice=rset.getString("price");
		    	strQty=rset.getString("qty");
		    	
		    	out.println("<tr>");
			     out.println("<td>"+strCatid+"</td>");
			     out.println("<td>"+strProdid+"</a></td>");
			     out.println("<td>"+strProdname+"</td>");
			   out.println("<td>"+strProdDesc+"</td>");
			   out.println("<td><img src='images/"+strProdimg+"'/></td>");
			     out.println("<td>"+ strQty+"</td>");
			     out.println("<td>"+ strPrice+"</td>");
			     
			     out.println("<td><a href='AddToCart?pid="+strProdid+"'class='btn btn-warning '>Add to Cart</a></td>");
			     out.println("</tr>");
			    
		    	
		    }
		   
		    out.println("</table></body></html>");
		}catch(SQLException e)
		{
			System.out.println("Expception !!!!!!!!!!!!!!");
		}
	}catch(ICSDAuthenticationException e)
		{
		out.println("<html><head><title>Invalid User</title></head><body>");
		out.println("<a href='Login.html'><b>"+e.getMessage()+"</b></a>");
		out.println("</body></html>");
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
