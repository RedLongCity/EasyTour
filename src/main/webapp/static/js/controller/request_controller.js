'use strict';
 
App.controller('RequestController', ['$scope', 'Request', function($scope, Request) {
          var self = this;
          self.requests=[];
          self.request={id:null,country:'',from_Cities:'',hotel_Rating:'',
          night_From:'',night_Till:'',meal_Type:''};     
               
          self.fetchAllRequests = function(){
              Request.fetchAll()
                  .then(
                               function(d) {
                                    self.requests = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching requests');
                                }
                       );
          };
            
          self.fetchRequest= function(id){
              Request.fetch(id)
                  .then(
                               function(d) {
                                    self.request = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching request');
                                }
                       );
          };
            self.fetchAllRequests();
      }]);
