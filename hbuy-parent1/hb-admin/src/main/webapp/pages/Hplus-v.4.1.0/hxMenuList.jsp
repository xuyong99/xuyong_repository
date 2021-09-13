<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>横向菜单管理</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="<%=basePath %>/static/pages/Hplus-v.4.1.0/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=basePath %>/static/pages/Hplus-v.4.1.0/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath %>/static/pages/Hplus-v.4.1.0/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%=basePath %>/static/pages/Hplus-v.4.1.0/css/animate.css" rel="stylesheet">
    <link href="<%=basePath %>/static/pages/Hplus-v.4.1.0/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            <%--<h4 class="example-title">事件</h4>--%>
                            <div class="example">
                                <%--<div class="alert alert-success" id="examplebtTableEventsResult" role="alert">
                                    事件结果
                                </div>--%>
                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                    <button type="button" class="btn btn-outline btn-default" data-toggle="modal"
                                            data-target="#myModal">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                    </button>
                                    <button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-heart" aria-hidden="true"></i>
                                    </button>
                                    <button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                                    </button>
                                </div>
                                <table id="exampleTableEvents" data-height="400" data-mobile-responsive="true">
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-field="title">标题</th>
                                            <th data-field="url">跳转链接</th>
                                            <th data-field="menuType">菜单类型</th>
                                            <th data-field="updateTime">最新修改</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${pageInfo.list}" var="map">
                                            <tr>
                                                <td>
                                                    <input type="checkbox">
                                                </td>
                                                <td>${map.title}</td>
                                                <td>${map.url}</td>
                                                <td>${map.menuType=='1'?"横向菜单":"纵向菜单"}</td>
                                                <td>${map.updateTime}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- End Example Events -->
                    </div>
                </div>
            </div>
        </div>
        <!-- End Panel Other -->
    </div>

    <%--添加菜单模态框--%>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加横向菜单
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="firstname" class="col-sm-2 control-label">标题</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="firstname"
                                       placeholder="请输入名字">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-sm-2 control-label">地址</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="lastname"
                                       placeholder="请输入姓">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" onclick="ok()" class="btn btn-primary">
                        添加
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <script type="text/javascript">
        function ok(){
            //触发ajax，将数据保存到数据库
            //关闭模态框
            $('#myModal').modal('hide');
            //保存成功--->刷新表格页面
            //保存失败--->弹窗提示
        }
    </script>

    <!-- 全局js -->
    <script src="<%=basePath %>/static/pages/Hplus-v.4.1.0/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=basePath %>/static/pages/Hplus-v.4.1.0/js/bootstrap.min.js?v=3.3.6"></script>
    <!-- 自定义js -->
    <script src="<%=basePath %>/static/pages/Hplus-v.4.1.0/js/content.js?v=1.0.0"></script>
    <!-- Bootstrap table -->
    <script src="<%=basePath %>/static/pages/Hplus-v.4.1.0/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="<%=basePath %>/static/pages/Hplus-v.4.1.0/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="<%=basePath %>/static/pages/Hplus-v.4.1.0/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <!-- Peity -->
    <script src="<%=basePath %>/static/pages/Hplus-v.4.1.0/js/demo/bootstrap-table-demo.js"></script>
</body>

</html>
