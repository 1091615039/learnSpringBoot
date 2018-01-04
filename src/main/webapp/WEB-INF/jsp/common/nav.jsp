<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="./blogs">${param.username == null ? "天码营":param.username}的博客</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li><a href="./blogs">首页</a></li>
          <li><a href="./about">关于</a></li>
          <c:if test="${sessionScope.currentUser == null }">
              <li><a href="./login">登陆</a></li>
              <li><a href="./register">注册</a></li>
          </c:if>
          <c:if test="${sessionScope.currentUser != null }">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">账号管理
              <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li class="dropdown-header">管理</li>
              <li><a href="list.html">博客信息</a></li>
              <li><a href="./create">创建博文</a></li>
              <li><a href="#">博客管理</a></li>
              <li class="divider"></li>
              <li class="dropdown-header">账号</li>
              <li><a href="#">更改密码</a></li>
              <li><a href="./logout">退出登录</a></li>
            </ul>
          </li>
        </c:if>
      </ul>
      <form class="navbar-form navbar-right">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="关键字">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
      </form>
    </div>
  </div>
</nav>