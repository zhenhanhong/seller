<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
    <!-- BEGIN HEADER INNER -->
    <div class="page-header-inner">
        <!-- BEGIN LOGO -->
        <div class="page-logo">
            <a href="${ctx}/main">
                <img src="${ctx}/assets/admin/layout/img/web_logo_header.png" alt="logo" class="logo-default"/>
            </a>

            <div class="menu-toggler sidebar-toggler hide"></div>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <div class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
        </div>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <div class="col-sm-4 col-md-4" style="text-align: center;color: #FFFFFF;float: right;">
            <h3 class="h3" style="margin-top:10px"><b>xxx&nbsp;&nbsp;<i style="font-size: 18px" title="查看运营商数据" class="glyphicon glyphicon-search" onclick="seeTenantData()"></i></b></h3>

        </div>
        <!-- END TOP NAVIGATION MENU -->
    </div>
    <!-- END HEADER INNER -->
</div>
<!-- END HEADER -->

<div id="tenantSel_list_div" class="modal fade" tabindex="-1" aria-hidden="true" style="display: none">
    <div class="modal-dialog">
        <div class="modal-content" style="width:800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">运营商列表</h4>
            </div>
            <div class="modal-body" style="margin:5px;">
                <div class="row" id="tableDatas">
                    <div class="col-md-14">
                        <div class="portlet box green-haze">
                            <div class="portlet-body">
                                <a class="btn btn-xs green" href="${ctx}/select-tenant?id=-1" style="float:right;margin-bottom:10px;"><i class="glyphicon glyphicon-search"></i>查看全体运营商数据</a>
                                <table class="table table-striped table-bordered table-hover" id="tenantSel_list_table">
                                    <thead>
                                    <tr role="row" class="heading">
                                        <th width="25%">运营商编码</th>
                                        <th width="25%">运营商名称</th>
                                        <th class="sorting_disabled">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/assets/global/plugins/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/jquery-file-upload/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-process.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-validate.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-ui.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/data-tables/DT_bootstrap.js"></script>
<script type="text/javascript"
        src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/common/commonUtil.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/assets/global/scripts/datatable.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script type="text/javascript">
    // 选择运营商用table
    var userGrid = new Datatable();
    var $userList_data_table = $("#tenantSel_list_table");
    userGrid.init({
        src: $userList_data_table,
        onSuccess: function (userGrid) {
            //console.log(userGrid);
        },
        onError: function (userGrid) {
        },
        dataTable: {

            "sDom": "<'table-scrollable't><'row'<'col-md-10 col-sm-12'pli><'col-md-1 col-sm-12'>r>>",//dataTable翻页,只保留表格底部翻页样式
            "aLengthMenu": [
                [5, 10, 25, 50, 100, -1],
                [5, 10, 25, 50, 100, "All"]
            ],
            "aoColumnDefs": [
                {"bSortable": false, "aTargets": [2]}
            ],//设置不排序得列
            "bServerSide": true,
            "sAjaxSource": "${rc.contextPath}/tenant/tenantContract/getTenantList",
            "aoColumns": [
                {"sWidth": "25%", "sTitle": "运营商编码", "mData": "tenantCode"},
                {"sWidth": "25%", "sTitle": "运营商名称", "mData": "name"},
                {
                    "sWidth": "15%",
                    "sTitle": "操作",
                    "mData": "id",
                    "sDefaultContent": "",
                    "mRender": function (data, type, row) {
                        var b = '<a class="btn btn-xs green" href="${ctx}/select-tenant?id=' + row.id + '"  title ="查看运营商数据"><i class="glyphicon glyphicon-search"></i>查看运营商数据</a>';
                        return b;
                    }
                }
            ]
        }
    });
    // 查看运营商
    function seeTenantData() {
        userGrid.getDataTable().fnDraw();
        $('#tenantSel_list_div').modal('show');
    }
    ;
</script>