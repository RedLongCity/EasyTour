App.factory('Tour', ['$http', '$q', function($http, $q){
        
    var SERVER_URL = 'http://localhost:8084/EasyTour/json/tour/';    
        
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while fetching tours');
                        return $q.reject(errResponse);
                    }
            );
        },
     
     
   fetch: function(id){
            return $http.get(SERVER_URL+"/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting tour');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    fetchByRequest:function(country_id,from_city_id,hotel_rating,night_from,night_till,meal_type_id){
        return $http.get('http://localhost:8084/EasyTour/json/gettours'+
                '?country='+country_id+
                '&from_city='+from_city_id+
                '&hotel_rating='+hotel_rating+
                '&night_from='+night_from+
                '&night_till='+night_till+
                '&meal_type='+meal_type_id).then(
                function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting tour');
                        return $q.reject(errResponse);
                    }
                );
        }        
            
    }
 
}]);