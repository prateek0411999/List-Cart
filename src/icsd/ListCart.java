package icsd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ListCart
 */
@WebServlet("/ListCart")
public class ListCart extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCart() {
        super();
        // TODO Auto-generated constructor stub
    }

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

PrintWriter out=response.getWriter();
try {
ICSDCheckAuth.checkAuthenticity(request);
HttpSession session=request.getSession(false);
LinkedList<ClsProduct> lst=(LinkedList<ClsProduct>) session.getAttribute("CART");


out.println("<html><head>");
out.println("<title>Category page</title>");
out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">");
out.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>" + 
"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>" + 
"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>");
//
//create table products
//(
//  categoryid varchAr(20),
//  prodid varchar(10) primary key,
//  productname varchar(20),
//  proddesc varchar(50),
//  prodimgurl varchar(20),
//  qty number(20),
//  PRICE NUMBER(15,2),
//  constraint abc foreign key (categoryid) references category(categoryid)
//)
out.println("</head><body>");

out.println("<table class='table table-bordered table-hover ' >");
out.println("<tr class='bg-danger'>");
out.println("<th>category id</th>");
out.println("<th>prod id</th>");
out.println("<th>product name</th>");
out.println("<th>prod desc</th>");
out.println("<th>prod img url</th>");
out.println("<th>qty</th>");
out.println("<th>price</th>");

out.println("</tr>");
double dblSum=0;
for(ClsProduct p :lst)
{
String strProdid,strProdname,strProdDesc,strprodImg,strQty,strPrice;
strProdid=p.getStrProdid();
strProdname=p.getStrProdname();
strProdDesc=p.getStrProdDesc();
strprodImg=p.getStrProdimg();
strQty=String.valueOf(p.getIntQty());
strPrice =String.valueOf(p.getDblPrice());


out.println("<tr>");
out.println("<td>"+"123333"+"</td>");
out.println("<td>"+strProdid+"</td>");
out.println("<td>"+strProdname+"</td>");
out.println("<td>"+strProdDesc+"</td>");
out.println("<td><img src='images/"+strprodImg+"'/></td>");
System.out.println(strprodImg);
out.println("<td>"+strQty+"</td>");
out.println("<td>"+strPrice+"</td>");

out.println("</tr>");
dblSum=dblSum+p.getDblPrice();

}
out.println("<tr class='bg-info'>");
out.println("<td colspan=3>Total Bill is </td>");
out.println("<td colspan=4>"+dblSum+" </td>");
out.println("</tr>");
out.println("</table>");
out.println("<a href='CategoryPage'><b>purchase more ....?<b></a>");
out.println("</body></html>");



} catch (ICSDAuthenticationException e1) 
{
//invalid user
//e1.printStackTrace();
out.println("<html><head><title>invalid user</title></head><body>");
out.println("<a href='LoginPage.html'><b>"+e1.getMessage()+"<b></a>");
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