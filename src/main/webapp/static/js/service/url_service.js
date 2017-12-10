App.factory('UrlService', [ function(){

var SERVER_URL="http://rest-easytour.193b.starter-ca-central-1.openshiftapps.com";
//var SERVER_URL="http://localhost:8084/EasyTour";
var SERVER_URL_JSON=SERVER_URL+"/json";//http://localhost:8084/EasyTour/json/

return{

getServerUrl: function(){
    return SERVER_URL;
},

getServerUrlJson:function(){
    return SERVER_URL_JSON;
}

}
}]);

