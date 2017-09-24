'use strict';
 
App.controller('MealTypeController', ['$scope', 'MealType', function($scope, MealType) {
          var self = this;
          self.mealtypes=[];
          self.mealtype={id:null,name:'',name_full:''};     
               
          self.fetchAllMealTypes = function(){
              MealType.fetchAll()
                  .then(
                               function(d) {
                                    self.mealtypes = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching mealtypes');
                                }
                       );
          };
            
          self.fetchMealType = function(id){
              MealType.fetch(id)
                  .then(
                               function(d) {
                                    self.mealtype = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching mealtype');
                                }
                       );
          };
            self.fetchAllMealTypes();
      }]);
