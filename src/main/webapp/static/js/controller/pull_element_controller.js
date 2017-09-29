'use strict';
 
App.controller('PullElementController', ['$scope', 'PullElement', function($scope, PullElement) {
          var self = this;
          self.elements=[];
          self.date_from;
          self.date_till;
          self.date_before;
          self.element={id:null,request_pull_DateTime:'',requestid:'',done:'',
          priority:'',byHuman:'',updateSessionid:''};     
               
          self.fetchAllElements = function(){
              PullElement.fetchAll()
                  .then(
                               function(d) {
                                    self.elements = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching elements');
                                }
                       );
          };
            
          self.fetchElement = function(id){
              PullElement.fetch(id)
                  .then(
                               function(d) {
                                    self.element = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching element');
                                }
                       );
          };
          
          self.fetchElementsByDates = function(datefrom,datetill){
             PullElement.fetchByDates(datefrom,datetill).then(
                               function(d) {
                                    self.elements = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching element');
                                }
                      ); 
          };
          
          self.deleteElement = function(id){
              PullElement.delete(id)
                  .then(
                                    self.fetchAllElements(),
                                function(errResponse){
                                    console.error('Error while deleting element');
                                }
                       );
          };
          
          self.deleteElements = function(){
              PullElement.deleteAll()
                  .then(
                                    self.fetchAllElements(),
                                function(errResponse){
                                    console.error('Error while deleting elements');
                                }
                       );
          };
          
          self.deleteElementsBefore = function(date){
              PullElement.deleteBefore(date)
                  .then(
                                    self.fetchAllElements(),
                                function(errResponse){
                                    console.error('Error while deleting elements');
                                }
                       );
          };
          
        self.deleteElementsBetween = function(from,till){
              PullElement.deleteBetween(from,till)
                  .then(
                                    self.fetchAllElements(),
                                function(errResponse){
                                    console.error('Error while deleting elements');
                                }
                       );
          };
          
            self.fetchAllElements();
      }]);
