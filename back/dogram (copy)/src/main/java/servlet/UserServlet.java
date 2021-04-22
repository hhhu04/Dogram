package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.GenericXmlApplicationContext;

import service.User;

/**
 * Servlet implementation class UserServlet
 */

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("/parking.jsp");
        rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		User user = ctx.getBean("user",User.class);
		
		String txt = "guest hello";
		int is_register = 0;
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("pw");
		String email = request.getParameter("email");
		
		try {
			user.create(id, password, email);
			txt = "hi";
				
			
		}catch (Exception e) {
			txt = "fail";
            e.printStackTrace();
        }
		
		
		
		 PrintWriter out = response.getWriter();
	        response.setCharacterEncoding("UTF-8");
	        out.print(txt);
	        out.flush();
	}

}
