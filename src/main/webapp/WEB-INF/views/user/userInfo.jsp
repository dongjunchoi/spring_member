<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserVo vo = (UserVo)request.getAttribute("userInfo");
	if(vo!=null){
%>
	{
		"cnt" : "1"
	}
<%		
	}else{
%>
	{
		"cnt" : "0"
	}
<%		
	}
%>