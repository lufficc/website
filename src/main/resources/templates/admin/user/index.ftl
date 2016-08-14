<#import "../layout/app_no_js.ftl" as app>
<@app.app>
<link href="https://almsaeedstudio.com/themes/AdminLTE/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet">


<div class="box">
    <div class="box-header">
        <h3 class="box-title">用户列表</h3>
    </div>
    <div class="box-body">
        <table id="articles" class="table table-responsive table-bordered table-striped">
            <thead>
            <tr>
                <th>用户名</th>
                <th>邮箱</th>
                <th>日期</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.createdAt}</td>
                    <td>
                        <div class="btn-group center-block">
                            <a href="/admin/user/update/${user.id}" role="button"
                               class="btn btn-sm btn-default"><i class="fa fa-edit"></i></a>
                            <button type="button" class="btn btn-sm btn-default" data-toggle="modal"
                                    data-target="#delete-modal" data-id="${user.id}">
                                <i class="fa fa-trash"></i>
                            </button>
                        </div>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>

<div class="modal fade modal-danger" id="delete-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">删除</h4>
            </div>
            <div class="modal-body">
                <p>确定删除吗?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">取消</button>
                <form action="/admin/user" method="post">
                    <input type="hidden" name="_method" value="delete">
                    <input id="input-id" type="hidden" name="id">
                    <button role="button" type="submit" class="btn btn-outline">确定</button>
                </form>

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/admin-lte/2.3.6/js/app.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://almsaeedstudio.com/themes/AdminLTE/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $('#articles').DataTable();
    });
    $('#delete-modal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('id') // Extract info from data-* attributes
        var modal = $(this)
        modal.find('#input-id').val(recipient)
    })
</script>
</@app.app>