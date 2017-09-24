'use strict';
 
App.controller('PullElementController', ['$scope', 'PullElement', function($scope, PullElement) {
          var self = this;
          self.elements=[];
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
            self.fetchAllElements();
      }]);
