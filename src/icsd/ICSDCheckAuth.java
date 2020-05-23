package icsd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ICSDCheckAuth {
	
	public static HttpSession checkAuthenticity(HttpServletRequest request) throws ICSDAuthenticationException
	{
		HttpSession session=request.getSession(false);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(session);
		if(session==null)
		{
			//invalid user
			throw new ICSDAuthenticationException("Please enter the valid credentials and login again");
		}
		
		String strUnm=(String) session.getAttribute("unm");
		if(strUnm==null)
		{
			//invalid user
			throw new ICSDAuthenticationException("Please enter the valid credentials and login again");
		}
	
		//valid user
		return session;
	}
	
}
