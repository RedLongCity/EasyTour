'use strict';
 
App.controller('TimeController', ['$scope', function($scope) {
          var self = this;
          self.now;
          self.today;
          self.before_week;
          self.before_month;
          
          self.getNow = function(){
             self.now = Date.now(); 
             return self.now;
          }
          
          self.getToday = function(){
              var date = Date.now();
              self.today=date-24*3600*1000;
              return self.today;
          };
          
          self.getBeforeWeek = function(){
              var date = Date.now();
              self.before_week = date - 7*24*3600*1000;
              return self.before_week;
          };
          
          self.getBeforeMonth = function(){
              var date = Date.now();
              self.before_month = date - 30*24*3600*1000;
              return self.before_month;
          };
      }]);