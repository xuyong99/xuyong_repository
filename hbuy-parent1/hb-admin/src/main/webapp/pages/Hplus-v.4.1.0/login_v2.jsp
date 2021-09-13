<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="<%=basePath %>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>好卖网后台登录</title>
    <link href="<%=basePath %>/static/pages/Hplus-v.4.1.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/static/pages/Hplus-v.4.1.0/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath %>/static/pages/Hplus-v.4.1.0/css/animate.css" rel="stylesheet">
    <link href="<%=basePath %>/static/pages/Hplus-v.4.1.0/css/style.css" rel="stylesheet">
    <link href="<%=basePath %>/static/pages/Hplus-v.4.1.0/css/login.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <%--<div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>[ H+ ]</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 <strong>H+ 后台主题UI框架</strong></h4>
                    <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                    </ul>
                    <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
                </div>--%>
            </div>
            <div class="col-sm-5">
                <form method="post" action="<%=basePath %>/login/login">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到好卖网后台系统</p>
                    <input type="text" name="username" class="form-control uname" placeholder="用户名" />
                    <input type="password" name="pwd" class="form-control pword m-b" placeholder="密码" />
                    <a href="">忘记密码了？</a>
                    <button class="btn btn-success btn-block">登录</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2015 All Rights Reserved. 好卖网
            </div>
        </div>
    </div>
</body>

</html>
