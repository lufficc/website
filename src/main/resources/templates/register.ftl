<#import "layout/app_without_nav.ftl" as app>
<@app.app>
<div class="row">
    <div class="col-md-4 col-sm-6 center-block" style="margin-top: 17%">
        <div class="box box-primary box-solid">
            <div class="box-header with-border"><a href="/">首页</a></div>
            <div class="box-body">
                <form class="form-vertical" method="post" action="/register">
                    <div class="input-group">
                        <span class="input-group-addon">用户名</span>
                        <input name="username" type="text" class="form-control" value="${username!""}">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">邮箱</span>
                        <input name="email" type="email" class="form-control" value="${email!""}">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">密    码</span>
                        <input name="password" type="password" class="form-control">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">确认密码</span>
                        <input name="password_confirm" type="password" class="form-control">
                    </div>
                    <br>
                    <input type="submit" class="btn btn-success center-block" value="注册">
                </form>
            </div>
        </div>
    </div>
</div>
</@app.app>