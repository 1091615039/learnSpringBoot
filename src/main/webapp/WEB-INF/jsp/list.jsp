<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>文章列表</title>
  <%@include file="common/head.jsp" %>
  <link rel="stylesheet" href="./css/footer.css">
  <link rel="stylesheet" href="./css/sidbar.css">
  <link rel="stylesheet" href="./css/blog.css">
</head>

<body>
<jsp:include page="common/nav.jsp"/>

<div class="container">

  <div class="page-header">
    <h1>Lorem 的博客
      <small>Mi nunc congue nunc, ante felis vestibulum bibendum.</small>
    </h1>
  </div>

  <div class="row">

    <div class="col-sm-8">
    <c:forEach var="blog" items="${blogs}">
      <div class="blog-post">
        <h3 class="blog-post-title"><a href="./blog?id=${blog.id }">${blog.title}</a></h3>
        <p class="blog-post-meta"><fmt:formatDate value="${blog.createdTime }" pattern="yyyy-MM-dd"/> 标签：<a href="#">Web开发</a></p>
        <p class="blog-post-content">${blog.content}</p>
      </div>
      <hr>
	</c:forEach>
      <nav>
        <ul class="pager">
          <li class="previous"><a href="#"><span aria-hidden="true">&larr;</span> 上一页</a></li>
          <li class="next"><a href="#">下一页 <span aria-hidden="true">&rarr;</span></a></li>
        </ul>
      </nav>

    </div>

    <div class="col-sm-3 col-sm-offset-1">
      <c:set var="author" value="${sessionScope.currentUser}" scope="request"/>
      <jsp:include page="common/userInfo.jsp"/>
      <%@include file="common/archivedList.jsp" %>
    </div>

  </div>

</div>

<%@include file="common/footer.jsp" %>

</body>
</html>