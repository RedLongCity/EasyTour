App.factory('City', ['$http', '$q','UrlService', function($http, $q,UrlService){
        
    var SERVER_URL_JSON = UrlService.getServerUrlJson();    
        
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL_JSON+"/city")
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while fetching cities');
                        return $q.reject(errResponse);
                    }
            );
        },
     
     
   fetch: function(id){
            return $http.get(SERVER_URL_JSON+"/city/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting cities');
                        return $q.reject(errResponse);
                    }
            );
        },
        
   delete: function(id){
            return $http.delete(SERVER_URL_JSON+"/city/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting cities');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    deleteAll: function() {
            return $http.delete(SERVER_URL_JSON+'/city')
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting cities');
                        return $q.reject(errResponse);
                    }
            );
        } 
         
    };
 
}]);

