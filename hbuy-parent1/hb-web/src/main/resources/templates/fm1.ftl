<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>freemaker语法学习1</title>
    <#--
         assign命令：定义全局变量
    -->
    <#assign baseUrl="http://localhost:8083/web" name="admin" gender="男" />
</head>
<body>
    <#--
        freemaker通过${key属性名}取值
    -->
    <h1>${uName}</h1>
    <h1>${baseUrl}</h1>

    <#--通过freemaker遍历list集合-->
    <#list imgUrlList as temp>
        <h3>${temp_index}---${temp}</h3>
    </#list>

    <#--遍历map集合-->
    <#list aMap?keys as  k>
        <h3>${k}:${aMap[k]}</h3>
    </#list>

    <#--freemaker中常用的函数-->
    <h3>${date?string("yyyy-MM-dd HH:mm:ss")}</h3>
    <h3>${price?string("0.00")}</h3>
    <h3>${num?default("库存为0")}</h3>
</body>
</html>