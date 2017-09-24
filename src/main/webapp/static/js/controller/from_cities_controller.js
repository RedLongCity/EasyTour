'use strict';
 
App.controller('CitiesController', ['$scope', 'City', function($scope, City) {
          var self = this;
          self.cities=[];
          self.city={id:null,name:'',countrySet:''};     
               
          self.fetchAllCities = function(){
              City.fetchAll()
                  .then(
                               function(d) {
                                    self.cities = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching cities');
                                }
                       );
          };
            
          self.fetchCity = function(id){
              City.fetch(id)
                  .then(
                               function(d) {
                                    self.city = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching cities');
                                }
                       );
          };
            self.fetchAllCities();
      }]);


