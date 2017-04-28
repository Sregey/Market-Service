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

    <title>Dashboard</title>

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
                    <li>
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
                    <li class="active">
                        <a href="adminpanel-resumes.action"><i class="fa fa fa-calendar"></i> Resumes</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <%@include file='resume-edit-modal.jsp'%>

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Resumes
                    </h1>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->

        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel-heading">
                        <a href="/generateResumesXLS.action">Generate XLS</a>
                        &middot;
                        <a href="/generateResumesCSV.action">Generate CSV</a>
                    </div>
                    <!-- Table -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div style="color: red;">
                                <s:property value="errorString"></s:property>
                            </div>
                            <p>Resumes list:</p>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>first name</th>
                                    <th>last name</th>
                                    <th>middle name</th>
                                    <th>skills</th>
                                    <th>description</th>
                                    <th>address</th>
                                    <th>phone number</th>
                                    <th>actions</th>
                                </tr>
                                </thead>
                                <tbody>

                                <s:iterator value="resumeList" var="resume">
                                    <tr>
                                        <td><s:property value="id"></s:property></td>
                                        <td><s:property value="firstname"></s:property></td>
                                        <td><s:property value="lastname"></s:property></td>
                                        <td><s:property value="middlename"></s:property></td>
                                        <td><s:property value="skills"></s:property></td>
                                        <td><s:property value="description"></s:property></td>
                                        <td><s:property value="address"></s:property></td>
                                        <td><s:property value="phone"></s:property></td>
                                        <td>
                                            <button class="btn btn-link btn-sm"
                                                    id="<s:property value="id"/>"
                                                    firstname="<s:property value="firstname"/>"
                                                    lastname="<s:property value="lastname"/>"
                                                    middlename="<s:property value="middlename"/>"
                                                    skills="<s:property value="skills"/>"
                                                    description="<s:property value="description"/>"
                                                    address="<s:property value="address"/>"
                                                    phone="<s:property value="phone"/>"
                                                    customerId="<s:property value="customer.getId()"/>"
                                                    birthdate="<s:property value="birthdate"/>"
                                                    onclick="showEditModal(this)">
                                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                                update
                                            </button>
                                            &middot;
                                            <a href="/generateResumePDF.action?id=<s:property value="id"/>">PDF</a>
                                                <%--&middot;--%>
                                                <%--<a href="/generateAnalysePDF.action?id=<s:property value="id"/>">PDF</a>--%>
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
    function showEditModal(instance)
    {
        $('#resume_edit_id').val($(instance).attr('id'));
        $('#resume_edit_firstname').val($(instance).attr('firstname'));
        $('#resume_edit_lastname').val($(instance).attr('lastname'));
        $('#resume_edit_middlename').val($(instance).attr('middlename'));
        $('#resume_edit_skills').val($(instance).attr('skills'));
        $('#resume_edit_description').val($(instance).attr('description'));
        $('#resume_edit_address').val($(instance).attr('address'));
        $('#resume_edit_phone').val($(instance).attr('phone'));
        $('#resume_edit_customer_id').val($(instance).attr('customerId'));
        $('#resume_edit_birthdate').val($(instance).attr('birthdate'));
        $('.resume_edit_modal').modal();
    }
</script>

</body>

</html>
