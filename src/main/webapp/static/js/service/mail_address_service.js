App.factory('MailAddress', ['$http', '$q', 'UrlService', function ($http, $q, UrlService) {

        var SERVER_URL_JSON = UrlService.getServerUrlJson();

        return {

            getAll: function () {
                return $http.get(SERVER_URL_JSON + '/address')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching address');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            get: function (id) {
                return $http.get(SERVER_URL_JSON + "/address/" + id)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting address');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            save: function (address) {
                return $http.post(SERVER_URL_JSON + "/address/", address)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while posting address');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            update: function (address, id) {
                return $http.put(SERVER_URL_JSON + "/address/" + id, address)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while posting address');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteMailAddress: function (id) {
                return $http.delete(SERVER_URL_JSON + "/address/" + id)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while deleting address');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteAll: function () {
                return $http.delete(SERVER_URL_JSON + '/address')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while deleting addresses');
                                    return $q.reject(errResponse);
                                }
                        );
            }

        };

    }]);


