'use strict';
 
App.controller('SessionController', ['$scope', 'Session', function($scope, Session) {
          var self = this;
          self.sessions=[];
          self.session={id:null,sessionTime:'',requestPullElementSet:''};     
               
          self.fetchAllSessions = function(){
              Session.fetchAll()
                  .then(
                               function(d) {
                                    self.sessions = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching sessions');
                                }
                       );
          };
            
          self.fetchSession = function(id){
              Session.fetch(id)
                  .then(
                               function(d) {
                                    self.session = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching session');
                                }
                       );
          };
            self.fetchAllSessions();
      }]);
