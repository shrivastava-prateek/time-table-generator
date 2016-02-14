<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="com.bean.User"%>
<%@page import="com.bean.Batch"%>
<%@page import="com.dao.BatchDao"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
<meta name="author" content="Coderthemes">

<link rel="shortcut icon" href="resources/images/favicon_1.ico">

<title>Delete Batch</title>

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

<script src="resources/js/modernizr.min.js"></script>




<%!String type;%>
<%
	Object obj = session.getAttribute("result");
	System.out.println(obj);
	if (obj == null) {
%>
<script>
	function func1(type) {
		console.log("in else");
		alert("Please log in again");
		window.location = "logout";

	}
</script>
<%
	}
%>

#f{ color:#003366 }

</head>



<body class="fixed-left" onload="func1('<%=type%>')">

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


							<li class="dropdown"><a href="welcome"
								class="dropdown-toggle profile" data-toggle="dropdown"
								aria-expanded="true"><img
									src="resources/images/Persistent_logo.png" alt="user-img"
									class="img-thumbnail"> </a>
								<ul class="dropdown-menu">


									<li><a href="welcome"><i class="md md-settings-power"></i>
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
						<li><a href="index_manager" class="waves-effect active"><i
								class="md md-home"></i><span> Dashboard </span></a></li>


						<li><a href="add_batch" class="waves-effect"><i
								class="md md-playlist-add"></i><span>Add New Batch </span></a></li>

						<li><a href="del_batch" class="waves-effect"><i
								class="md md-playlist-add"></i><span>Delete Batch </span></a></li>
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


					<!-- Form-validation -->
					<div class="row">

						<div class="col-sm-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Select the Batch name</h3>
								</div>
								<div class="panel-body">



									<div class=" form">


										<form:form modelAttribute="batch"
											class="cmxform form-horizontal tasi-form" id="signupForm"
											method="post" action="del_batch_1" novalidate="novalidate">
											<div class="form-group ">
												<label for="batch_name" class="control-label col-lg-2">Batch
													Name </label>

												<div class="col-lg-10">

													<%
														BatchDao bdao = (BatchDao) request.getAttribute("bdao");
															List<Batch> batch_list = bdao.getAllBatch();
													%>


													<form:select path="batch_name" class="form-control">
														<%-- <form:option style="color: black;" value="0" id="f"> Select a Batch</form:option> --%>

														<%
															for (Batch batch : batch_list) {
														%>

														<form:option style="color: black;"
															value="<%=batch.getBatch_name()%>" id="f">
															<%
																out.println(batch.getBatch_name());
															%>
														</form:option>
														<%
															}
														%>


													</form:select>



												</div>

											</div>

											<div class="col-md-4"></div>
											<button
												class="col-md-4 btn btn-primary waves-effect waves-light btn-lg m-b-5"
												type="submit">Delete</button>
											<div class="col-md-4"></div>

										</form:form>


									</div>
								</div>

							</div>
						</div>
					</div>
					<!-- .form -->



				</div>
				<!-- container -->

			</div>
			<!-- content -->

			<footer class="footer text-right"> 2016 © TT Generator. </footer>

		</div>
		<!-- ============================================================== -->
		<!-- End Right content here -->
		<!-- ============================================================== -->





	</div>
	<!-- END wrapper -->

	<script>
		var resizefunc = [];
	</script>

	<!-- jQuery  -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/waves.js"></script>
	<script src="resources/assets/jquery-sparkline/jquery.sparkline.min.js"></script>
	<script src="resources/js/wow.min.js"></script>
	<script src="resources/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="resources/js/jquery.scrollTo.min.js"></script>
	<script src="resources/assets/jquery-detectmobile/detect.js"></script>
	<script src="resources/assets/fastclick/fastclick.js"></script>
	<script src="resources/assets/jquery-slimscroll/jquery.slimscroll.js"></script>
	<script src="resources/assets/jquery-blockui/jquery.blockUI.js"></script>
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
	<!-- sweet alerts -->
		<script src="resources/assets/sweet-alert/sweet-alert.min.js">
	</script>
	<script src="resources/assets/sweet-alert/sweet-alert.init.js"></script>

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
	<script type="text/javascript"
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


</body>
</html>