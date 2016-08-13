<#macro app>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>lufficc</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/admin-lte/2.3.6/css/AdminLTE.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/admin-lte/2.3.6/css/skins/_all-skins.min.css" rel="stylesheet">
    <link href="/css/app.css" rel="stylesheet">
</head>
<body>
    <#include "nav.ftl">
<div class="container-fluid">
    <#include "../../partials/error.ftl"/>
        <#nested>
        <#include "footer.ftl">
</div>
</body>
</html>
</#macro>



