'use strict';
 
App.controller('HotelRatingController', ['$scope', 'HotelRating', function($scope, HotelRating) {
          var self = this;
          self.ratings=[];
          self.rating={id:null,name:''};     
               
          self.fetchAllRatings = function(){
              HotelRating.fetchAll()
                  .then(
                               function(d) {
                                    self.ratings = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching hotelratings');
                                }
                       );
          };
            
          self.fetchRating = function(id){
              HotelRating.fetch(id)
                  .then(
                               function(d) {
                                    self.rating = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching hotelrating');
                                }
                       );
          };
          
        self.deleteAllRatings = function(){
              HotelRating.deleteAll()
                  .then(
                                    self.fetchAllRatings(),
                                function(errResponse){
                                    console.error('Error while deleting hotelratings');
                                }
                       );
          };
            
          self.deleteRating = function(id){
              HotelRating.delete(id)
                  .then(
                                    self.fetchAllRatings(),
                                function(errResponse){
                                    console.error('Error while deleting hotelrating');
                                }
                       );
          };
          
            self.fetchAllRatings();
      }]);
