App.factory('SettingsService', ['$http', '$q', function($http, $q){

    var SERVER_URL = 'http://localhost:8084/EasyTour';
    var SERVER_URL_DELAYS = 'http://localhost:8084/EasyTour/json/getdelays/';    
    var SERVER_URL_STATUSES = 'http://localhost:8084/EasyTour/json/getstatuses/'; 

    return{

    getDelays: function(){
            return $http.get(SERVER_URL_DELAYS)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting delays');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    getStatuses: function(){
            return $http.get(SERVER_URL_STATUSES)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting statuses');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    stopShort: function(){
            return $http.get(SERVER_URL+"/stopshort")
        }, 
        
    resumeShort: function(){
            return $http.get(SERVER_URL+"/resumeshort")
        }, 
        
    stopGlobal: function(){
            return $http.get(SERVER_URL+"/stopglobal")
        }, 
        
    resumeGlobal: function(){
            return $http.get(SERVER_URL+"/resumeglobal")
        },
        
    setGlobalDelay: function(delay){
            return $http.get(SERVER_URL+"/setglobaldelay+?delay="+delay)
        },
        
    setShortDelay: function(delay){
            return $http.get(SERVER_URL+"/setshortdelay+?delay="+delay)
        } 
    }
}]);