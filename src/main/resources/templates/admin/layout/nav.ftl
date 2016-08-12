<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#blog-navbar" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/admin">后台管理</a>
        </div>
        <div class="collapse navbar-collapse" id="blog-navbar">
            <ul class="nav navbar-nav">
                <li class="${(path == "/admin/article/create")?string("active","")}">
                    <a href="/admin/article/create">新的文章</a>
                </li>
                <li class="${(path == "/admin/category/create")?string("active","")}">
                    <a href="/admin/category/create">新的分类</a>
                </li>
                <li class="${(path == "/admin/folder/create")?string("active","")}">
                    <a href="/admin/folder/create">新的文件夹</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="${(path == "/login")?string("active","")}"><a href="/login">登陆</a></li>
                <li class="${(path == "/register")?string("active","")}"><a href="/register">注册</a></li>
            </ul>
        </div>
    </div>
</nav>