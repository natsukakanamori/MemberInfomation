package jp.co.aforce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Members;
import jp.co.aforce.dao.UpdateDAO;
import tool.Page;

@WebServlet(urlPatterns = { "/jp.co.aforce.servlet/update" })
public class Update extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Page.header(out);

		String view = request.getParameter("view"); //jspのボタンを取得
		String url = "../jsp/update.jsp";
		String memberNo = request.getParameter("memberNo");
		String name = request.getParameter("name");
		String getAge = request.getParameter("age");
		String getBirthYear = request.getParameter("birthYear");
		String getBirthMonth = request.getParameter("birthMonth");
		String getBirthDay = request.getParameter("birthDay");

		if (view.equals("表示")) { //押したボタンが表示ボタンならば
			try {
				request.setCharacterEncoding("UTF-8");
				UpdateDAO dao = new UpdateDAO(); //daoをインスタンス化
				Members members = dao.search(memberNo); //daoでsearchしてきた会員情報をmembersに代入

				if (members != null) { //nullじゃないということはあてはまる情報が見つけられたということ
					request.setAttribute("members", members);
					//System.out.println(members.getAge());
				} else {
					request.setAttribute("msg", "該当する会員情報が見つかりません");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (view.equals("戻る")) {
			url = "../jsp/menu.jsp";

		} else if (view.equals("変更")) {
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

				UpdateDAO dao = new UpdateDAO();
				int line;
				try {
					line = dao.update(members);

					if (line > 0) {
						request.setAttribute("msg", "変更に成功しました");
					} else {
						request.setAttribute("msg", "変更に失敗roしました");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				out.println("入力されていない項目があります");
			}
		}
		request.getRequestDispatcher(url)
		.forward(request, response); //フォワード
		Page.footer(out);
	}
}