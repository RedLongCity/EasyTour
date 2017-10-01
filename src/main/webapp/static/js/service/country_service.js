App.factory('Country', ['$http', '$q','UrlService', function($http, $q,UrlService){
        
    var SERVER_URL_JSON = UrlService.getServerUrlJson();    
        
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL_JSON+'/country')
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while fetching country');
                        return $q.reject(errResponse);
                    }
            );
        },
     
     
   fetch: function(id){
            return $http.get(SERVER_URL_JSON+"/country/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting country');
                        return $q.reject(errResponse);
                    }
            );
        },
   deleteCountry: function(id){
            return $http.delete(SERVER_URL_JSON+"/country/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting country');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    deleteAll: function() {
            return $http.delete(SERVER_URL_JSON+'/country')
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting countries');
                        return $q.reject(errResponse);
                    }
            );
        }
         
    };
 
}]);


