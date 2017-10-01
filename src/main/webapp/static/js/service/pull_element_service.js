App.factory('PullElement', ['$http', '$q','UrlService', function($http, $q,UrlService){
        
    var SERVER_URL_JSON = UrlService.getServerUrlJson();    
        
    return {
         
    fetchAll: function() {
            return $http.get(SERVER_URL_JSON+'/element')
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
            return $http.get(SERVER_URL_JSON+"/element/"+id)
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
                    return $http.get(SERVER_URL_JSON+'/getelements'+
                            "?datefrom="+dateFrom+
                            "&datetill="+dateTill)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting elements');
                        return $q.reject(errResponse);
                    }
            );
    },
    
    deleteAll: function() {
            return $http.delete(SERVER_URL_JSON+'/element')
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting elements');
                        return $q.reject(errResponse);
                    }
            );
        },
     
     
   delete: function(id){
            return $http.delete(SERVER_URL_JSON+"/element/"+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting element');
                        return $q.reject(errResponse);
                    }
            );
        },
        
   deleteBefore: function(date){
            return $http.delete(SERVER_URL_JSON+"/deleteelementsbeforedate/"+date)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting elements');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    deleteBetween: function(from,till){
            return $http.delete(SERVER_URL_JSON+"/deleteelementsbetweendates?datefrom="+
                    from+"&datetill="+till)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while deleting elements');
                        return $q.reject(errResponse);
                    }
            );
        } 
         
    };
 
}]);