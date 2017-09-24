App.factory('PullElementService', ['$resource', function ($resource) {
    return $resource('http://localhost:8084/EasyTour/json/element/:id',
    {id: '@id'});
}]);

