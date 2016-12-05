<%--
  Created by IntelliJ IDEA.
  User: Json
  Date: 2016/10/11
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>期货交易平台</title>
    <link rel = "stylesheet" type = "text/css" href = "../../resources/css/futures/header.css" />
    <link rel = "stylesheet" type = "text/css" href = "../../resources/css/futures/k-line.css" />
    <script src = "http://d3js.org/d3.v3.min.js" charset = "utf-8"></script>
    <script src = "js/jquery.js"      type = "text/javascript"></script>
    <script src = "js/futures/k-line.js" type = "text/javascript"></script>
</head>
<body>
<!--导航栏-->
<div id = "header">
    <img id = "logo" src = "a" alt = "logo"/>
    <ul>
        <li>首页 </li>
        <li>交易中心</li>
        <li>行情图表</li>
        <li>登陆</li>
        <li>注册</li>
    </ul>
</div>
<!--k线图-->
<div id = "k-line" >
    <div id = "k-line-top">
        <label id = "time-label">周期</label>
        <ul id = "time">
            <li>分时</li>
            <li>1分钟</li>
            <li>3分钟</li>
            <li>5分钟</li>
            <li>15分钟</li>
            <li>30分钟</li>
            <li>1小时</li>
            <li>日线</li>
        </ul>
    </div>
    <div id = "k-line-body">
        <div id = "k-line-basic"></div>
        <hr />
    </div>
</div>
<div id = "footer">

</div>
</body>
</html>