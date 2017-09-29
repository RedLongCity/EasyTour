'use strict';
 
App.controller('CountryController', ['$scope', 'Country', function($scope, Country) {
          var self = this;
          self.countries=[];
          self.country={id:null,name:'',from_CitiesSet:''};     
               
          self.fetchAllCountries = function(){
              Country.fetchAll()
                  .then(
                               function(d) {
                                    self.countries = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching countries');
                                }
                       );
          };
            
          self.fetchCountry = function(id){
              Country.fetch(id)
                  .then(
                               function(d) {
                                    self.country = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching country');
                                }
                       );
          };
          
          self.deleteAllCountries = function(){
              Country.deleteAll()
                  .then(
                            self.fetchAllCountries(),
                                function(errResponse){
                                    console.error('Error while deleting countries');
                                }
                       );
          };
            
          self.deleteCountry = function(id){
              Country.deleteCountry(id)
                  .then(
                            self.fetchAllCountries(),
                                function(errResponse){
                                    console.error('Error while deleting country');
                                }
                       );
          };
            self.fetchAllCountries();
      }]);


