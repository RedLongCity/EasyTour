App.factory('Session', ['$http', '$q', function($http, $q){
        
    var SERVER_URL = 'http://localhost:8084/EasyTour/json/session/';    
    var SERVER_URL_DATES='http://localhost:8084/EasyTour/json/getsessions';
    var SERVER_URL_JSON = 'http://localhost:8084/EasyTour/json/';    
        
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while fetching session');
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
                        console.error('Error while getting session');
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
    },
    
   deleteAll: function() {
            return $http.delete(SERVER_URL)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while fetching sessions');
                        return $q.reject(errResponse);
                    }
            );
        },
        
   delete: function(id){
            return $http.delete(SERVER_URL+"/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting session');
                        return $q.reject(errResponse);
                    }
            );
        },
     
     
   deleteBefore: function(date){
            return $http.delete(SERVER_URL_JSON+"/deletesessionsbeforedate/"+date)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting sessions');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    deleteBetween: function(from,till){
            return $http.delete(SERVER_URL_JSON+"/deletesessionsbetweendates?datefrom="+
                    from+"&datetill="+till)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting sessions');
                        return $q.reject(errResponse);
                    }
            );
        }
        
         
    };
 
}]);