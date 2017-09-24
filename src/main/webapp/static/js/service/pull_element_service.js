//App.factory('PullElement', ['$resource', function ($resource) {
//    return $resource('http://localhost:8084/EasyTour/json/element/:id');
//}]);

App.factory('PullElement', ['$http', '$q', function($http, $q){
        
    var SERVER_URL = 'http://localhost:8084/EasyTour/json/element/';    
        
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while fetching users');
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
                        console.error('Error while getting user');
                        return $q.reject(errResponse);
                    }
            );
        }
         
    };
 
}]);