<%-- 
    Document   : search
    Created on : 25.09.2017, 12:23:17
    Author     : redlongcity
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="utf-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<meta name="description" content="">
	
	<meta name="author" content="">
	
	<link rel="icon" href="images/favicon.ico">
	
	<title>Searching</title>
        
            <!-- Bootstrap core CSS -->
    <link href="static/dist/css/bootstrap.min.css" rel="stylesheet">

    <!--Fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    
    <!-- Icons -->
    <link href="static/css/font-awesome.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="static/css/style.css" rel="stylesheet">
    
    </head>
<body ng-app="myApp" class="ng-cloak">
    <div class="container-fluid" id="wrapper" ng-controller="ConstantsController as constants">
    <div class="row">
            <nav class="sidebar col-xs-12 col-sm-4 col-lg-3 col-xl-2 bg-faded sidebar-style-1">
                    <h1 class="site-title"><a href="http://localhost:8084/EasyTour/search"><em class="fa fa-globe" aria-hidden="true"></em> Easy Tours</a></h1>

                    <a href="#menu-toggle" class="btn btn-default" id="menu-toggle"><em class="fa fa-bars"></em></a>

                    <ul class="nav nav-pills flex-column sidebar-nav">
                            <li class="nav-item"><a class="nav-link active" href="http://localhost:8084/EasyTour/search"><em class="fa fa-search" aria-hidden="true"></em> Searching <span class="sr-only">(current)</span></a></li>
                            <li class="nav-item"><a class="nav-link" href="http://localhost:8084/EasyTour/statistics"><em class="fa fa-bar-chart"></em> Statistics</a></li>
                            <li class="nav-item"><a class="nav-link" href="http://localhost:8084/EasyTour/settings"><em class="fa fa-cogs" aria-hidden="true"></em> Settings</a></li>
                    </ul>

                    <a href="#" class="logout-button"><em class="fa fa-power-off"></em> Signout</a></nav>

            <main class="col-xs-12 col-sm-8 offset-sm-4 col-lg-9 offset-lg-3 col-xl-10 offset-xl-2 pt-3 pl-4">
                    <header class="page-header row justify-center">
                            <div class="col-md-6 col-lg-8" >
                                    <h1 class="float-left text-center text-md-left">Searching</h1>
                            </div>

                            <div class="dropdown user-dropdown col-md-6 col-lg-4 text-center text-md-right"><a class="btn btn-stripped dropdown-toggle" href="https://example.com" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <img src="static/images/profile-pic.jpg" alt="profile photo" class="circle float-left profile-photo" width="50" height="auto">

                                    <div class="username mt-1">
                                            <h4 class="mb-1">Admin</h4>

                                            <h6 class="text-muted">Super Admin</h6>
                                    </div>
                                    </a>

                                    <div class="dropdown-menu dropdown-menu-right" style="margin-right: 1.5rem;" aria-labelledby="dropdownMenuLink"><a class="dropdown-item" href="#"><em class="fa fa-user-circle mr-1"></em> View Profile</a>
                                         <a class="dropdown-item" href="#"><em class="fa fa-sliders mr-1"></em> Preferences</a>
                                         <a class="dropdown-item" href="#"><em class="fa fa-power-off mr-1"></em> Logout</a></div>
                            </div>

                            <div class="clear"></div>
                    </header>

                    <section class="row">
                            <div class="col-sm-12">
                                    <section class="row">
                                            <div class="col-md-12 col-lg-12">

                                                    <div class="card mb-4 text-center" ng-controller="TourController as ctrl">
                                                            <div class="card-block pull-right">
                                                                    <h3 class="card-title">Tours</h3>
                                                                    
                                                                    <!--<div class="btn-group" role="group" ng-controller="DateController">
                                                                        <div clas s="input-group date">
                                                                            <input type="text" class="form-control" ng-model="constants.date" id="from_date" placeholder="Date from: dd/mm/yyyy"><span class="input-group-addon"><i class="carpet"></i></span>
                                                                        </div>
                                                                    </div>-->
                                                                    
                                                                    <div role="tablist" id="accordion" aria-multiselectable="true">
                                                                        <div class="card">
                                                                            <div class="card-header" role="tab" id="headingOne">
                                                                                <h5 class="mb-0">
                                                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" <em class="fa fa-search fa-lg"></em> Searching</a>
                                                                                </h5>
                                                                            </div>
                                                                            <div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
                                                                                <div class="card-block">
                                                                    <div class="btn-group btn-lg" role="group" >
                                                                        <div class="btn-group" ng-controller="CountryController as countryctrl">
                                                                            <button type="button" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown">
                                                                                <em class="fa fa-globe fa-lg"></em> <span ng-bind="constants.but_country"></span>
                                                                            </button>
                                                                            <div class="dropdown-menu">
                                                                                <div ng-repeat="c in countryctrl.countries">
                                                                                    <button class="dropdown-item" ng-click="constants.but_country=c.name; constants.country_id=c.id">
                                                                                        <span ng-bind="c.name"></span></button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    
                                                                        
                                                                        <div class="btn-group" ng-controller="CitiesController as cityctrl">
                                                                            <button type="button" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown">
                                                                                <em class="fa fa-globe fa-lg"></em> <span ng-bind="constants.but_city"></span>
                                                                            </button>
                                                                            <div class="dropdown-menu">
                                                                                <div ng-repeat="c in cityctrl.cities">
                                                                                    <button class="dropdown-item" ng-click="constants.but_city=c.name; constants.city_id=c.id">
                                                                                        <span ng-bind="c.name"></span></button>
                                                                                </div>
                                                                            </div>
                                                                            
                                                                        </div>
                                                                    
                                                                            
                                                                        <div class="btn-group">
                                                                            <button type="button" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown">
                                                                                <em class="fa fa-star-half-o fa-lg" aria-hidden="true"></em> <span ng-bind="constants.but_rating"></span>
                                                                            </button>
                                                                            <div class="dropdown-menu">
                                                                                <div ng-repeat="c in constants.ratings_array_face">
                                                                                    <button class="dropdown-item" ng-click="constants.but_rating=c;constants.hotel_rating=$index">
                                                                                        <span ng-bind="c"></span></button>
                                                                                </div>
                                                                            </div>
                                                                            
                                                                        </div>
                                                                    </div> 
                                                                    <div class="btn-group btn-lg" role="group" >
                                                                        <div class="btn-group">
                                                                            <button type="button" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown">
                                                                                <em class="fa fa-clock-o fa-lg" aria-hidden="true"></em> <span ng-bind="constants.but_duration"></span>
                                                                            </button>
                                                                            <div class="dropdown-menu">
                                                                                <div ng-repeat="c in constants.duration_array_face">
                                                                                    <button class="dropdown-item" ng-click="constants.but_duration=c;constants.duration_from=$index;
                                                                                            constants.duration_till=$index">
                                                                                        <span ng-bind="c"></span></button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        
                                                                        <div class="btn-group" ng-controller="MealTypeController as mealctrl">
                                                                            <button type="button" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown">
                                                                                <em class="fa fa-cutlery fa-lg" aria-hidden="true"></em> <span ng-bind="constants.but_mealtype"></span>
                                                                            </button>
                                                                            <div class="dropdown-menu">
                                                                                <div ng-repeat="c in mealctrl.mealtypes">
                                                                                    <button class="dropdown-item" ng-click="constants.but_mealtype=c.name_full;constants.meal_type_id=c.id">
                                                                                        <span ng-bind="c.name_full"></span></button>
                                                                                </div>
                                                                            </div>
                                                                            
                                                                        </div>
                                                                    </div>
                                                                    <div class="btn-group" role="group">
                                                                        
                                                                        <button type="button" class="btn btn-sm btn-danger" ng-click="ctrl.fetchAllTours()">
                                                                                <em class="fa fa-search fa-lg"></em> Find All
                                                                        </button>
                                                                        
                                                                        <button type="button" class="btn btn-sm btn-success" ng-click="ctrl.fetchTourByRequest(constants.getRequest())">
                                                                                <em class="fa fa-search fa-lg"></em> Search
                                                                        </button>
                                                                        
                                                                    </div>           
                                                                    </div> 
                                                                            </div>
                                                                        </div>
                                                                     <div div class="card">
                                                                           <div class="card-header" role="tab" id="headingTwo">
                                                                                <h5 class="mb-0">
                                                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo" <em class="fa fa-trash fa-lg"></em> Deleting</a>
                                                                                </h5>
                                                                            </div>
                                                                            <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
                                                                               <div class="card-block">
                                                                        <div class="input-daterange input-group"   data-provide="datepicker" data-date-format="mm/dd/yyyy">
                                                                            <input type="text" class="input-sm form-control" ng-model="ctrl.date_from"  name="start"  placeholder="mm/dd/yyyy" />
                                                                            <span class="input-group-addon">to</span>
                                                                            <input type="text" class="input-sm form-control" ng-model="ctrl.date_till" name="end"  placeholder="mm/dd/yyyy"/>
                                                                        </div>
                                                                   <div class="btn-group btn-lg" role="group" >
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteToursBetween()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Between
                                                                        </button>
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteAllTours()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> All
                                                                        </button>
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteToursBefore()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Before
                                                                        </button>
                                                                   </div>    
                                                                        <input type="text" class="form-control" ng-model="ctrl.date_before" placeholder="Date: mm/dd/yyyy" 
                                                                               data-provide="datepicker" data-date-format="mm/dd/yyyy">  
                                                                                    
                                                                                </div> 
                                                                            </div>

                                                                        </div> 
                                                                    </div>
                                                                    
                                                                    <div class="divider"></div>
                                                                        
                                                                    <div class="progress progress-custom">
                                                                                        <div class="progress-bar bg-primary" style="width: 25%" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                                                                </div>
                                                                        </div>

                                                                        <div class="col-sm-3 col-md-2 col-xl-1">
                                                                                <div class="progress-label">25%</div>
                                                                        </div>
                                                        
                                                        <button class="but but-success" ng-click="ctrl.Timer(21000,100)">Start</button>
                                                        <h6>{{load_delay}}</h6>
                                                        
                                                                    <div class="table-responsive">
                                                                        <table class="table table-striped">
                                                                                <thead>
                                                                                        <tr>
                                                                                                <th>#</th>
                                                                                            
                                                                                                <th>ID.</th>

                                                                                                <th>Type</th>

                                                                                                <th>Coutry</th>

                                                                                                <th>Region</th>

                                                                                                <th>Hotel Id</th>

                                                                                                <th>Hotel</th>

                                                                                                <th>Hotel Rating</th>

                                                                                                <th>Meal Type</th>

                                                                                                <th>Room Type</th>

                                                                                                <th>Adult Amount</th>

                                                                                                <th>Child Amount</th>

                                                                                                <th>Accomodation</th>

                                                                                                <th>Duration</th>

                                                                                                <th>Date From</th>

                                                                                                <th>From City</th>

                                                                                                <th>Transport Type</th>
                                                                                        </tr>
                                                                                </thead>

                                                                                <tbody>
                                                                                    <tr ng-repeat="t in ctrl.tours">
                                                                                            
                                                                                        <td><span ng-bind="$index"></span>    

                                                                                        <td><span ng-bind="t.id"></span></td>

                                                                                        <td><span ng-bind="t.type"></span></td>

                                                                                        <td><span ng-bind="t.country.name"></span></td>

                                                                                        <td><span ng-bind="t.region"></span></td>

                                                                                        <td><span ng-bind="t.hotel_id"></span></td>

                                                                                        <td><span ng-bind="t.hotel"></span></td>

                                                                                        <td><span ng-bind="t.hotel_Rating.name"></span></td>

                                                                                        <td><span ng-bind="t.meal_Type.name_full"></span></td>

                                                                                        <td><span ng-bind="t.room_Type"></span></td>

                                                                                        <td><span ng-bind="t.adult_Amount"></span></td>

                                                                                        <td><span ng-bind="t.child_Amount"></span></td>

                                                                                        <td><span ng-bind="t.accomodation"></span></td>

                                                                                        <td><span ng-bind="t.duration"></span></td>

                                                                                        <td><span ng-bind="t.date_From | date:'dd/MM/yyyy'"></span></td>

                                                                                        <td><span ng-bind="t.from_Cities.name"></span></td>

                                                                                        <td><span ng-bind="t.transport_Type"></span></td>

                                                                                        </tr>
                                                                                </tbody>
                                                                        </table>
                                                                    </div>
                                                            </div>
                                                    </div>

                                            </div>
                                    </section>
                                    <section class="row">
                                            <div class="col-12 mt-1 mb-4">Template by <a href="https://www.medialoot.com">Medialoot</a></div>
                                    </section>
                            </div>
                    </section>

            </main>
    </div>
