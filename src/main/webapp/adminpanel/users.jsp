<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Adminpanel</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">Adminpanel</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    <c:set var="salary" scope="session" value='<%=session.getAttribute("login")%>'/>
                    <c:out value="${salary}"/>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="../.."><i class="fa fa-gear fa-home"></i> Go to mainpage</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="../logout.action"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="active">
                        <a href="adminpanel-users.action"><i class="fa fa-fw fa-users"></i> Users</a>
                    </li>
                    <li>
                        <a href="adminpanel-add-admin.action"><i class="fa fa-fw fa-plus"></i> Add admin</a>
                    </li>
                    <li>
                        <a href="adminpanel-categories.action"><i class="fa fa-fw fa-bank"></i> Categories</a>
                    </li>
                    <li>
                        <a href="adminpanel-vacancys.action"><i class="fa fa-fw fa-bookmark"></i> Vacancys</a>
                    </li>
                    <li>
                        <a href="adminpanel-resumes.action"><i class="fa fa fa-calendar"></i> Resumes</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <%@include file='users-delete-modal.jsp'%>

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Users
                    </h1>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->

        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <a href="/generateUsersXLS.action">Generate XLS</a>
                            &middot;
                            <a href="/generateUsersCSV.action">Generate CSV</a>
                            <div style="color: red;">
                                <s:property value="errorString"></s:property>
                            </div>
                            <p>Users list:</p>
                            </br>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>login</th>
                                    <th>role</th>
                                    <th>actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="usersList" var="user">
                                    <tr>
                                        <td><s:property value="id"></s:property></td>
                                        <td><s:property value="login"></s:property></td>
                                        <td><s:property value="role.getName()"></s:property></td>
                                        <td>
                                            <s:if test="%{login == 'admin'}">
                                                no actions
                                            </s:if>
                                            <s:else>
                                                <button class="btn btn-link btn-sm" id_instance="<s:property value="id"/>"
                                                        onclick="showDeleteModal(this)">
                                                    <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                                    delete
                                                </button>
                                                &middot;
                                                <a href="generateUserPDF.action?id=<s:property value="id"/>">PDF</a>
                                            </s:else>
                                        </td>
                                    </tr>
                                </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="vendor/metisMenu/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="vendor/raphael/raphael.min.js"></script>
<script src="vendor/morrisjs/morris.min.js"></script>
<script src="data/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="dist/js/sb-admin-2.js"></script>

<script>
    function showDeleteModal(instance)
    {
        var id = $(instance).attr('id_instance');
        $('#users_delete_id').val(id);
        $('.users_delete_modal').modal();
    }
</script>

</body>

</html>
