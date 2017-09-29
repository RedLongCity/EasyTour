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
          
        self.fetchElementsByDates = function(datefrom,datetill){
             Session.fetchByDates(datefrom,datetill).then(
                               function(d) {
                                    self.sessions = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching sessions');
                                }
                      ); 
          };
          
          self.deleteAllSessions=function(){
              Session.deleteAll().then(
                      self.fetchAllSessions(),
                      function(errResponse){
                                    console.error('Error while deleting sessions');
                        }
                      );
          };
          
         self.deleteSession=function(id){
              Session.delete(id).then(
                      self.fetchAllSessions(),
                      function(errResponse){
                                    console.error('Error while deleting session');
                        }
                      );
          };
          
        self.deleteSessionsBefore=function(date){
              Session.deleteBefore(date).then(
                      self.fetchAllSessions(),
                      function(errResponse){
                                    console.error('Error while deleting sessions');
                        }
                      );
          };
          
        self.deleteSessionsBetween=function(from,till){
              Session.deleteBetween(from,till).then(
                      self.fetchAllSessions(),
                      function(errResponse){
                                    console.error('Error while deleting sessions');
                        }
                      );
          };
          
            self.fetchAllSessions();
      }]);
