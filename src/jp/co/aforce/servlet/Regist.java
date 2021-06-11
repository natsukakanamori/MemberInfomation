package jp.co.aforce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Members;
import jp.co.aforce.dao.MembersDAO;
import tool.Page;

@WebServlet(urlPatterns = { "/jp.co.aforce.servlet/regist" })
public class Regist extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Page.header(out);

		try {
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			String getAge = request.getParameter("age");
			String getBirthYear = request.getParameter("birthYear");
			String getBirthMonth = request.getParameter("birthMonth");
			String getBirthDay = request.getParameter("birthDay");

			if ((name.length() != 0)
					&& getAge != ""
					&& getBirthYear != ""
					&& getBirthMonth != ""
					&& getBirthDay != "") {

				int age = Integer.parseInt(getAge);
				int birthYear = Integer.parseInt(getBirthYear);
				int birthMonth = Integer.parseInt(getBirthMonth);
				int birthDay = Integer.parseInt(getBirthDay);

				Members members = new Members();
				members.setName(name);
				members.setAge(age);
				members.setBirthYear(birthYear);
				members.setBirthMonth(birthMonth);
				members.setBirthDay(birthDay);

				MembersDAO dao = new MembersDAO();
				int line = dao.insert(members);

				if (line > 0) {
					out.println("登録に成功しました。");
				} else {
					out.println("登録に失敗しました。");
				}
			} else {
				out.println("入力されていない項目があります");
			}

		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}