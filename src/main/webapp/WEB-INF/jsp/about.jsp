<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>博客首页</title>
  <%@include file="common/head.jsp" %>
  <link rel="stylesheet" href="./css/about.css">
</head>
<body>

<jsp:include page="common/nav.jsp">
  <jsp:param name="username" value="${sessionScope.currentUser.username }"/>
</jsp:include>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
  </ol>
  <div class="carousel-inner">
    <div class="item active">
      <img class="first-slide" src="./img/mac.jpg" alt="First slide">

      <div class="container">
        <div class="carousel-caption">
          <h2>极致体验</h2>

          <p>优雅简洁的外观设计，细心的交互方式，帮助您更快捷地创建博客，更方便地浏览您的博客内容。</p>

          <p><a class="btn btn-primary" href="#">立即加入</a></p>
        </div>
      </div>
    </div>
    <div class="item">
      <img class="second-slide" src="./img/cloud.png" alt="Second slide">

      <div class="container">
        <div class="carousel-caption">
          <h2>Markdown 引擎</h2>

          <p>Sample Blog 使用 Markdown 创建易读易写的博客文章。Markdown 由一些经过精挑细选符号所组成，其作用一目了然。</p>

          <p><a class="btn btn-primary" href="#">深入了解</a></p>
        </div>
      </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

<div class="container project-list">
  <div class="row">
    <c:forEach var="project" items="${projects}">
      <div class="col-sm-4">
        <img class="img-circle" src="${project.logo }" width="140" height="140">
  
        <h2>${project.name }</h2>
  
        <p>${project.description }</p>
  
        <p><a class="btn btn-default" href="${project.url }">访问 &raquo;</a></p>
      </div>
    </c:forEach>
  </div>
</div>

</body>
</html>