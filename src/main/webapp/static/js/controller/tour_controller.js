'use strict';
 
App.controller('TourController', ['$scope', 'Tour', function($scope, Tour) {
          var self = this;
          self.tours=[];
          self.tour={id:null,key:'',type:'',country:'',
          region:'',hotel_id:'',hotel:'',hotel_Rating:'',
          meal_Type:'',room_Type:'',adult_Amount:'',
          child_Amount:'',accomodation:'',duration:'',
          date_From:'',currency_id:'',currency_Symbol:'',
          prices:'',price_Old:'',price_Change_Percent:'',
          from_Cities:'',from_City_Gen:'',transport_Type:''};     
               
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
          
          self.fetchTourByRequest = function(
                  country_id,
                  from_city_id,
                  hotel_rating,
                  night_from,
                  night_till,
                  meal_type_id){
                      Tour.fetchByRequest(
                  country_id,
                  from_city_id,
                  hotel_rating,
                  night_from,
                  night_till,
                  meal_type_id).then(
                               function(d) {
                                    self.tour = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching tours');
                                }
                      );
                  };
            //self.fetchAllTours();
      }]);
