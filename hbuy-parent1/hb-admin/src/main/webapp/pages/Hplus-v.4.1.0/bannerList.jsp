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
    <title>轮播图列表</title>
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
                                            <th data-field="imgUrl">图片</th>
                                            <th data-field="href">图片跳转链接</th>
                                            <th data-field="remark">备注</th>
                                            <th data-field="sort">排序</th>
                                            <th data-field="updateTime">修改时间</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${pageInfo.list}" var="map">
                                            <tr>
                                                <td>
                                                    <input type="checkbox">
                                                </td>
                                                <td>${map.imgUrl}</td>
                                                <td>${map.href}</td>
                                                <td>${map.remark}</td>
                                                <td>${map.sort}</td>
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
                        添加轮播图片
                    </h4>
                </div>
                <div class="modal-body">
                    <form action="javascript:void(0)" class="form-horizontal" role="form">
                        <input type="hidden" name="imgUrl">
                        <div class="form-group">
                            <label for="href" class="col-sm-2 control-label">跳转链接</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="href">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="remark" class="col-sm-2 control-label">备注</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="remark">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sort" class="col-sm-2 control-label">排序</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="sort">
                            </div>
                        </div>
                        <div class="form-group">
                           <label class="sr-only" for="inputfile">文件输入</label>
                            <input type="file" onchange="ajaxUploadFile()" id="inputfile">
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
            //关闭模态框
            $('#myModal').modal('hide');
            //触发ajax，将数据保存到数据库
            $.ajax({
                url:"<%=basePath %>/banner/addBanner",
                type:"POST",
                dataType:"JSON",
                data:{
                    "imgUrl":$("input[name=imgUrl]").val(),
                    "href":$("#href").val(),
                    "remark":$("#remark").val(),
                    "sort":$("#sort").val()
                },
                success:function(rs){
                    if(rs){
                        window.location.reload();
                    }else{
                        //
                        alert("上传失败");
                    }
                }
            });
            //保存成功--->刷新表格页面
            //保存失败--->弹窗提示
        }

        /**
         * ajax图片上传
         */
        function ajaxUploadFile(){
            //创建一个容器，封装图片信息。将容器作为ajax传递给后台的参数
            var form = new FormData();
            //将文件装入到容器中
            var file = $("#inputfile")[0].files[0];
            form.append("wenJian",file);
            //执行ajax
            $.ajax({
                url:"<%=basePath %>/upload/uploadFile",
                type:"POST",
                data:form,
                processData:false,
                contentType:false,
                success:function(rs){
                    var status=rs.status;
                    if(status=='1'){//上传失败
                        //模态框
                        alert("上传失败");
                    }else{//上传成功
                        $("input[name=imgUrl]").val(rs.url);
                        $("#inputfile").after("<a target='_blank' href='"+rs.url+"'><img src='"+rs.url+"' style='width: 100px;height: 100px'></a>");
                    }
                }
            });
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
