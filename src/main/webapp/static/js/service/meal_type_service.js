App.factory('MealType', ['$http', '$q','UrlService', function($http, $q,UrlService){
        
    var SERVER_URL_JSON = UrlService.getServerUrlJson();    
        
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL_JSON+"/mealtype")
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while fetching mealtype');
                        return $q.reject(errResponse);
                    }
            );
        },
     
     
   fetch: function(id){
            return $http.get(SERVER_URL_JSON+"/mealtype/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting mealtype');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    deleteAll: function() {
            return $http.delete(SERVER_URL_JSON+"/mealtype")
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting mealtypes');
                        return $q.reject(errResponse);
                    }
            );
        },
     
     
   delete: function(id){
            return $http.delete(SERVER_URL_JSON+"/mealtype/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting mealtype');
                        return $q.reject(errResponse);
                    }
            );
        }
         
    };
 
}]);