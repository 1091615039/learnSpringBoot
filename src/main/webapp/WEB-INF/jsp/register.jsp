<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
  <meta charset="utf-8">
  <title>用户注册</title>
  <%@include file="common/head.jsp" %>
  <link rel="stylesheet" href="./css/account.css">
</head>

<body>
<jsp:include page="common/nav.jsp"/>

<form class="form-signin" action="./register" method="post">
  <c:if test = "${message != null }">
  <div class="alert alert-warning">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Warning!</strong> ${message }
  </div>
  </c:if>
  <h2 class="form-signin-heading">用户注册</h2>
  <input type="email" name="email" class="form-control" placeholder="电子邮件" required autofocus>
  <input type="text" name="username" class="form-control" placeholder="用户名" required>
  <input type="password" name="password" class="form-control" placeholder="密码" required>
  <button class="btn btn-primary btn-block" type="submit">确定</button>
</form>

</body>

</html>
