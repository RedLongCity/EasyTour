<%-- 
    Document   : helloworld
    Created on : 17.09.2017, 22:29:59
    Author     : redlongcity
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin</title>
</head>
<body ng-app="myApp" class="ng-cloak">
    
        <label>Name :</label><input type="text" ng-model="name" placeholder="Enter your name"/>
        <h1>Hello <span ng-bind="name"></span></h1> 
        
                                   <!--Elements-->
        
        <div class="card mb-4" ng-controller="PullElementController as ctrl">
    <div class="card-block">
            <h3 class="card-title">Pull Elements</h3>

            <div class="dropdown card-title-btn-container">
                    <button class="btn btn-sm btn-subtle" type="button"><em class="fa fa-list-ul"></em> View All</button>

                    <button class="btn btn-sm btn-subtle dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><em class="fa fa-cog"></em></button>

                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton"><a class="dropdown-item" href="#"><em class="fa fa-search mr-1"></em> More info</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-thumb-tack mr-1"></em> Pin Window</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-remove mr-1"></em> Close Window</a></div>
            </div>

            <div class="table-responsive">
                    <table class="table table-striped">
                            <thead>
                                    <tr>
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

                                    <td><span ng-bind="e.id"></span></td>

                                    <td><span ng-bind="e.request_pull_DateTime"></span></td>

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
            
            <label>Id :</label><input type="number" ng-model="id" placeholder="Enter id"/>
        <button type="button" ng-click="ctrl.fetchElement(id)" class="btn btn-success custom-width">Edit</button>         
        <h4>Element: {{ctrl.element}}</h4> 
</div>
        
                                         <!--Countries-->
        
        <div class="card mb-4" ng-controller="CountryController as ctrl">
    <div class="card-block">
            <h3 class="card-title">Countries</h3>

            <div class="dropdown card-title-btn-container">
                    <button class="btn btn-sm btn-subtle" type="button"><em class="fa fa-list-ul"></em> View All</button>

                    <button class="btn btn-sm btn-subtle dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><em class="fa fa-cog"></em></button>

                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton"><a class="dropdown-item" href="#"><em class="fa fa-search mr-1"></em> More info</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-thumb-tack mr-1"></em> Pin Window</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-remove mr-1"></em> Close Window</a></div>
            </div>

            <div class="table-responsive">
                    <table class="table table-striped">
                            <thead>
                                    <tr>
                                            <th>ID.</th>

                                            <th>Name</th>

                                    </tr>
                            </thead>

                            <tbody>
                                <tr ng-repeat="c in ctrl.countries">

                                    <td><span ng-bind="c.id"></span></td>

                                    <td><span ng-bind="c.name"></span></td>


                                    </tr>
                            </tbody>
                    </table>
            </div>
    </div>
            
            <label>Id :</label><input type="number" ng-model="id" placeholder="Enter id"/>
        <button type="button" ng-click="ctrl.fetchCountry(id)" class="btn btn-success custom-width">Edit</button>         
        <h4>Country: {{ctrl.country}}</h4> 
</div>        
        
                                          <!--Cities-->
        
        <div class="card mb-4" ng-controller="CitiesController as ctrl">
    <div class="card-block">
            <h3 class="card-title">Cites</h3>

            <div class="dropdown card-title-btn-container">
                    <button class="btn btn-sm btn-subtle" type="button"><em class="fa fa-list-ul"></em> View All</button>

                    <button class="btn btn-sm btn-subtle dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><em class="fa fa-cog"></em></button>

                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton"><a class="dropdown-item" href="#"><em class="fa fa-search mr-1"></em> More info</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-thumb-tack mr-1"></em> Pin Window</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-remove mr-1"></em> Close Window</a></div>
            </div>

            <div class="table-responsive">
                    <table class="table table-striped">
                            <thead>
                                    <tr>
                                            <th>ID.</th>

                                            <th>Name</th>

                                    </tr>
                            </thead>

                            <tbody>
                                <tr ng-repeat="c in ctrl.cities">

                                    <td><span ng-bind="c.id"></span></td>

                                    <td><span ng-bind="c.name"></span></td>


                                    </tr>
                            </tbody>
                    </table>
            </div>
    </div>
            
            <label>Id :</label><input type="number" ng-model="id" placeholder="Enter id"/>
        <button type="button" ng-click="ctrl.fetchCity(id)" class="btn btn-success custom-width">Edit</button>         
        <h4>City: {{ctrl.city}}</h4> 
</div> 
                                          
                                        <!--Hotel Ratings-->
        
        <div class="card mb-4" ng-controller="HotelRatingController as ctrl">
    <div class="card-block">
            <h3 class="card-title">Hotel Ratings</h3>

            <div class="dropdown card-title-btn-container">
                    <button class="btn btn-sm btn-subtle" type="button"><em class="fa fa-list-ul"></em> View All</button>

                    <button class="btn btn-sm btn-subtle dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><em class="fa fa-cog"></em></button>

                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton"><a class="dropdown-item" href="#"><em class="fa fa-search mr-1"></em> More info</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-thumb-tack mr-1"></em> Pin Window</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-remove mr-1"></em> Close Window</a></div>
            </div>

            <div class="table-responsive">
                    <table class="table table-striped">
                            <thead>
                                    <tr>
                                            <th>ID.</th>

                                            <th>Name</th>

                                    </tr>
                            </thead>

                            <tbody>
                                <tr ng-repeat="r in ctrl.ratings">

                                    <td><span ng-bind="r.id"></span></td>

                                    <td><span ng-bind="r.name"></span></td>


                                    </tr>
                            </tbody>
                    </table>
            </div>
    </div>
            
            <label>Id :</label><input type="number" ng-model="id" placeholder="Enter id"/>
        <button type="button" ng-click="ctrl.fetchRating(id)" class="btn btn-success custom-width">Edit</button>         
        <h4>Hotel Rating: {{ctrl.rating}}</h4> 
