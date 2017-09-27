App.factory('PullElement', ['$http', '$q', function($http, $q){
        
    var SERVER_URL = 'http://localhost:8084/EasyTour/json/element/';
    var SERVER_URL_DATES='http://localhost:8084/EasyTour/json/getelements';
        
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while fetching element');
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
                        console.error('Error while getting element');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    fetchByDates: function(dateFrom,dateTill){
                    return $http.get(SERVER_URL_DATES+
                            "?datefrom="+dateFrom+
                            "&datetill="+dateTill)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting element');
                        return $q.reject(errResponse);
                    }
            );
    }    
         
    };
 
}]);