</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="static/js/jquery-3.2.1.min.js"></script>
    <script src="static/dist/js/bootstrap.min.js"></script>
    <script src="static/js/chart.min.js"></script>
    <script src="static/js/chart-data.js"></script>
    <script src="static/js/easypiechart.js"></script>
    <script src="static/js/easypiechart-data.js"></script>
    <script src="static/js/bootstrap-datepicker.js"></script>
    <script src="static/js/custom.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
    <script src="<c:url value='/static/js/app.js' />"></script>
    <script src="<c:url value='/static/js/controller/search_constants_cotntroller.js' />"></script>
    <script src="<c:url value='/static/js/service/tour_service.js' />"></script>
    <script src="<c:url value='/static/js/service/url_service.js' />"></script>
    <script src="<c:url value='/static/js/controller/tour_controller.js' />"></script>
    <script src="<c:url value='/static/js/service/country_service.js' />"></script>
    <script src="<c:url value='/static/js/controller/country_controller.js' />"></script>
    <script src="<c:url value='/static/js/service/from_cities_service.js' />"></script>
    <script src="<c:url value='/static/js/controller/from_cities_controller.js' />"></script>
    <script src="<c:url value='/static/js/service/meal_type_service.js' />"></script>
    <script src="<c:url value='/static/js/controller/meal_type_controller.js' />"></script>
    <script src="<c:url value='/static/js/controller/date_controller.js' />"></script>
    <script>
	    window.onload = function () {
	var chart1 = document.getElementById("line-chart").getContext("2d");
	window.myLine = new Chart(chart1).Line(lineChartData, {
	responsive: true,
	scaleLineColor: "rgba(0,0,0,.2)",
	scaleGridLineColor: "rgba(0,0,0,.05)",
	scaleFontColor: "#c5c7cc"
	});
};
	</script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    
	  </body>
</html>
