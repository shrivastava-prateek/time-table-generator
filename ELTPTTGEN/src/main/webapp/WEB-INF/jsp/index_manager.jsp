<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="com.bean.User"%>
<%@page import="com.bean.Batch"%>
<%@page import="com.dao.BatchDao"%>
<%@page import="com.bean.Trainer"%>
<%@page import="com.dao.TrainerDao"%>
<%@page import="com.bean.Session"%>
<%@page import="com.dao.SessionDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
<meta name="author" content="Coderthemes">

<link rel="shortcut icon" href="resources/images/favicon_1.ico">

<title>Home</title>

<!-- Base Css Files -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<!--form validation-->
<script type="text/javascript"
	src="resources/assets/jquery.validate/jquery.validate.min.js"></script>

<!--form validation init-->
<script src="resources/assets/jquery.validate/form-validation-init.js"></script>

<!-- Font Icons -->
<link href="resources/assets/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="resources/assets/ionicon/css/ionicons.min.css"
	rel="stylesheet" />
<link href="resources/css/material-design-iconic-font.min.css"
	rel="stylesheet">

<!-- animate css -->
<link href="resources/css/animate.css" rel="stylesheet" />

<!-- Waves-effect -->
<link href="resources/css/waves-effect.css" rel="stylesheet">

<!-- sweet alerts -->
<link href="resources/assets/sweet-alert/sweet-alert.min.css"
	rel="stylesheet">

<!-- Custom Files -->
<link href="resources/css/helper.css" rel="stylesheet" type="text/css" />
<link href="resources/css/style.css" rel="stylesheet" type="text/css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

<script src="resources/js/modernizr.min.js"></script>
<!-- Base Css Files -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<!-- sweet alerts -->
<link href="resources/assets/sweet-alert/sweet-alert.min.css"
	rel="stylesheet">
<!-- Font Icons -->
<link href="resources/assets/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="resources/assets/ionicon/css/ionicons.min.css"
	rel="stylesheet" />
<link href="resources/css/material-design-iconic-font.min.css"
	rel="stylesheet">

<!-- animate css -->
<link href="resources/css/animate.css" rel="stylesheet" />

<!-- Waves-effect -->
<link href="resources/css/waves-effect.css" rel="stylesheet">

<!-- Custom Files -->
<link href="resources/css/helper.css" rel="stylesheet" type="text/css" />
<link href="resources/css/style.css" rel="stylesheet" type="text/css" />

<!-- Plugins css-->
<link href="resources/assets/tagsinput/jquery.tagsinput.css"
	rel="stylesheet" />
<link href="resources/assets/toggles/toggles.css" rel="stylesheet" />
<link href="resources/assets/timepicker/bootstrap-timepicker.min.css"
	rel="stylesheet" />
<link href="resources/assets/timepicker/bootstrap-datepicker.min.css"
	rel="stylesheet" />
<link href="resources/assets/colorpicker/colorpicker.css"
	rel="stylesheet" type="text/css" />
<link href="resources/assets/jquery-multi-select/multi-select.css"
	rel="stylesheet" type="text/css" />
<link href="resources/assets/select2/select2.css" rel="stylesheet"
	type="text/css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->





<%!String type;%>
<%
	Object obj=session.getAttribute("result");
System.out.println(obj);
	if(obj==null){
		%>
<script>
		function func1(type){
			console.log("in else");
			alert("Please log in again");
			window.location="logout";
	
		}
</script>
<%
	}
	%>
<% if(obj!=null) {
		User u = (User)obj; 
		type= u.getUser_type().toString();
	%>
<script>
		function func1(type){
			var post = document.getElementById('new_user');
			var post1 = document.getElementById('delete_user');
			if (type == "ADMIN")
				{
				post.style.visibility = 'visible';
			    post1.style.visibility = 'visible';
				}
			else
				{
				post.style.visibility = 'hidden';
				post1.style.visibility = 'hidden';
				}
		}
		</script>
<%
	}
	%>
</head>



