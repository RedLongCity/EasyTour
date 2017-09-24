'use strict';
 
App.controller('CurrencyController', ['$scope', 'Currency', function($scope, Currency) {
          var self = this;
          self.currencies=[];
          self.currency={id:null,name:''};     
               
          self.fetchAllCurrencies = function(){
              Currency.fetchAll()
                  .then(
                               function(d) {
                                    self.currencies = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching currencys');
                                }
                       );
          };
            
          self.fetchCurrency = function(id){
              Currency.fetch(id)
                  .then(
                               function(d) {
                                    self.currency = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching currency');
                                }
                       );
          };
            self.fetchAllCurrencies();
      }]);