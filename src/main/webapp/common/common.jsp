<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (session.getAttribute("USERNAME") != null) {
%>
<span class="userinfo">Hi, <%=session.getAttribute("USERNAME")%> </span>
<a type="button" href="<%=request.getContextPath()%>/my/baozang.jsp" class="btn btn-link main-baozang">数据宝藏</a>
<a type="button" href="<%=request.getContextPath()%>/my/main.jsp" class="btn btn-link main-myplace">我的地盘</a>
<%
} else {
%>
<a type="button" href="<%=request.getContextPath()%>/my/baozang.jsp" class="btn btn-link main-baozang">数据宝藏</a>
<a type="button" href="<%=request.getContextPath()%>/login.html" class="btn btn-link main-login">登录</a>
<%
    }
%>