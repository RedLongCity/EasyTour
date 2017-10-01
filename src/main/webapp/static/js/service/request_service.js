App.factory('Request', ['$http', '$q','UrlService', function($http, $q,UrlService){
        
      var SERVER_URL_JSON =  UrlService.getServerUrlJson();
      
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL_JSON+"/request")
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while fetching request');
                        return $q.reject(errResponse);
                    }
            );
        },
     
     
   fetch: function(id){
            return $http.get(SERVER_URL_JSON+"/request/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting request');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    deleteAll: function() {
            return $http.delete(SERVER_URL_JSON+"/request")
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting requests');
                        return $q.reject(errResponse);
                    }
            );
        },
     
     
   delete: function(id){
            return $http.delete(SERVER_URL_JSON+"/request/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting request');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    };
 
}]);

