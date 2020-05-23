package icsd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class DBHandler {

	public ClsProduct getProdRowByPid(String strProdid)
	{
		ClsProduct obj=null;
		Connection con=getDBCon();
		String strCatid = null,strProdname = null,strProdDesc = null,strProdimg = null;
		try
		{
			PreparedStatement stmt=con.prepareStatement("select * from products where prodid=?");
			stmt.setString(1, strProdid);
			
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				 strCatid=rset.getString("categoryid");
				 strProdname = rset.getString("productname");
				 strProdDesc = rset.getString("proddesc");
				 strProdimg = rset.getString("prodimgurl");
				
				
				int intQty=rset.getInt("qty");
				double dblPrice=rset.getDouble("price");
				obj=new ClsProduct(strCatid,strProdid,strProdname,strProdDesc,strProdimg,intQty,dblPrice);
				
				String strRes=obj.toString();
				System.out.println(obj);
			}
			System.out.println(strCatid+" "+strProdname+" "+strProdDesc+" "+strProdimg );
			
			con.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return obj;
	}
	
	
	public Connection getDBCon()
	{
		Connection con=null;
		OracleDataSource ods;
		try
		{
			ods=new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			con=ods.getConnection("K1","icsd");
			System.out.println("Connection set DBHandler");
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
