'use strict';
 
App.controller('ConstantsController', ['$scope',function($scope) {
          var self = this;
          self.but_country='Country';
          self.but_city='City';
          self.but_duration='Duration';
          self.but_mealtype='Meal Type';
          self.but_rating='Hotel Rating';
          self.but_date='Date From';
          
          self.request={country_id:null,from_city_id:null,
          hotel_rating:"3:78",night_from:"2",
          night_till:"7",meal_type_id:null};
          
          self.country_id="null";
          self.city_id="null";
          self.duration_from="1";
          self.duration_till="1";
          self.meal_type_id="null";
          self.hotel_rating="2";
          
          self.date="mm/dd/yyyy";
          
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
          self.country_id="null";
          self.city_id="null";
          self.duration_from="1";
          self.duration_till="1";
          self.meal_type_id="null";
          self.hotel_rating="2";
          };
          
          self.getRequest=function(){
              self.request.country_id=self.country_id;
              self.request.from_city_id=self.city_id;
              self.request.hotel_rating=
                      self.ratings_array_machine[self.hotel_rating];
              self.request.night_from=
                      self.duration_array_from[self.duration_from];
              self.request.night_till=
                      self.duration_array_till[self.duration_till];
              self.request.meal_type_id=self.meal_type_id;
              return self.request;
          };
          
      }]);


