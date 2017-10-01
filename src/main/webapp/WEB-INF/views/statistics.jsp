<%-- 
    Document   : statistics
    Created on : 27.09.2017, 10:30:01
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
    <div class="container-fluid" id="wrapper">
    <div class="row">
            <nav class="sidebar col-xs-12 col-sm-4 col-lg-3 col-xl-2 bg-faded sidebar-style-1">
                    <h1 class="site-title"><a href="http://localhost:8084/EasyTour/search"><em class="fa fa-globe" aria-hidden="true"></em> Easy Tours</a></h1>

                    <a href="#menu-toggle" class="btn btn-default" id="menu-toggle"><em class="fa fa-bars"></em></a>

                    <ul class="nav nav-pills flex-column sidebar-nav">
                            <li class="nav-item"><a class="nav-link" href="http://localhost:8084/EasyTour/search"><em class="fa fa-search" aria-hidden="true"></em> Searching <span class="sr-only">(current)</span></a></li>
                            <li class="nav-item"><a class="nav-link active" href="http://localhost:8084/EasyTour/statistics"><em class="fa fa-bar-chart"></em> Statistics</a></li>
                            <li class="nav-item"><a class="nav-link" href="http://localhost:8084/EasyTour/settings"><em class="fa fa-cogs" aria-hidden="true"></em> Settings</a></li>
                    </ul>

                    <a href="#" class="logout-button"><em class="fa fa-power-off"></em> Signout</a></nav>

            <main class="col-xs-12 col-sm-8 offset-sm-4 col-lg-9 offset-lg-3 col-xl-10 offset-xl-2 pt-3 pl-4">
                    <header class="page-header row justify-center">
                            <div class="col-md-6 col-lg-8" >
                                    <h1 class="float-left text-center text-md-left">Statistics</h1>
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
                            <div class="col-sm-12 col-md-12">
                                    <section class="row">
                                            <div class="col-md-12 col-lg-12 col-xl-4" ng-controller="SessionController as ctrl">

                                                    <div class="card mb-4 text-center">
                                                            <div class="card-block pull-right">
                                                                    <h3 class="card-title text-md-left">Sessions</h3>
                                                                    
                                                                    <div ng-controller="TimeController as time">
                                                                    <div class="btn-group btn-lg" role="group" >
                                                                            <button type="button" class="btn btn-sm btn-danger" 
                                                                                    ng-click="ctrl.fetchAllSessions()">
                                                                                <em class="fa fa-search fa-lg"></em> All
                                                                            </button>
                                                                        
                                                                            <button type="button" class="btn btn-sm btn-primary"
                                                                                    ng-click="ctrl.fetchElementsByDates(time.getBeforeMonth(),time.getNow())">
                                                                                <em class="fa fa-search fa-lg"></em> Month
                                                                            </button>
                                                                            
                                                                       </div>  
                                                                    
                                                                       <div class="btn-group btn-lg" role="group" >
                                                                            <button type="button" class="btn btn-sm btn-primary"
                                                                                    ng-click="ctrl.fetchElementsByDates(time.getBeforeWeek(),time.getNow())">
                                                                                <em class="fa fa-search fa-lg" aria-hidden="true"></em> Week
                                                                            </button>
                                                                            
                                                                            <button type="button" class="btn btn-sm btn-success"
                                                                                    ng-click="ctrl.fetchElementsByDates(time.getToday(),time.getNow())">
                                                                                <em class="fa fa-search fa-lg" aria-hidden="true"></em> Today
                                                                            </button>
                                                                        
                                                                       </div> 
                                                                    </div>
                                                                    
                                                                        <div class="input-daterange input-group"   data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                                            <input type="text" class="input-sm form-control" ng-model="ctrl.date_from"  name="start"  placeholder="dd/mm/yyyy" />
                                                                            <span class="input-group-addon">to</span>
                                                                            <input type="text" class="input-sm form-control" ng-model="ctrl.date_till" name="end"  placeholder="dd/mm/yyyy"/>
                                                                        </div>
                                                                   <div class="btn-group btn-lg" role="group" >
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteSessionsBetween()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Between
                                                                        </button>
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteAllSessions()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> All
                                                                        </button>
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteSessionsBefore()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Before
                                                                        </button>
                                                                   </div>    
                                                                        <input type="text" class="form-control" ng-model="ctrl.date_before" placeholder="Date: dd/mm/yyyy" 
                                                                               data-provide="datepicker" data-date-format="dd/mm/yyyy">  
                                                                        
                                                                    <div class="divider"></div>
                                                                    
                                                                    <div class="table-responsive">
                                                                     <table class="table table-striped">
                                                                            <thead>
                                                                                    <tr>
                                                                                            <th>#</th>
                                                                                        
                                                                                            <th>ID.</th>

                                                                                            <th>Session Time</th>

                                                                                    </tr>
                                                                            </thead>

                                                                            <tbody>
                                                                                <tr ng-repeat="s in ctrl.sessions">
                                                                                    
                                                                                    <td><span ng-bind="$index"></span></td>

                                                                                    <td><span ng-bind="s.id"></span></td>

                                                                                    <td><span ng-bind="s.sessionTime | date:'dd/MM/yyyy-HH-mm-ss'"></span></td>

                                                                                    </tr>
                                                                            </tbody>
                                                                    </table>
                                                                    </div>
                                                            </div>
                                                    </div>

                                            </div>
                                         <div class="col-md-12 col-lg-12 col-xl-8" ng-controller="PullElementController as ctrl">

                                                    <div class="card mb-4 text-center">
                                                            <div class="card-block pull-right">
                                                                    <h3 class="card-title text-md-left">Pull Elements</h3>
                                                                    <div ng-controller="TimeController as time">
                                                                        
                                                                    <div class="btn-group btn-lg" role="group" >
                                                                            <button type="button" class="btn btn-sm btn-danger" 
                                                                                    ng-click="ctrl.fetchAllElements()">
                                                                                <em class="fa fa-search fa-lg"></em> All
                                                                            </button>
                                                                        
                                                                        <button type="button" class="btn btn-sm btn-primary" 
                                                                                ng-click="ctrl.fetchElementsByDates(time.getBeforeMonth(),time.getNow())">
                                                                                <em class="fa fa-search fa-lg"></em>Month
                                                                            </button>
                                                                            
                                                                       </div>  
                                                                    
                                                                       <div class="btn-group btn-lg" role="group" >
                                                                            <button type="button" class="btn btn-sm btn-primary"
                                                                                    ng-click="ctrl.fetchElementsByDates(time.getBeforeWeek(),time.getNow())">
                                                                                <em class="fa fa-search fa-lg" aria-hidden="true"></em>Week
                                                                            </button>
                                                                            
                                                                            <button type="button" class="btn btn-sm btn-success"
                                                                                    ng-click="ctrl.fetchElementsByDates(time.getToday(),time.getNow())">
                                                                                <em class="fa fa-search fa-lg" aria-hidden="true"></em>Day
                                                                            </button>
                                                                        
                                                                       </div>   
                                                                    </div>
                                                                    
                                                                        <div class="input-daterange input-group"   data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                                            <input type="text" class="input-sm form-control" ng-model="ctrl.date_from" name="start"  placeholder="dd/mm/yyyy" />
                                                                            <span class="input-group-addon">to</span>
                                                                            <input type="text" class="input-sm form-control" ng-model="ctrl.date_till" name="end"  placeholder="dd/mm/yyyy"/>
                                                                        </div>
                                                                   <div class="btn-group btn-lg" role="group" >
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteElementsBetween()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Between
                                                                        </button>
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteElements()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> All
                                                                        </button>
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteElementsBefore()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Before
                                                                        </button>
                                                                   </div>    
                                                                    <input type="text" class="form-control" ng-model="ctrl.date_before" placeholder="Date: dd/mm/yyyy" data-provide="datepicker" data-date-format="dd/mm/yyyy">  
                                                                        
                                                                    <div class="divider"></div>
                                                                    
                                                                    <div class="table-responsive">
                                                                        <table class="table table-striped">
                                                                                <thead>
                                                                                        <tr>
                                                                                                <th>#</th>
                                                                                            
                                                                                                <th>ID.</th>

                                                                                                <th>Date Time</th>

                                                                                                <th>Request Id</th>

                                                                                                <th>Done</th>

                                                                                                <th>Priority</th>

                                                                                                <th>By Human</th>

                                                                                                <th>Session ID</th>
                                                                                        </tr>
                                                                                </thead>

                                                                                <tbody>
                                                                                    <tr ng-repeat="e in ctrl.elements">
                                                                                        
                                                                                        <td><span ng-bind="$index"></span></td>

                                                                                        <td><span ng-bind="e.id"></span></td>

                                                                                        <td><span ng-bind="e.request_pull_DateTime | date:'dd/MM/yyyy-HH-mm-ss '"></span></td>

                                                                                        <td><span ng-bind="e.request.id"></span></td>

                                                                                        <td><span ng-bind="e.done"></span></td>

                                                                                        <td><span ng-bind="e.priority"></span></td>

                                                                                        <td><span ng-bind="e.byHuman"></span></td>

                                                                                        <td><span ng-bind="e.updateSession.id"></span></td>

                                                                                        </tr>
                                                                                </tbody>
                                                                        </table>
                                                                    </div>
                                                            </div>
                                                    </div>
                                            </div>
                                        
                                    </section>
                            </div>
                    </section>
                    
                    <section class="row">
                            <div class="col-sm-12 col-md-12">
                                    <section class="row">
                                            <div class="col-md-12 col-lg-6 col-xl-3" ng-controller="CountryController as ctrl">

                                                    <div class="card mb-4 text-center">
                                                            <div class="card-block pull-right">
                                                                    <h3 class="card-title text-md-left">Countries</h3>
                                                                    
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteAllCountries()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Delete All
                                                                        </button>
                                                                    
                                                                    <div class="divider"></div>
                                                                    
                                                                    <div class="table-responsive">
                                                                        <table class="table table-striped">
                                                                                <thead>
                                                                                        <tr>
                                                                                                <th>#</th>
                                                                                                
                                                                                                <th>ID.</th>

                                                                                                <th>Name</th>

                                                                                        </tr>
                                                                                </thead>

                                                                                <tbody>
                                                                                    <tr ng-repeat="c in ctrl.countries">
                                                                                        
                                                                                        <td><span ng-bind="$index"></span></td>

                                                                                        <td><span ng-bind="c.id"></span></td>

                                                                                        <td><span ng-bind="c.name"></span></td>


                                                                                        </tr>
                                                                                </tbody>
                                                                        </table>
                                                                    </div>
                                                            </div>
                                                    </div>

                                            </div>
                                         <div class="col-md-12 col-lg-6 col-xl-3" ng-controller="CitiesController as ctrl">

                                                    <div class="card mb-4 text-center">
                                                            <div class="card-block pull-right">
                                                                    <h3 class="card-title text-md-left">Cites</h3>
                                                                        
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteAllCities()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Delete All
                                                                        </button>
                                                                        
                                                                    <div class="divider"></div>
                                                                    
                                                                    <div class="table-responsive">
                                                                        <table class="table table-striped">
                                                                                <thead>
                                                                                        <tr>
                                                                                            
                                                                                                <th>#</th>
                                                                                            
                                                                                                <th>ID.</th>

                                                                                                <th>Name</th>

                                                                                        </tr>
                                                                                </thead>

                                                                                <tbody>
                                                                                    <tr ng-repeat="c in ctrl.cities">
                                                                                        
                                                                                        <td><span ng-bind="$index"></span></td>

                                                                                        <td><span ng-bind="c.id"></span></td>

                                                                                        <td><span ng-bind="c.name"></span></td>


                                                                                        </tr>
                                                                                </tbody>
                                                                        </table>
                                                                    </div>
                                                            </div>
                                                    </div>

                                            </div>
                                        
                                         <div class="col-md-12 col-lg-12 col-xl-6" ng-controller="RequestController as ctrl">

                                                    <div class="card mb-4 text-center">
                                                            <div class="card-block pull-right">
                                                                    <h3 class="card-title text-md-left">Requests</h3>
                                                                    
                                                                                                                                            
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteAllRequests()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Delete All
                                                                        </button>
                                                                    
                                                                    <div class="divider"></div>
                                                                    
                                                                    <div class="table-responsive">
                                                                        <table class="table table-striped">
                                                                                <thead>
                                                                                        <tr>
                                                                                                <th>#</th>
                                                                                            
                                                                                                <th>ID.</th>

                                                                                                <th>Coutry ID</th>

                                                                                                <th>City ID</th>

                                                                                                <th>Hotel Rating</th>

                                                                                                <th>Night From</th>

                                                                                                <th>Night Till</th>

                                                                                                <th>Meal Type</th>
                                                                                                
                                                                                                <th>Delay</th>
                                                                                        </tr>
                                                                                </thead>

                                                                                <tbody>
                                                                                    <tr ng-repeat="r in ctrl.requests">
                                                                                        
                                                                                        <td><span ng-bind="$index"></span></td>

                                                                                        <td><span ng-bind="r.id"></span></td>

                                                                                        <td><span ng-bind="r.country.id"></span></td>

                                                                                        <td><span ng-bind="r.from_Cities.id"></span></td>

                                                                                        <td><span ng-bind="r.hotel_Rating"></span></td>

                                                                                        <td><span ng-bind="r.night_From"></span></td>

                                                                                        <td><span ng-bind="r.night_Till"></span></td>

                                                                                        <td><span ng-bind="r.meal_Type.name_full"></span></td>
                                                                                        
                                                                                        <td><span ng-bind="r.requestDelay"></span></td>

                                                                                        </tr>
                                                                                </tbody>
                                                                        </table>
                                                                    </div>
                                                            </div>
                                                    </div>

                                            </div>
                                    </section>
                                </div>
                        </section>
                        
                        <section class="row">
                            <div class="col-sm-12">
                                    <section class="row">
                                            <div class="col-md-12 col-lg-6 col-xl-3" ng-controller="CurrencyController as ctrl">

                                                    <div class="card mb-4 text-center">
                                                            <div class="card-block pull-right">
                                                                    <h3 class="card-title text-mb-left">Currencies</h3>
                                                                    
                                                                                                                                            
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteAllCurrencies()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Delete All
                                                                        </button>
                                                                    
                                                                    <div class="divider"></div>
                                                                    
                                                                    <div class="table-responsive">
                                                                        <table class="table table-striped">
                                                                                <thead>
                                                                                        <tr>
                                                                                                <th>#</th>
                                                                                            
                                                                                                <th>ID.</th>

                                                                                                <th>Name</th>

                                                                                        </tr>
                                                                                </thead>

                                                                                <tbody>
                                                                                    <tr ng-repeat="c in ctrl.currencies">
                                                                                        
                                                                                        <td><span ng-bind="$index"></span></td>

                                                                                        <td><span ng-bind="c.id"></span></td>

                                                                                        <td><span ng-bind="c.name"></span></td>

                                                                                        </tr>
                                                                                </tbody>
                                                                        </table>
                                                                    </div>
                                                            </div>
                                                    </div>

                                            </div>
                                         <div class="col-md-12 col-lg-6 col-xl-3" ng-controller="MealTypeController as ctrl">

                                                    <div class="card mb-4 text-center">
                                                            <div class="card-block pull-right">
                                                                    <h3 class="card-title text-mb-left">Meal Types</h3>
                                                                    
                                                                                                                                            
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteAllMealTypes()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Delete All
                                                                        </button>
                                                                    
                                                                    <div class="divider"></div>
                                                                    
                                                                    <div class="table-responsive">
                                                                        <table class="table table-striped">
                                                                                <thead>
                                                                                        <tr>
                                                                                                <th>#</th>
                                                                                            
                                                                                                <th>ID.</th>

                                                                                                <th>Name</th>

                                                                                                <th>Full Name</th>
                                                                                        </tr>
                                                                                </thead>

                                                                                <tbody>
                                                                                    <tr ng-repeat="r in ctrl.mealtypes">
                                                                                        
                                                                                        <td><span ng-bind="$index"></span></td>

                                                                                        <td><span ng-bind="r.id"></span></td>

                                                                                        <td><span ng-bind="r.name"></span></td>

                                                                                        <td><span ng-bind="r.name_full"></span></td>
                                                                                        </tr>
                                                                                </tbody>
                                                                        </table>
                                                                    </div>
                                                            </div>
                                                    </div>

                                            </div>
                                        
                                         <div class="col-md-12 col-lg-12 col-xl-6" ng-controller="HotelRatingController as ctrl">

                                                    <div class="card mb-4 text-center">
                                                            <div class="card-block pull-right">
                                                                    <h3 class="card-title text-mb-left">Hotel Ratings</h3>
                                                                    
                                                                                                                                            
                                                                        <button type="button" class="btn btn-sm btn-danger"
                                                                                    ng-click="ctrl.deleteAllRatings()">
                                                                                <em class="fa fa-trash" aria-hidden="true"></em> Delete All
                                                                        </button>
                                                                    
                                                                    <div class="divider"></div>
                                                                    
                                                                    <div class="table-responsive">
                                                                        <table class="table table-striped">
                                                                            <table class="table table-striped">
                                                                                    <thead>
                                                                                            <tr>
                                                                                                    <th>#</th>
                                                                                                
                                                                                                    <th>ID.</th>

                                                                                                    <th>Name</th>

                                                                                            </tr>
                                                                                    </thead>

                                                                                    <tbody>
                                                                                        <tr ng-repeat="r in ctrl.ratings">

                                                                                            <td><span ng-bind="$index"></span></td>
                                                                                            
                                                                                            <td><span ng-bind="r.id"></span></td>

                                                                                            <td><span ng-bind="r.name"></span></td>


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
      <script src="<c:url value='/static/js/service/pull_element_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/pull_element_controller.js' />"></script>
      <script src="<c:url value='/static/js/service/country_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/country_controller.js' />"></script>
      <script src="<c:url value='/static/js/service/from_cities_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/from_cities_controller.js' />"></script>
      <script src="<c:url value='/static/js/service/hotel_rating_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/hotel_rating_controller.js' />"></script>
      <script src="<c:url value='/static/js/service/meal_type_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/meal_type_controller.js' />"></script>
      <script src="<c:url value='/static/js/service/currency_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/currency_controller.js' />"></script>
      <script src="<c:url value='/static/js/service/session_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/session_controller.js' />"></script>
      <script src="<c:url value='/static/js/service/request_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/request_controller.js' />"></script>
      <script src="<c:url value='/static/js/controller/time_controller.js' />"></script>
      <script src="<c:url value='/static/js/controller/date_controller.js' />"></script>
      <script src="<c:url value='/static/js/service/url_service.js' />"></script>
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
