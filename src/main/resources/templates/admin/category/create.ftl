<#import "../layout/app.ftl" as app>
<@app.app>
<div class="box box-primary box-solid">
    <div class="box-header">创建一个新的分类</div>
    <div class="box-body">
        <form class="form-vertical" action="/admin/category" method="post">
            <div class="input-group">
                <span class="input-group-addon">名字*</span>
                <input type="text" name="name" class="form-control" id="name" value="${categoryForm.name ! ""}">
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon">描述*</span>
                <input type="text" name="description" class="form-control" id="description"
                       value="${categoryForm.description ! ""}"/>
            </div>
            <br>
            <div class="input-group">
                <button type="submit" class="btn btn-success">创建</button>
            </div>
        </form>
    </div>

</div>

</@app.app>