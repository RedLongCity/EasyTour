'use strict';
 
App.controller('ConstantsController', ['$scope',function($scope) {
          var self = this;
          self.but_country='Country';
          self.but_city='City';
          self.but_duration='Duration';
          self.but_mealtype='Meal Type';
          self.but_rating='Hotel Rating';
          self.but_date='Date From';
          
          self.country_id="null";
          self.city_id="null";
          self.duration_from="5";
          self.duration_till="7";
          self.meal_type_id="null";
          self.hotel_rating="3:78";
          self.date="";
          
          self.ratings_array_face=['Rating: 2-3','Rating: 3-4','Rating: 4-5'];
          self.ratings_array_machine=['7:3','3:4','4:78'];
          
          self.duration_array_face=["From: 1 Till: 5","From:5 Till: 7","From:7 Till:21"];
          self.duration_array_from=["1","5","7"];
          self.duration_array_till=["5","7","21"];
          
          self.refresh=function(){
          self.but_country='Country';
          self.but_city='City';
          self.but_duration='Duration';
          self.but_mealtype='Meal Type';
          self.but_rating='Hotel Rating';
          self.but_date='Date From'; 
          };
      }]);


