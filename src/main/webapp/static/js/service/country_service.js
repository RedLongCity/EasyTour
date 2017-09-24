App.factory('CountryService', ['$resource', function ($resource) {
    return $resource('http://localhost:8084/EasyTour/json/country/:id',
    {id: '@id'});
}]);


