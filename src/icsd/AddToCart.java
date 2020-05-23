package icsd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    public AddToCart() {

    }



protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{

try {

		ICSDCheckAuth.checkAuthenticity(request);
	
		HttpSession session=request.getSession(false);
		LinkedList<ClsProduct> lst=(LinkedList<ClsProduct>) session.getAttribute("CART");
			if(lst==null)	
			{
					//ist request
					lst=new LinkedList<>();
				session.setAttribute("CART", lst);
				
				}
				
				
				String strPid=request.getParameter("pid");
				DBHandler objDH=new DBHandler();
				ClsProduct obj=objDH.getProdRowByPid(strPid);
				///////////////////////////////////out.println(obj);
				lst.add(obj);
				
				response.sendRedirect(request.getContextPath()+"/ListCart");
				
				
				
				} 
		catch (ICSDAuthenticationException e1)
			{
				PrintWriter out=response.getWriter();
				out.println("<html><head><title>invalid user</title></head><body>");
				out.println("<a href='LoginPage.html'><b>"+e1.getMessage()+"<b></a>");
				out.println("</body></html>");
			}
		
				}
				
				
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		}				
	}
				
