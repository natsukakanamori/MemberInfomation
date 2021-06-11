package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.Members;

public class UpdateDAO extends DAO{
	public Members search(String memberNo)throws Exception{ //調べて取り出す情報は1つだけだからList化する必要はない
		Members members = new Members(); //beanをインスタンス化

		Connection con = getConnection(); //データベースにつなぐ

		//PeparedStatementクラスのsetStringメソッドで、ブレースホルダを検索文字列に置き換え
		PreparedStatement st=con.prepareStatement(
			"select * from members where member_no like ?");
		st.setString(1, memberNo); //プレースホルダは１つなので、第一引数は1。入力値（memberNo）をセット
		ResultSet rs=st.executeQuery();
		//resultSetはselect実行後に各行各列のデータを取得するためのインターフェース
		//executeQueryはselect命令を実行するためのメソッド

		if(rs.next()){ //ResultSetでカーソルが現在の行を指し示すよう維持し、nextメソッドにより次の行へ移動
			members.setMemberNo(rs.getString("member_no"));
			members.setName(rs.getString("name"));
			members.setAge(rs.getInt("age"));
			members.setBirthYear(rs.getInt("birth_year"));
			members.setBirthMonth(rs.getInt("birth_month"));
			members.setBirthDay(rs.getInt("birth_day"));
			//beanに取得してきた値を返す
		}else {
			return null;
			//取得できない場合はnullを返す
		}
		st.close();
		con.close(); //データベース切断

		return members;
	}

	public int update(Members members) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"Update INTO members VALUES(?,?,?,?,?,?)");
		st.setString(1, members.getMemberNo());
		st.setString(2, members.getName());
		st.setInt(3, members.getAge());
		st.setInt(4, members.getBirthYear());
		st.setInt(5, members.getBirthMonth());
		st.setInt(6, members.getBirthDay());
		int line = st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
}
