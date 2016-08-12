<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>lcc_luffy's blog</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/admin-lte/2.3.6/css/AdminLTE.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/admin-lte/2.3.6/css/skins/_all-skins.min.css" rel="stylesheet">
</head>
<body>
<#include "../layout/nav.ftl">
<div class="container">
<#include "../partials/error.ftl"/>
    <div>
        <div class="page-header">
            <h1 class="text-center">${title}</h1>
        </div>
        <div id="md"></div>
    </div>
<#include "../layout/footer.ftl">
    <script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/marked/0.3.6/marked.min.js"></script>
    <script>
        document.getElementById('md').innerHTML =
                marked("${content?js_string}");

    </script>
</body>
</html>
