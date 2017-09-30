App.factory('SettingsService', ['$http', '$q', function($http, $q){

    var SERVER_URL = 'http://localhost:8084/EasyTour';
    var SERVER_URL_JSON = 'http://localhost:8084/EasyTour/json';    

    return{

    getShort_Delay: function(){
            return $http.get(SERVER_URL_JSON+"/getshortdelay")
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting shortdelay');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    getShort_Status: function(){
            return $http.get(SERVER_URL_JSON+"/getshortstatus")
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting short status');
                        return $q.reject(errResponse);
                    }
            );
        },
    getShort_Suspended: function(){
            return $http.get(SERVER_URL_JSON+"/getshortsuspended")
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting short suspended');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    getGlobal_Delay: function(){
            return $http.get(SERVER_URL_JSON+"/getglobaldelay")
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting global delay');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    getGlobal_Status: function(){
            return $http.get(SERVER_URL_JSON+"/getglobalstatus")
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting global status');
                        return $q.reject(errResponse);
                    }
            );
        },
        
    getGlobal_Suspended: function(){
            return $http.get(SERVER_URL_JSON+"/getglobalsuspended")
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting global suspended');
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
            return $http.get(SERVER_URL+"/setglobaldelay?delay="+delay)
        },
        
    setShortDelay: function(delay){
            return $http.get(SERVER_URL+"/setshortdelay?delay="+delay)
        } 
    }
}]);