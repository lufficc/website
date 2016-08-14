<#import "../layout/app.ftl" as app>
<@app.app>
<div class="container">
    <div class="box box-primary box-solid">
        <div class="box-header">创建一个新的文件夹</div>
        <div class="box-body">
            <form class="form-vertical" action="/admin/folder" method="post">
                <div class="input-group">
                    <span class="input-group-addon">名字*</span>
                    <input type="text" name="name" class="form-control" id="name" value="${folderForm.name ! ""}">
                </div>
                <br>
                <div class="input-group">
                    <span class="input-group-addon">描述*</span>
                    <input type="text" name="description" class="form-control" id="description"
                           value="${folderForm.description ! ""}"/>
                </div>
                <br>
                <div class="input-group">
                    <span class="input-group-addon">分类*</span>
                    <select class="form-control" name="category">
                        <#list categories as category>
                            <#if folderForm.category?? && (folderForm.category == category.name)>
                                <option value="${category.name}" selected>${category.name}</option>
                            <#else>
                                <option value="${category.name}">${category.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
                <br>
                <div class="input-group">
                    <button type="submit" class="btn btn-success">创建</button>
                </div>
            </form>
        </div>

    </div>
</div>
</@app.app>