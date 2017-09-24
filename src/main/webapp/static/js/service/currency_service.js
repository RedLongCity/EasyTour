App.factory('Currency', ['$http', '$q', function($http, $q){
        
    var SERVER_URL = 'http://localhost:8084/EasyTour/json/currency/';    
        
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while fetching currencys');
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
                        console.error('Error while getting currency');
                        return $q.reject(errResponse);
                    }
            );
        }
         
    };
 
}]);