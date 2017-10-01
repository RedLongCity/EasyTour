App.factory('Tour', ['$http', '$q','UrlService', function($http, $q,UrlService){
        
    var SERVER_URL_JSON = UrlService.getServerUrlJson();
    var SERVER_URL = UrlService.getServerUrl();    
  
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL_JSON+'/tour')
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
            return $http.get(SERVER_URL_JSON+"/tour/"+id)
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
        return $http.get(SERVER_URL_JSON+'/gettours'+
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
            return $http.delete(SERVER_URL_JSON+"/tour")
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
            return $http.delete(SERVER_URL_JSON+"/tour/"+id)
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
            return $http.delete(SERVER_URL_JSON+"/deletetoursbeforedate/"+date)
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
            return $http.delete(SERVER_URL_JSON+"/deletetoursbetweendates?datefrom="+
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