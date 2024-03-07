<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 전용 페이지</h2>
	<form action="Logout.do">
		<table>
			<tr>
				<td>안녕하세요. ${loginUser.name }(${ loginUser.userid})님</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="로그아웃"> &nbsp;&nbsp;&nbsp;
					<input type="button" value="회원정보수정" onclick="location.href='memberUpdate.do?userid=${loginUser.userid}'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>