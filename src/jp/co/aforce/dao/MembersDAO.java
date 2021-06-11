package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import jp.co.aforce.bean.Members;

public class MembersDAO extends DAO {
	public int insert(Members members) throws Exception {
		Connection con = getConnection();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
        String str = sdf.format(timestamp);

		String memberNo="A"+str;

		PreparedStatement st = con.prepareStatement(
				"INSERT INTO members VALUES(?,?,?,?,?,?)");
		st.setString(1, memberNo);
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
