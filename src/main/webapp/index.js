var advApp = angular.module('advApp', [ "highcharts-ng", 'ngRoute',
    'ngResource', 'ngTable', 'ngCookies', 'ui.bootstrap', 'angularjs-datetime-picker']);

/*
 * Customer services
 */
advApp.factory('Tokens', function ($resource) {
    return $resource('rest/tokens');
});

advApp.factory('Customer', function ($resource) {
    return $resource('rest/customers/:customerId');
});

advApp.factory('CustomerDevices', function ($resource) {
    return $resource('rest/customers/:customerId/devices/:deviceId');
});

advApp.factory('DeviceStates', function ($resource) {
    return $resource('rest/customers/:customerId/devices/:deviceId/devicestates');
});

//advApp.factory('DeviceHumidities', function($resource){
//    return $resource('rest/customers/:customerId/devices/:deviceId/humidities');
//});

/*
 * Administration services
 */
advApp.factory('Customers', function ($resource) {
    return $resource('rest/administration/customers/:customerId');
});

advApp.factory('Devices', function ($resource) {
    return $resource('rest/administration/devices/:deviceId');
});

advApp.factory('WorkingModes', function ($resource) {
    return $resource('rest/administration/workingmodes/:id');
});

/*
 * Routing
 */
advApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
                when('/guest', {
                    templateUrl: 'guest.view.html',
                    controller: 'GuestController'
                }).
                when('/customer/:customerId', {
                    templateUrl: 'customer/customer.index.view.html',
                    controller: 'CustomerIndexController'
                }).
                when('/admin', {
                    templateUrl: 'admin/admin.index.view.html',
                    controller: 'AdminIndexController'
                }).
                when('/customer/:customerId', {
                    templateUrl: 'customer/customer.index.view.html',
                    controller: 'CustomerIndexController'
                }).
                when('/customer/:customerId/device/:deviceId/temperature/chart', {
                    templateUrl: 'customer/customer.chart.view.html',
                    controller: 'CustomerChartTemperatureController'
                }).
                when('/customer/:customerId/device/:deviceId/humidity/chart', {
                    templateUrl: 'customer/customer.chart.view.html',
                    controller: 'CustomerChartHumidityController'
                }).
                when('/customer_stat', {
                    templateUrl: 'customer/customer.stat.view.html',
                    controller: 'CustomerStatController'
                }).
                otherwise({
                    redirectTo: '/guest'
                });
    }]);

advApp.factory('TokenInjector', function ($cookies) {
    var tokenInjector = {
        request: function (config) {
            var token = $cookies.get("auth_token");
            if (token && token !== "UNSECURED") {
                config.headers['auth_token'] = token;
            }
            return config;
        }
    };
    return tokenInjector;
});
advApp.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('TokenInjector');
    }]);

//
//advApp.filter("longToDate", function(){
//            return function(input){
//                return new Date(input);
//            }
//        });