<body class="fixed-left" onload="func1('<%=type%>')">

	<%!int numofTrainers;
	int numofBatches;
	int activeSessions;%>
	<%
	TrainerDao tdao=(TrainerDao)request.getAttribute("tdao");
		List<Trainer> trainers = tdao.GetInternalTrainers();
		numofTrainers = trainers.size();
    BatchDao bdao =(BatchDao)request.getAttribute("bdao");
		List<Batch> batches = bdao.getAllBatch();
		numofBatches = batches.size();
    SessionDao sdao=(SessionDao)request.getAttribute("sdao");
		List<Session> sessions =sdao.getSessionsByDate(new Date());
		activeSessions = sessions.size();
	%>

	<!-- Begin page -->
	<div id="wrapper">

		<!-- Top Bar Start -->
		<div class="topbar">
			<!-- LOGO -->
			<div class="topbar-left">
				<div class="text-center">
					<a href="#" class="logo"><img size=1px><span></span><span>TT
							Generator </span></a>
				</div>
			</div>
			<!-- Button mobile view to collapse sidebar menu -->
			<div class="navbar navbar-default" role="navigation">
				<div class="container">
					<div class="">
						<div class="pull-left">
							<button class="button-menu-mobile open-left">
								<i class="fa fa-bars"></i>
							</button>
							<span class="clearfix"></span>
						</div>


						<ul class="nav navbar-nav navbar-right pull-right">
							<li></li>


							<li class="dropdown"><a href="logout"
								class="dropdown-toggle profile" data-toggle="dropdown"
								aria-expanded="true"><img
									src="resources/images/Persistent_logo.png" alt="user-img"
									class="img-thumbnail"> </a>
								<ul class="dropdown-menu">


									<li><a href="logout"><i class="md md-settings-power"></i>
											Logout</a></li>
								</ul></li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>
		<!-- Top Bar End -->


		<!-- ========== Left Sidebar Start ========== -->

		<div class="left side-menu">
			<div class="sidebar-inner slimscrollleft">

				<!--- Divider -->
				<div id="sidebar-menu">
					<ul>
						<li>
						<li><a href="#" class="waves-effect active"><i
								class="md md-home"></i><span> Dashboard </span></a></li>

						<li><a href="add_batch" class="waves-effect"><i
								class="md md-playlist-add"></i><span> Add New Batch </span></a></li>
						<li><a href="del_batch" class="waves-effect"><i
								class="md md-playlist-add"></i><span> Delete Batch </span></a></li>

					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-- Left Sidebar End -->



		<!-- ============================================================== -->
		<!-- Start right Content here -->
		<!-- ============================================================== -->
		<div class="content-page">
			<!-- Start content -->
			<div class="content">
				<div class="container">

					<!-- Page-Title -->
					<div class="row">
						<div class="col-sm-12">
							<h4 class="pull-left page-title">
								Welcome!, ${result.user_type} <p style="padding-left: 7px; color: red; display: inline; font-size: 15px"> ${message}</p>
							</h4>
							<div class="pull-right">
								<button id="new_user"
									class="btn btn-primary waves-effect waves-light btn-lg m-b-5"
									data-toggle="modal" data-target="#myModal">Create User</button>


								<form action="del_user" method="post" style="display: inline">
									<button
										class="btn btn-primary waves-effect waves-light btn-lg m-b-5"
										id="delete_user">Delete User</button>
								</form>


							</div>
						</div>
					</div>
					<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">Back</button>

								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-sm-12">
											<div class="panel panel-default">

												<div class="panel-body">


													<form:form modelAttribute="user"
														class="cmxform form-horizontal tasi-form" id="signupForm"
														method="post" action="create_user_1"
														novalidate="novalidate">
														<div class="form-group ">
															<label for="firstname" class="control-label col-lg-3">User
																Name</label>
															<div class="col-lg-8">
																<form:input path="username" class="form-control"
																	id="firstname" type="text" placeholder="username"
																	required="" />
																<form:errors path="username" cssStyle="color: #ff0000;" />
															</div>
														</div>
														<div class="form-group ">
															<label for="lastname" class="control-label col-lg-3">Password</label>
															<div class="col-lg-8">
																<form:input path="password" class="form-control"
																	id="lastname" type="password" placeholder="password"
																	required="" />
																<form:errors path="password" cssStyle="color: #ff0000;" />
															</div>
														</div>
														<div class="form-group ">
															<label for="name" class="control-label col-lg-3">Full
																Name</label>
															<div class="col-lg-8">
																<form:input path="name" class="form-control" id="name"
																	type="text" placeholder="name" required="" />
																<form:errors path="name" cssStyle="color: #ff0000;" />
															</div>
														</div>
														<div class="form-group ">
															<label for="email" class="control-label col-lg-3">Email
															</label>
															<div class="col-lg-8">
																<form:input path="email" class="form-control" id="email"
																	type="text" placeholder="username" required="" />
																<form:errors path="email" cssStyle="color: #ff0000;" />
															</div>
														</div>

														<div class="col-md-4"></div>
														<button
															class="col-md-4 btn btn-primary waves-effect waves-light btn-lg m-b-5"
															type="submit">Add</button>
														<div class="col-md-4"></div>


													</form:form>




												</div>
												<!-- panel-body -->
											</div>
											<!-- panel -->
										</div>
										<!-- col -->
									</div>
									<!-- End row -->




								</div>

							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->
					<!-- Start Widget -->
					<div class="row">
						<div class="col-md-6 col-sm-6 col-lg-3">
							<div class="mini-stat clearfix bx-shadow">
								<span class="mini-stat-icon bg-info"><i
									class="ion-clipboard"></i></span>
								<div class="mini-stat-info text-right text-muted">
									<span class="counter"><%=numofBatches%></span> Total Batches
								</div>

							</div>
						</div>
						<div class="col-md-6 col-sm-6 col-lg-3">
							<div class="mini-stat clearfix bx-shadow">
								<span class="mini-stat-icon bg-purple"><i
									class="ion-person"></i></span>
								<div class="mini-stat-info text-right text-muted">
									<span class="counter"><%=numofTrainers%></span> Internal
									Trainers
								</div>

							</div>
						</div>

						<div class="col-md-6 col-sm-6 col-lg-3">
							<div class="mini-stat clearfix bx-shadow">
								<span class="mini-stat-icon bg-success"><i
									class="ion-person-stalker"></i></span>
								<div class="mini-stat-info text-right text-muted">
									<span class="counter"><%=activeSessions%></span> Active
									Sessions
								</div>
								<div class="tiles-progress"></div>
							</div>
						</div>

						<div class="col-md-6 col-sm-6 col-lg-3">
							<div class="mini-stat clearfix bx-shadow">
								<form:form modelAttribute="user" id="download" method="post"
									action="download" novalidate="novalidate">
									<button
										class="btn btn-primary waves-effect waves-light btn-lg m-b-5">
										Download Time Table</button>
								</form:form>
								<div class="tiles-progress"></div>
							</div>
						</div>
					</div>
					<!-- End row-->


					<div class="row">
						<!-- Trainers list -->
						<div class="col-lg-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">Internal Trainers</h4>
								</div>
								<div class="panel-body">
									<div class="inbox-widget nicescroll mx-box">

										<%
											for (Trainer tr : trainers) {
										%>

										<a href="#">
											<div class="inbox-item">
												<div class="inbox-item-img">
													<img src="resources/images/Persistent_logo.png"
														class="img-circle" alt="">
												</div>
												<p class="inbox-item-author">
													<%
														out.println(tr.getTrainer_name());
													%>
												</p>
												<p class="inbox-item-text">
													<%
														out.println(tr.getEmail());
													%>
												</p>
												<p class="inbox-item-date">
													<%
														out.println(tr.getCity());
													%>
												</p>
											</div>
										</a>
										<%
											}
										%>

									</div>
								</div>
							</div>
						</div>
						<!-- end col -->

						<!-- Batch_List -->
						<div class="col-lg-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">Current Batches</h4>
								</div>
								<div class="panel-body">
									<div class="inbox-widget nicescroll mx-box">

										<%
											for (Batch vt : batches) {
										%>

										<a href="show_batch?val=<%=vt.getBatch_id()%>">
											<div class="inbox-item">
												<div class="inbox-item-img">
													<img src="resources/images/Persistent_logo.png"
														class="img-circle" alt="">
												</div>
												<p class="inbox-item-author">
													<%
														out.println(vt.getBatch_name());
													%>
												</p>
												<p class="inbox-item-text">
													<%
														out.println(vt.getNo_of_cand());
													%>
												</p>

											</div>
										</a>
										<%
											}
										%>

									</div>
								</div>
							</div>
						</div>


						<!-- Trainer_List -->

						<div class="col-lg-4">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">Active Sessions</h4>
								</div>
								<div class="panel-body">
									<div class="inbox-widget nicescroll mx-box">

										<%
											for (Session tr : sessions) {
										%>

										<a href="#">
											<div class="inbox-item">
												<div class="inbox-item-img">
													<img src="resources/images/Persistent_logo.png"
														class="img-circle" alt="">
												</div>
												<p class="inbox-item-author">
													<%
														out.println(tr.getSubjectName());
													%>
												</p>
												<p class="inbox-item-text">
													<%
														out.println(tr.getTrainererName());
													%>
												</p>
												<p class="inbox-item-date">
													<%
														out.println(tr.getEndDate());
													%>
												</p>
											</div>
										</a>
										<%
											}
										%>

									</div>
								</div>
							</div>
						</div>
						<!-- end col -->
						<!-- TODO -->


					</div>
					<!-- end row -->

				</div>
				<!-- container -->

			</div>
			<!-- content -->

			<footer class="footer text-right"> 2016 @ TT Generator </footer>

		</div>
		<!-- ============================================================== -->
		<!-- End Right content here -->
		<!-- ============================================================== -->


		<!-- Right Sidebar -->

		<!-- /Right-bar -->

	</div>
	<!-- END wrapper -->



	<script>
		var resizefunc = [];
	</script>

	<!-- jQuery  -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/waves.js"></script>
	<script src="resources/js/wow.min.js"></script>
	<script src="resources/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="resources/js/jquery.scrollTo.min.js"></script>
	<script src="resources/assets/chat/moment-2.2.1.js"></script>
	<script src="resources/assets/jquery-sparkline/jquery.sparkline.min.js"></script>
	<script src="resources/assets/jquery-detectmobile/detect.js"></script>
	<script src="resources/assets/fastclick/fastclick.js"></script>
	<script src="resources/assets/jquery-slimscroll/jquery.slimscroll.js"></script>
	<script src="resources/assets/jquery-blockui/jquery.blockUI.js"></script>

	<!-- sweet alerts -->
	<script src="resources/assets/sweet-alert/sweet-alert.min.js"></script>
	<script src="resources/assets/sweet-alert/sweet-alert.init.js"></script>

	<!-- flot Chart -->
	<script src="resources/assets/flot-chart/jquery.flot.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.time.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.tooltip.min.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.resize.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.pie.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.selection.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.stack.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.crosshair.js"></script>

	<!-- Counter-up -->
	<script src="resources/assets/counterup/waypoints.min.js"
		type="text/javascript"></script>
	<script src="resources/assets/counterup/jquery.counterup.min.js"
		type="text/javascript"></script>

	<!-- CUSTOM JS -->
	<script src="resources/js/jquery.app.js"></script>

	<!-- Dashboard -->
	<script src="resources/js/jquery.dashboard.js"></script>

	<!-- Chat -->
	<script src="resources/js/jquery.chat.js"></script>

	<!-- Todo -->
	<script src="resources/js/jquery.todo.js"></script>


	<script type="text/javascript">
		/* ==============================================
		Counter Up
		=============================================== */
		jQuery(document).ready(function($) {
			$('.counter').counterUp({
				delay : 100,
				time : 1200
			});
		});
	</script>
	<script>
		var resizefunc = [];
	</script>

	<!-- jQuery  -->

	<script src="resources/assets/jquery-sparkline/jquery.sparkline.min.js"></script>
	<script src="resources/js/wow.min.js"></script>
	<script src="resources/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="resources/js/jquery.scrollTo.min.js"></script>
	<script src="resources/assets/jquery-detectmobile/detect.js"></script>
	<script src="resources/assets/fastclick/fastclick.js"></script>
	<script src="resources/assets/jquery-slimscroll/jquery.slimscroll.js"></script>
	<script src="resources/assets/jquery-blockui/jquery.blockUI.js"></script>

	<!-- sweet alerts -->
	<script src="resources/assets/sweet-alert/sweet-alert.min.js"></script>
	<script src="resources/assets/sweet-alert/sweet-alert.init.js"></script>

	<!-- flot Chart -->
	<script src="resources/assets/flot-chart/jquery.flot.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.time.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.tooltip.min.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.resize.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.pie.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.selection.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.stack.js"></script>
	<script src="resources/assets/flot-chart/jquery.flot.crosshair.js"></script>



	<!-- Dashboard -->
	<script src="resources/js/jquery.dashboard.js"></script>





	<!-- CUSTOM JS -->
	<script src="resources/js/jquery.app.js"></script>

	<!--form validation-->
	<script type="text/javascript"
		src="resources/assets/jquery.validate/jquery.validate.min.js"></script>

	<!--form validation init-->
	<script src="resources/assets/jquery.validate/form-validation-init.js"></script>

	<script src="resources/js/jquery.app.js"></script>

	<script src="resources/assets/tagsinput/jquery.tagsinput.min.js"></script>
	<script src="resources/assets/toggles/toggles.min.js"></script>
	<script src="resources/assets/timepicker/bootstrap-timepicker.min.js"></script>
	<script src="resources/assets/timepicker/bootstrap-datepicker.js"></script>
	<script type="resources/text/javascript"
		src="resources/assets/colorpicker/bootstrap-colorpicker.js"></script>
	<script type="resources/text/javascript"
		src="resources/assets/jquery-multi-select/jquery.multi-select.js"></script>
	<script type="resources/text/javascript"
		src="resources/assets/jquery-multi-select/jquery.quicksearch.js"></script>
	<script
		src="resources/assets/bootstrap-inputmask/bootstrap-inputmask.min.js"
		type="text/javascript"></script>
	<script type="resources/text/javascript"
		src="resources/assets/spinner/spinner.min.js"></script>
	<script src="resources/assets/select2/select2.min.js"
		type="text/javascript"></script>


	<script>
		jQuery(document)
				.ready(
						function() {

							// Tags Input
							jQuery('#tags').tagsInput({
								width : 'auto'
							});

							// Form Toggles
							jQuery('.toggle').toggles({
								on : true
							});

							// Time Picker
							jQuery('#timepicker').timepicker({
								defaultTIme : false
							});
							jQuery('#timepicker2').timepicker({
								showMeridian : false
							});
							jQuery('#timepicker3').timepicker({
								minuteStep : 15
							});

							// Date Picker
							jQuery('#datepicker').datepicker();
							jQuery('#datepicker-inline').datepicker();
							jQuery('#datepicker-multiple').datepicker({
								numberOfMonths : 3,
								showButtonPanel : true
							});
							//colorpicker start

							$('.colorpicker-default').colorpicker({
								format : 'hex'
							});
							$('.colorpicker-rgba').colorpicker();

							//multiselect start

							$('#my_multi_select1').multiSelect();
							$('#my_multi_select2').multiSelect({
								selectableOptgroup : true
							});

							$('#my_multi_select3')
									.multiSelect(
											{
												selectableHeader : "<input type='text' class='form-control search-input' autocomplete='off' placeholder='search...'>",
												selectionHeader : "<input type='text' class='form-control search-input' autocomplete='off' placeholder='search...'>",
												afterInit : function(ms) {
													var that = this, $selectableSearch = that.$selectableUl
															.prev(), $selectionSearch = that.$selectionUl
															.prev(), selectableSearchString = '#'
															+ that.$container
																	.attr('id')
															+ ' .ms-elem-selectable:not(.ms-selected)', selectionSearchString = '#'
															+ that.$container
																	.attr('id')
															+ ' .ms-elem-selection.ms-selected';

													that.qs1 = $selectableSearch
															.quicksearch(
																	selectableSearchString)
															.on(
																	'keydown',
																	function(e) {
																		if (e.which === 40) {
																			that.$selectableUl
																					.focus();
																			return false;
																		}
																	});

													that.qs2 = $selectionSearch
															.quicksearch(
																	selectionSearchString)
															.on(
																	'keydown',
																	function(e) {
																		if (e.which == 40) {
																			that.$selectionUl
																					.focus();
																			return false;
																		}
																	});
												},
												afterSelect : function() {
													this.qs1.cache();
													this.qs2.cache();
												},
												afterDeselect : function() {
													this.qs1.cache();
													this.qs2.cache();
												}
											});

							//spinner start
							$('#spinner1').spinner();
							$('#spinner2').spinner({
								disabled : true
							});
							$('#spinner3').spinner({
								value : 0,
								min : 0,
								max : 10
							});
							$('#spinner4').spinner({
								value : 0,
								step : 5,
								min : 0,
								max : 200
							});
							//spinner end

							// Select2
							jQuery(".select2").select2({
								width : '100%'
							});
						});
	</script>

	<script type="text/javascript">
		/* ==============================================
		Counter Up
		=============================================== */
		jQuery(document).ready(function($) {
			$('.counter').counterUp({
				delay : 100,
				time : 1200
			});
		});
	</script>
</body>
</html>