'use strict';
 
App.controller('SessionController', ['$scope', 'Session', function($scope, Session) {
          var self = this;
          self.sessions=[];
          self.date_from;
          self.date_till;
          self.date_before;
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
            if(date==null){
                return;
            }else{
              var date_before=new Date(date).getDate();
            }
              Session.deleteBefore(date_before).then(
                      self.fetchAllSessions(),
                      function(errResponse){
                                    console.error('Error while deleting sessions');
                        }
                      );
          };
          
        self.deleteSessionsBetween=function(from,till){
                if(from==null||till==null){
                return;
            }else{
                var date_from=new Date(from).getDate();
                var date_till=new Date(till).getDate();
            }
              Session.deleteBetween(date_from,date_till).then(
                      self.fetchAllSessions(),
                      function(errResponse){
                                    console.error('Error while deleting sessions');
                        }
                      );
          };
          
            self.fetchAllSessions();
      }]);
