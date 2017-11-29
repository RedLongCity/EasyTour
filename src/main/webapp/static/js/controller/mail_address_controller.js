'use strict';

App.controller('MailAddressController', ['$scope', 'MailAddress', function ($scope, MailAddress) {
        var self = this;
        self.addresses = [];
        self.address = {id: null, name: '', emailAddress: ''};
        
        self.getAllMailAddresses = function () {
            MailAddress.getAll()
                    .then(
                            function (d) {
                                self.addresses = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching addresses');
                            }
                    );
        };

        self.getMailAddress = function (id) {
            MailAddress.get(id)
                    .then(
                            function (d) {
                                self.address = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching address');
                            }
                    );
        };

        self.saveMailAddress = function (address) {
            MailAddress.save(address)
                    .then(
                            self.getAllMailAddresses(),
                            function (errResponse) {
                                console.error('Error while save address');
                            }
                    );
        };

        self.updateMailAddress = function (address, id) {
            MailAddress.update(address, id)
                    .then(
                            self.getAllMailAddresses(),
                            function (errResponse) {
                                console.error('Error while update address');
                            }
                    );
        };

        self.deleteAllMailAddresses = function () {
            MailAddress.deleteAll()
                    .then(
                            self.getAllMailAddresses(),
                            function (errResponse) {
                                console.error('Error while deleting addresses');
                            }
                    );
        };

        self.deleteMailAddress = function (id) {
            MailAddress.deleteMailAddress(id)
                    .then(
                            self.getAllMailAddresses(),
                            function (errResponse) {
                                console.error('Error while deleting address');
                            }
                    );
        };
        self.getAllMailAddresses();
    }]);