</div> 
        
                                            <!--Meal Types-->
        
        <div class="card mb-4" ng-controller="MealTypeController as ctrl">
    <div class="card-block">
            <h3 class="card-title">Meal Types</h3>

            <div class="dropdown card-title-btn-container">
                    <button class="btn btn-sm btn-subtle" type="button"><em class="fa fa-list-ul"></em> View All</button>

                    <button class="btn btn-sm btn-subtle dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><em class="fa fa-cog"></em></button>

                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton"><a class="dropdown-item" href="#"><em class="fa fa-search mr-1"></em> More info</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-thumb-tack mr-1"></em> Pin Window</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-remove mr-1"></em> Close Window</a></div>
            </div>

            <div class="table-responsive">
                    <table class="table table-striped">
                            <thead>
                                    <tr>
                                            <th>ID.</th>

                                            <th>Name</th>

                                            <th>Full Name</th>
                                    </tr>
                            </thead>

                            <tbody>
                                <tr ng-repeat="r in ctrl.mealtypes">

                                    <td><span ng-bind="r.id"></span></td>

                                    <td><span ng-bind="r.name"></span></td>

                                    <td><span ng-bind="r.name_full"></span></td>
                                    </tr>
                            </tbody>
                    </table>
            </div>
    </div>
            
            <label>Id :</label><input type="number" ng-model="id" placeholder="Enter id"/>
        <button type="button" ng-click="ctrl.fetchMealType(id)" class="btn btn-success custom-width">Edit</button>         
        <h4>Meal Type: {{ctrl.mealtype}}</h4> 
</div> 
                                            
                                        <!--Currencies-->
        
        <div class="card mb-4" ng-controller="CurrencyController as ctrl">
    <div class="card-block">
            <h3 class="card-title">Currencies</h3>

            <div class="dropdown card-title-btn-container">
                    <button class="btn btn-sm btn-subtle" type="button"><em class="fa fa-list-ul"></em> View All</button>

                    <button class="btn btn-sm btn-subtle dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><em class="fa fa-cog"></em></button>

                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton"><a class="dropdown-item" href="#"><em class="fa fa-search mr-1"></em> More info</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-thumb-tack mr-1"></em> Pin Window</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-remove mr-1"></em> Close Window</a></div>
            </div>

            <div class="table-responsive">
                    <table class="table table-striped">
                            <thead>
                                    <tr>
                                            <th>ID.</th>

                                            <th>Name</th>

                                    </tr>
                            </thead>

                            <tbody>
                                <tr ng-repeat="c in ctrl.currencies">

                                    <td><span ng-bind="c.id"></span></td>

                                    <td><span ng-bind="c.name"></span></td>

                                    </tr>
                            </tbody>
                    </table>
            </div>
    </div>
            
            <label>Id :</label><input type="number" ng-model="id" placeholder="Enter id"/>
        <button type="button" ng-click="ctrl.fetchCurrency(id)" class="btn btn-success custom-width">Edit</button>         
        <h4>Currency: {{ctrl.currency}}</h4> 
</div> 
                                        
                                    <!--Sessions-->
        
        <div class="card mb-4" ng-controller="SessionController as ctrl">
    <div class="card-block">
            <h3 class="card-title">Sessions</h3>

            <div class="dropdown card-title-btn-container">
                    <button class="btn btn-sm btn-subtle" type="button"><em class="fa fa-list-ul"></em> View All</button>

                    <button class="btn btn-sm btn-subtle dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><em class="fa fa-cog"></em></button>

                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton"><a class="dropdown-item" href="#"><em class="fa fa-search mr-1"></em> More info</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-thumb-tack mr-1"></em> Pin Window</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-remove mr-1"></em> Close Window</a></div>
            </div>

            <div class="table-responsive">
                    <table class="table table-striped">
                            <thead>
                                    <tr>
                                            <th>ID.</th>

                                            <th>Session Time</th>
                                            
                                    </tr>
                            </thead>

                            <tbody>
                                <tr ng-repeat="s in ctrl.sessions">

                                    <td><span ng-bind="s.id"></span></td>

                                    <td><span ng-bind="s.sessionTime"></span></td>

                                    </tr>
                            </tbody>
                    </table>
            </div>
    </div>
            
            <label>Id :</label><input type="number" ng-model="id" placeholder="Enter id"/>
        <button type="button" ng-click="ctrl.fetchSession(id)" class="btn btn-success custom-width">Edit</button>         
        <h4>Session: {{ctrl.session}}</h4> 
</div> 
                                            
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
</body>
</html>
