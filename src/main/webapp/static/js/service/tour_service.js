App.factory('Tour', ['$http', '$q', function($http, $q){
        
    var SERVER_URL_JSON = 'http://localhost:8084/EasyTour/json/tour/';     
    var SERVER_URL = 'http://localhost:8084/EasyTour/json/';    
  
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL_JSON)
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
            return $http.get(SERVER_URL_JSON+"/"+id)
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
        },
        
    deleteAll: function() {
            return $http.delete(SERVER_URL_JSON)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting tours');
                        return $q.reject(errResponse);
                    }
            );
        },
     
     
   delete: function(id){
            return $http.delete(SERVER_URL_JSON+"/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting tour');
                        return $q.reject(errResponse);
                    }
            );
        },
        
   deleteBefore: function(date){
            return $http.delete(SERVER_URL+"/deletetoursbeforedate/"+date)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting tours');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    deleteBetween: function(from,till){
            return $http.delete(SERVER_URL+"/deletetoursbetweendates?datefrom="+
                    from+"&datetill="+till)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting tours');
                        return $q.reject(errResponse);
                    }
            );
        } 
            
    }
 
}]);