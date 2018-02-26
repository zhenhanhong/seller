<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh-cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh-cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh-cn" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>迅充网 | <sitemesh:title/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${ctx}/assets/global/css/openSans.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${ctx}/assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/global/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet"/>
    <!-- BEGIN:File Upload Plugin CSS files-->
    <link href="${ctx}/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css" rel="stylesheet"/>
    <!-- END:File Upload Plugin CSS files-->
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <sitemesh:head/>
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="${ctx}/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
    <link id="style_color" href="${ctx}/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}/assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
    <!-- END THEME STYLES -->
    <script>var ctx="${ctx}";</script>
    <style>

    </style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed " id="grad1">
<%@ include file="/WEB-INF/layouts/header.jsp" %>
<div class="clearfix"></div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <%@ include file="/WEB-INF/layouts/sidebar.jsp" %>
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper" >
        <div class="page-content">
            <sitemesh:body/>
        </div>
    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@ include file="/WEB-INF/layouts/footer.jsp" %>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${ctx}/assets/global/plugins/respond.min.js"></script>
<script src="${ctx}/assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="${ctx}/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN: Page level plugins -->
<script src="${ctx}/assets/global/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js" type="text/javascript"></script>
<!-- The main application script -->
<!-- The XDomainRequest Transport is included for cross-domain file deletion for IE 8 and IE 9 -->
<!--[if (gte IE 8)&(lt IE 10)]>
<script src="${ctx}/assets/global/plugins/jquery-file-upload/js/cors/jquery.xdr-transport.js"></script>
<![endif]-->
<!-- END:File Upload Plugin JS files-->
<!-- END: Page level plugins -->
<script src="${ctx}/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${ctx}/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function(){
        // initiate layout and plugins
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
    });
    window.onload=function(){
        if($('.page-sidebar-menu .open a')[0]!=undefined){
            var home=$('.page-sidebar-menu .open a')[0].innerText;
            $('.page-breadcrumb li a')[0].innerText=home;
        }
        if($('.page-sidebar-menu .open ul .active')[0]!=undefined){
            var current=$('.page-sidebar-menu .open ul .active')[0].innerText;
            var currentUrl=$('.page-sidebar-menu .open ul .active a')[0].href;
            $('.page-breadcrumb li a')[1].innerText=clearBr(current);
            $('.page-breadcrumb li a')[1].href=currentUrl;
        }
        var body=$('body');
        if(body.hasClass("page-sidebar-closed")){
            $("#userShow").hide();
            $("#username").hide();
            $("#logout").show();
        }else{
            $("#userShow").show();
            $("#username").show();
            $("#logout").hide();
        }
        setRfxchReadonly();
    }

    function setRfxchReadonly(){
        if ($('.page-sidebar-menu .open ul .active > input')!= undefined){
            if ($('.page-sidebar-menu .open ul .active > input').val() =='false') {
                $('.rfxch-readonly').attr('disabled',true);
            }
        }
    }

    function clearBr(key){
        key = key.replace(/<\/?.+?>/g,"");
        key = key.replace(/[\r\n]/g, "");
        return key;
    }
</script>
<sitemesh:getProperty property="page.script"/>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>