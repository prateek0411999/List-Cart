package icsd;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

import oracle.jdbc.pool.OracleDataSource;

@WebServlet("/Auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	Connection con=null;
	ResultSet rset=null;
	PreparedStatement stmt=null;
	
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		ServletContext context=getServletContext();
		OracleDataSource ods;
		try {
			ods=new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			con=ods.getConnection("k1","icsd");
			context.setAttribute("gcon", con);
			
			stmt=con.prepareStatement("select * from tbluser where unm=? and upwd=?");
		}catch(Exception e)
		{
			System.out.println("Exception occured");
		}
	}
    public Auth() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String strUnm=request.getParameter("txtUnm");
	String strPwd=request.getParameter("txtPwd");
	
	try
	{
		stmt.setString(1, strUnm);
		stmt.setString(2, strPwd);
		
		rset=stmt.executeQuery();
		if(rset.next())
		{
			HttpSession session=request.getSession();
			session.setAttribute("unm", "strUnm");
			response.sendRedirect(request.getContextPath()+"/CategoryPage");
		}
		else
		{
			System.out.println("Entered usename or password is wrong");
		}
	}
	catch(SQLException e)
	{
		System.out.println("EXception occcured");
	}
	}

}
