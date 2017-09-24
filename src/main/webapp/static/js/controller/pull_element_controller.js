'use strict';
 
App.controller('PullElementController', ['$scope', 'PullElementService', function($scope, PullElementService) {
          var self = this;
          self.elements=[];
               
          self.fetchAllElements = function(){
              self.elements = PullElementService.query();
          };
            
            self.fetchAllElements();
      }]);
