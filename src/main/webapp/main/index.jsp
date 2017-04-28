<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Market service</title>

        <!-- CSS -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,600">        
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/animate.css">
		<link rel="stylesheet" href="assets/css/form-elements.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/media-queries.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>
    
        <!-- Loader -->
    	<div class="loader">
    		<div class="loader-img"></div>
    	</div>

		<!-- Top menu -->
		<nav class="navbar navbar-inverse navbar-fixed-top navbar-no-bg" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="top-navbar-1">
					<ul class="nav navbar-nav">
						<%--<li><a class="scroll-link" href="#top-content">Top</a></li>--%>
						<%--<li><a class="scroll-link" href="#services">Services</a></li>--%>
						<%--<li><a class="scroll-link" href="#testimonials">Clients</a></li>--%>
						<%--<li><a class="scroll-link" href="#footer">Contact</a></li>--%>
						<c:set var="salary" scope="session" value='<%=session.getAttribute("role")%>'/>
						<c:if test="${salary eq 'Customer'}">
							<li><a href="customer-dashboard.action"><%=session.getAttribute("login")%>'s Dashboard</a></li>
							<li><a href="logout.action">Logout</a></li>
						</c:if>
						<c:if test="${salary eq 'Company'}">
							<li><a href="company-dashboard.action"><%=session.getAttribute("login")%>'s Dashboard</a></li>
							<li><a href="logout.action">Logout</a></li>
						</c:if>
						<c:if test="${salary eq 'Admin'}">
							<li><a href="adminpanel.action"><%=session.getAttribute("login")%>'s Dashboard</a></li>
							<li><a href="logout.action">Logout</a></li>
						</c:if>
						<c:choose>
							<c:when test="${salary eq null}">
								<li><a href="registration.action">Sign Up</a></li>
								<li><a href="authorisation.action">Sign In</a></li>
							</c:when>
						</c:choose>
                    </ul>
				</div>
			</div>
		</nav>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                	
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text right">
                            <h1 class="wow fadeInLeftBig">Try our market service</h1>
                            <div class="top-big-link wow fadeInUp">
                            	<a class="btn btn-link-2" href="registration.action">Sign up</a>
                            	<a class="btn btn-link-2 scroll-link" href="#services">Learn more</a>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
            
        </div>
        
        <!-- Services -->
        <div class="services-container section-container">
	        <div class="container">
	            <div class="row">
                	<div class="col-sm-12 services-box wow fadeInUp">
                		<div class="row">
                			<div class="col-sm-4">
			                	<div class="services-box-icon">
			                		<i class="fa fa-magic"></i>
			                	</div>
		                	</div>
	                		<div class="col-sm-8">
	                    		<h3>Web-Service</h3>
	                    		<p>The audience of online stores constantly extends.
									It is connected with fixed increase in Internet users,
									and also with increase in number of online stores
									that gives the chance to purchase on the Internet practically any goods or service.</p>
	                    	</div>
	                    </div>
                    </div>
					</div>
				<div class="row">
                    <div class="col-sm-12 services-box wow fadeInDown">
	                	<div class="row">
                			<div class="col-sm-4">
			                	<div class="services-box-icon">
			                		<i class="fa fa-cog"></i>
			                	</div>
		                	</div>
	                		<div class="col-sm-8">
	                    		<h3>Audience</h3>
	                    		<p>The audience of online stores constantly extends also
									thanks to benefits which shop online the most convenient.</p>
	                    	</div>
	                    </div>
                    </div>
					</div>
				<div class="row">
                    <div class="col-sm-12 services-box wow fadeInUp">
	                	<div class="row">
                			<div class="col-sm-4">
			                	<div class="services-box-icon">
			                		<i class="fa fa-twitter"></i>
			                	</div>
		                	</div>
	                		<div class="col-sm-8">
	                    		<h3>Social media</h3>
	                    		<p>Dear clients and partners since recent time we appeared
									on social networks. This step is proved by desire of the
									Company to become more and more transparent and to
									conduct online dialogue with all who needs our professional help.</p>
	                    	</div>
	                    </div>
                    </div>
					</div>
	            </div>
	        </div>
        </div>

        <!-- Footer -->
        <footer class="footer-container">
	        <div class="container">
	        	<div class="row">

                    Developed by Gelda, Ivanchik and Pashkovski.
                    
                </div>
	        </div>
	        
	        <div class="container-fluid">
	        	<div class="row">
                	<div class="col-sm-12 footer-bottom">
                		<a class="scroll-link" href="#top-content"><i class="fa fa-chevron-up"></i></a>
                	</div>
                </div>
	        </div>
        </footer>


        <!-- Javascript -->
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.backstretch.min.js"></script>
        <script src="assets/js/wow.min.js"></script>
        <script src="assets/js/retina-1.1.0.min.js"></script>
        <script src="assets/js/waypoints.min.js"></script>
        <script src="assets/js/scripts.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>