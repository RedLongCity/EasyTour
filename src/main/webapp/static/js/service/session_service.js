App.factory('Session', ['$http', '$q','UrlService', function($http, $q,UrlService){
        
    var SERVER_URL_JSON = UrlService.getServerUrlJson();    
        
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL_JSON+'/session')
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
            return $http.get(SERVER_URL_JSON+"/session/"+id)
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
                    return $http.get(SERVER_URL_JSON+"getsessions"+
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
            return $http.delete(SERVER_URL_JSON+"/session")
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
            return $http.delete(SERVER_URL_JSON+"/session/"+id)
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