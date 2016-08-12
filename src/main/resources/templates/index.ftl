<#import "layout/app.ftl" as app>
<@app.app>
<div class="row">
    <div class="col-lg-6 col-md-6 col-sm-8 center-block">
        <!-- Widget: user widget style 1 -->
        <div class="box box-widget widget-user">
            <!-- Add the bg color to the header using any of the bg-* classes -->
            <div class="widget-user-header bg-black"
                 style="background: url('https://almsaeedstudio.com/themes/AdminLTE/dist/img/photo4.jpg') center center;">
                <h3 class="widget-user-username">lufficc</h3>
                <h5 class="widget-user-desc">Android Developer</h5>
            </div>
            <div class="widget-user-image">
                <img class="img-circle" src="/img/avatar.png" alt="User Avatar">
            </div>
            <div class="box-footer">
                <div class="row">
                    <div class="col-sm-4 border-right">
                        <div class="description-block">
                            <h5 class="description-header">3,200</h5>
                            <span class="description-text">SALES</span>
                        </div>
                    </div>
                    <div class="col-sm-4 border-right">
                        <div class="description-block">
                            <h5 class="description-header">13,000</h5>
                            <span class="description-text">FOLLOWERS</span>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="description-block">
                            <h5 class="description-header">35</h5>
                            <span class="description-text">PRODUCTS</span>
                        </div>
                    </div>
                </div>
                <ul class="nav nav-stacked">
                    <li class="divider"></li>
                    <li><a href="#">Projects <span class="pull-right badge bg-blue">31</span></a></li>
                    <li><a href="#">Tasks <span class="pull-right badge bg-aqua">5</span></a></li>
                    <li><a href="#">Completed Projects <span class="pull-right badge bg-green">12</span></a></li>
                    <li><a href="#">Followers <span class="pull-right badge bg-red">842</span></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
</@app.app>