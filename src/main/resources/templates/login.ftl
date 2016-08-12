<#import "layout/app_without_nav.ftl" as app>
<@app.app>
<div class="row">
    <div class="col-md-4 col-sm-6 center-block" style="margin-top: 17%">
        <div class="box box-primary box-solid">
            <div class="box-header with-border"><a href="/">首页</a></div>
            <div class="box-body">
                <form class="form-vertical" method="post" action="/login">
                    <div class="input-group">
                        <span class="input-group-addon">用户名</span>
                        <input name="username" type="text" class="form-control">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">密    码</span>
                        <input name="password" type="password" class="form-control">
                    </div>
                    <br>
                    <input name="remember-me" id="remember-me" type="hidden" value="false">
                    <#--<label for="remember-me">记住密码</label>-->
                    <input type="submit" class="btn btn-success center-block" value="登录">
                </form>
            </div>
        </div>
    </div>
</div>
</@app.app>