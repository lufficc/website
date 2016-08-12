<#import "layout/app.ftl" as app>
<@app.app>
<div class="row">
    <div class="col-lg-3 col-md-3 col-sm-6">
        <div class="info-box bg-blue">
            <a href="/admin/user"><span class="info-box-icon"><i class="fa fa-user"></i></span></a>
            <div class="info-box-content">
                <span class="info-box-text">用户</span>
                <span class="info-box-number">${userCount}</span>
            </div>
        </div>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-6">
        <div class="info-box bg-green">
            <a href="/admin/article"><span class="info-box-icon"><i class="fa fa-sticky-note"></i></span></a>
            <div class="info-box-content">
                <span class="info-box-text">文章</span>
                <span class="info-box-number">${articleCount}</span>
            </div>
        </div>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-6">
        <div class="info-box bg-red">
            <a href="/admin/category"><span class="info-box-icon"><i class="fa fa-tags"></i></span></a>
            <div class="info-box-content">
                <span class="info-box-text">分类</span>
                <span class="info-box-number">${categoryCount}</span>
            </div>
        </div>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-6">
        <div class="info-box bg-orange">
            <a href="/admin/folder"><span class="info-box-icon"><i class="fa fa-folder"></i></span></a>
            <div class="info-box-content">
                <span class="info-box-text">文件夹</span>
                <span class="info-box-number">${folderCount}</span>
            </div>
        </div>
    </div>
</div>
</@app.app>