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
                        <a href="company-dashboard-vacancys.action"><i class="fa fa-fw fa-bookmark"></i> Vacacnys</a>
                    </li>
                    <li>
                        <a href="company-dashboard-feedbacks.action"><i class="fa fa-fw fa-usd"></i> Feedbacks</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <%@include file='vacancy-add-modal.jsp'%>
    <%@include file='vacancy-edit-modal.jsp'%>
    <%@include file='vacancy-delete-modal.jsp'%>

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Vacancys <small>Only yours</small>
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
                        <button class="btn btn-default btn-sm" onclick="showAddModal()">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            Add new vacancy
                        </button>
                        &middot;
                        <a href="/generateCompanyVacancysXLS.action?companyId=<s:property value="companyId"></s:property>">Generate XLS</a>
                        &middot;
                        <a href="/generateCompanyVacancysCSV.action?companyId=<s:property value="companyId"></s:property>">Generate CSV</a>
                    </div>
                    <!-- Table -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div style="color: red;">
                                <s:property value="errorString"></s:property>
                            </div>
                            <p>Vacancys list:</p>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>title</th>
                                    <th>description</th>
                                    <th>category</th>
                                    <th>skills</th>
                                    <th>email</th>
                                    <th>phone</th>
                                    <th>company</th>
                                    <th>salary</th>
                                    <th>actions</th>
                                </tr>
                                </thead>
                                <tbody>

                                <s:iterator value="vacancyList" var="vacancy">
                                    <tr>
                                        <td><s:property value="id"></s:property></td>
                                        <td><s:property value="title"></s:property></td>
                                        <td><s:property value="description"></s:property></td>
                                        <td><s:property value="category.getName()"></s:property></td>
                                        <td><s:property value="skills"></s:property></td>
                                        <td><s:property value="email"></s:property></td>
                                        <td><s:property value="phone"></s:property></td>
                                        <td><s:property value="company.getCompanyName()"></s:property></td>
                                        <td><s:property value="salary"></s:property></td>
                                        <td>
                                            <button class="btn btn-link btn-sm"
                                                    id="<s:property value="id"/>"
                                                    title="<s:property value="title"/>"
                                                    salary="<s:property value="salary"/>"
                                                    description="<s:property value="description"/>"
                                                    categoryId="<s:property value="category.getId()"/>"
                                                    email="<s:property value="email"/>"
                                                    phone="<s:property value="phone"/>"
                                                    skills="<s:property value="skills"/>"
                                                    onclick="showEditModal(this)">
                                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                                update
                                            </button>
                                            &middot;
                                            <button class="btn btn-link btn-sm" id_instance="<s:property value="id"/>"
                                                    onclick="showDeleteModal(this)">
                                                <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                                delete
                                            </button>
                                            &middot;
                                            <a href="/generateVacancyPDF.action?id=<s:property value="id"/>">PDF</a>
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
    function showAddModal()
    {
        $('.vacancy_add_modal').modal();
    }

    function showEditModal(instance)
    {
        $('#vacancy_edit_id').val($(instance).attr('id'));
        $('#vacancy_edit_title').val($(instance).attr('title'));
        $('#vacancy_edit_description').val($(instance).attr('description'));
        $('#vacancy_edit_email').val($(instance).attr('email'));
        $('#vacancy_edit_phone').val($(instance).attr('phone'));
        $('#vacancy_edit_skills').val($(instance).attr('skills'));
        $('#vacancy_edit_salary').val($(instance).attr('salary'));
        $('#vacancy_edit_categoryId option').removeAttr('selected');
        $('#vacancy_edit_categoryId option[value=' + $(instance).attr('categoryId') + ']').attr('selected', 'selected');
        $('.vacancy_edit_modal').modal();
    }

    function showDeleteModal(instance)
    {
        var id = $(instance).attr('id_instance');
        $('#vacancy_delete_id').val(id);
        $('.vacancy_delete_modal').modal();
    }
</script>

</body>

</html>
