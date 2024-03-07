<%@page import="java.sql.Connection"%>
<%@page import="com.saeyan.dto.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%
	MemberDAO memDao = MemberDAO.getInstance();

	Connection con = memDao.getConnection();
	
	out.println("DB연결 성공 : " + con);
%>