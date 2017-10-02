'use strict';
 
App.controller('TourController', ['$scope', 'Tour', function($scope, Tour) {
          var self = this;
          self.tours=[];
          self.date_from;
          self.date_till;
          self.date_before;
          $scope.hide=true;
          self.tour={id:null,key:'',type:'',country:'',
          region:'',hotel_id:'',hotel:'',hotel_Rating:'',
          meal_Type:'',room_Type:'',adult_Amount:'',
          child_Amount:'',accomodation:'',duration:'',
          date_From:'',currency_id:'',currency_Symbol:'',
          prices:'',price_Old:'',price_Change_Percent:'',
          from_Cities:'',from_City_Gen:'',transport_Type:''};
      
          self.response={comeBackDelay:null,tourList:[],request:null};
          self.delay;
      
          self.request={country_id:null,from_city_id:null,
          hotel_rating:"3:78",night_from:"2",
          night_till:"7",meal_type_id:null};
               
          
          self.fetchByTimer=function(delay){
              var tick=0;
              document.getElementById("progressbar").style="width: "+tick+"%";
              document.getElementById("progress_label").innerHTML=tick+"%";
              var step = delay/20;
              $scope.hide=false;
              document.getElementById("progress_card").hidden=false;
              document.getElementById("search_button").disabled=true;
              var timerId = setInterval(function() {
              tick+=5;  
              document.getElementById("progressbar").style="width: "+tick+"%";
              document.getElementById("progress_label").innerHTML=tick+"%";
            }, step);

            setTimeout(function() {
              clearInterval(timerId);
              document.getElementById("progress_card").hidden=true;
              document.getElementById("search_button").disabled=false;
              self.fetchTourByRequest();
            }, delay);
          };
          
          self.setRequest=function(req){
              self.request=req;
          }
          
          self.setLoadDelay=function(delay){
              self.load_delay=delay;
          };
               
          self.fetchAllTours = function(){
              Tour.fetchAll()
                  .then(
                               function(d) {
                                   self.tours = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching tours');
                                }
                       );
          };
            
          self.fetchTour = function(id){
              Tour.fetch(id)
                  .then(
                               function(d) {
                                    self.tour = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching tour');
                                }
                       );
          };
          
          self.fetchTourByRequest = function(){
                      Tour.fetchByRequest(
                  self.request.country_id,
                  self.request.from_city_id,
                  self.request.hotel_rating,
                  self.request.night_from,
                  self.request.night_till,
                  self.request.meal_type_id).then(
                               function(d) {
                                    self.response = d;
                                    self.delay=self.response.comeBackDelay;
                                    if(self.delay>0){
                                    self.fetchByTimer(self.delay);    
                                    }
                                    self.tours=self.response.tourList;
                               },
                                function(errResponse){
                                    console.error('Error while fetching tours');
                                }
                      );
                  };
            
          self.deleteAllTours = function(){
              Tour.deleteAll()
                  .then(
                                   self.fetchAllTours(),
                                function(errResponse){
                                    console.error('Error while deleting tours');
                                }
                       );
          };
            
          self.deleteTour = function(id){
              Tour.delete(id)
                  .then(
                                   self.fetchAllTours(),
                                function(errResponse){
                                    console.error('Error while deleting tour');
                                }
                       );
          };
          
           self.deleteToursBefore = function(){
              if(self.date_before==null){
                  return;
              }
              var before = new Date(self.date_before).getTime()/1000;
              Tour.deleteBefore(before)
                  .then(
                                   self.fetchAllTours(),
                                function(errResponse){
                                    console.error('Error while deleting tours');
                                }
                       );
          };
          
           self.deleteToursBetween = function(from,till){
              Tour.deleteBetween(from,till)
                  .then(
                                   self.fetchAllTours(),
                                function(errResponse){
                                    console.error('Error while deleting tours');
                                }
                       );
          };
                  
            self.fetchAllTours();
      }]);
