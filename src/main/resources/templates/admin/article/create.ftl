<#import "../layout/app.ftl" as app>
<@app.app>
<div class="row">
    <form class="form-vertical" action="/admin/article" method="post" enctype="multipart/form-data">
        <div class="col-lg-4 col-md-4 col-sm-12">
            <div class="box box-success box-solid">
                <div class="box-header with-border">分类&状态</div>
                <div class="box-body">
                    <div class="input-group">
                        <span class="input-group-addon">选择分类*</span>
                        <select class="form-control" name="category">
                            <#list categories as category>
                                <#if articleForm.category?? && (articleForm.category == category.name)>
                                    <option value="${category.name}" selected>${category.name}</option>
                                <#else>
                                    <option value="${category.name}">${category.name}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">文章状态*</span>
                        <select class="form-control" name="articleStatus">
                            <#if articleForm.articleStatus == "Published">
                                <option value="Published" selected>发布</option>
                                <option value="Draft">草稿</option>
                            <#else>
                                <option value="Published">发布</option>
                                <option value="Draft" selected>草稿</option>
                            </#if>
                        </select>
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">文 件 夹 ^</span>
                        <select class="form-control" name="folder">
                            <option value="-1">无文件夹</option>
                            <#list folders as folder>
                                <#if articleForm.folder?? && articleForm.folder == folder.id>
                                    <option value="${folder.id}" selected>${folder.name}</option>
                                <#else>
                                    <option value="${folder.id}">${folder.name}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-8 col-md-8 col-sm-12">
            <div class="box box-primary box-solid">
                <div class="box-header with-border">文章信息</div>
                <div class="box-body">

                    <div class="input-group">
                        <span class="input-group-addon">文章标题*</span>
                        <input type="text" name="title" class="form-control" id="title"
                               value="${articleForm.title ! ""}"/>
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">文章描述*</span>
                        <input type="text" name="description" class="form-control" id="description"
                               value="${articleForm.description ! ""}"/>
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">作者名称^</span>
                        <input type="text" name="author" class="form-control" id="author"
                               value="${articleForm.author ! ""}">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">原文链接^</span>
                        <input type="text" name="originUrl" class="form-control" id="originUrl"
                               value="${articleForm.originUrl ! ""}"/>
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">MD 文件*</span>
                        <input type="file" name="md_file" class="form-control" id="md_file"/>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <input type="hidden" name="MAX_FILE_SIZE" value="4048000"/>
        <div class="col-lg-offset-5 col-lg-offset-5 col-lg-12 col-md-12 col-sm-12">
            <div class=" input-group">
                <button type="submit" class="btn btn-success">创建</button>
            </div>
        </div>
    </form>
</div>
</@app.app>