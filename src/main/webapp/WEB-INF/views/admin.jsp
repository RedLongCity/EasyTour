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
        
        <div class="card mb-4" ng-controller="PullElementController as ctrl">
    <div class="card-block">
            <h3 class="card-title">Recent Orders</h3>

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
        
        
    
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/pull_element_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/pull_element_controller.js' />"></script>
</body>
</html>
