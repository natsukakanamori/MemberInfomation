<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.html"%>
<link rel="stylesheet"  href="../member.css">

<p>メニュー画面</p>
<form action="" method="post">
	<p><input type="submit" value="会員情報新規登録" formaction="regist.jsp"></p>
	<p><input type="submit" value="会員情報変更" formaction="update.jsp"></p>
	<p><input type="submit" value="会員情報削除" formaction="delete.jsp"></p>
</form>



<%@include file="../footer.html"%>