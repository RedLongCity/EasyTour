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
          
        self.deleteAllCurrencies = function(){
              Currency.deleteAll()
                  .then(
                                    self.fetchAllCurrencies(),
                                function(errResponse){
                                    console.error('Error while deleting currencies');
                                }
                       );
          };
            
      self.deleteCurrency = function(id){
              Currency.delete(id)
                  .then(
                                    self.fetchAllCurrencies(),
                                function(errResponse){
                                    console.error('Error while deleting currency');
                                }
                       );
          };
          
            self.fetchAllCurrencies();
      }]);
