package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Members;
import jp.co.aforce.dao.DeleteDAO;

@WebServlet(urlPatterns = { "/servlet/delete" })
public class Delete extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		String view = request.getParameter("view"); 	//jspのボタンを取得
		String url = "../jsp/delete.jsp";
		String memberNo = request.getParameter("memberNo");

		if (view.equals("表示")) { 	//押したボタンが表示ボタンならば
			try {
				request.setCharacterEncoding("UTF-8");
				DeleteDAO dao = new DeleteDAO(); 	//daoをインスタンス化
				Members members = dao.search(memberNo); 	//daoでsearchしてきた会員情報をmembersに代入

				if (members != null) { 		//nullじゃないということはあてはまる情報が見つけられたということ
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
		} else if (view.equals("削除")) {
			DeleteDAO dao = new DeleteDAO(); 	//daoをインスタンス化
			try {
				if (dao.delete(memberNo)) { 	//削除できたら
					System.out.println(dao.delete(memberNo));
					request.setAttribute("msg", "削除に成功しました"); 	//${msg}に第2引数の値をセット
				} else {
					request.setAttribute("msg", "削除に失敗しました");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url)
			.forward(request, response); 	//フォワード
	}
}