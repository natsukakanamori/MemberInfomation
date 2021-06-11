package jp.co.aforce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/jp.co.aforce.servlet/menu"})
public class Menu extends HttpServlet{

	public void doPost(
			HttpServletRequest request,HttpServletResponse response
		)throws ServletException,IOException{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();

			request.setCharacterEncoding("UTF-8");
			String regist=request.getParameter("regist");
			String update=request.getParameter("update");
			String delete=request.getParameter("delete");

			if(regist=="regist") {
			request.getRequestDispatcher("../jsp/regist.jsp")
				.forward(request, response);
			}
			if(update=="update") {
			request.getRequestDispatcher("../jsp/update.jsp")
				.forward(request, response);
			}
			if(delete=="delete") {
			request.getRequestDispatcher("../jsp/delete.jsp")
				.forward(request, response);
			}
	}
}
