<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sidebar-module sidebar-module-inset">
  <div class="avatar">
    <img class="img-circle img-rounded img-thumbnail avatar" src="./img/catty.jpeg">
    <div>
      <h4>${author.username == null ? "天码营": author.username}</h4>
      <a>${author.email == null ? "admin@tianmaying.com": author.email }</a>
    </div>
  </div>
  <p>${author.description == null ? "天码营秉承让技术学习更加高效和便捷的使命，致力于打造新一代的技术学习服务平台，提供创新并且专业的内容、工具与服务，帮助学习者与从业者实现个人价值。" : author.description }</p>
</div>