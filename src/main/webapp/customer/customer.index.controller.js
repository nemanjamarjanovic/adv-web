advApp.controller('CustomerIndexController', function ($scope, $cookies, $location, $routeParams, $interval,
        Tokens, Customer, CustomerDevices, DeviceStates) {

    /*
     * Provjera tokena
     */
    $scope.token = $cookies.get("auth_token");
    if (!$scope.token || $scope.token === "UNSECURED") {
        $location.path("/guest");
    }

    /*
     * Dobavljanje trenutnog korisnika
     */
    Customer.get({customerId: $routeParams.customerId},
    function (result) {
        $scope.customer = result;
    }, function () {
        $scope.logout();
    });


    /*
     * Dobavljanje trenutnog uredjaja
     */
    CustomerDevices.query({customerId: $routeParams.customerId},
    function (result) {
        $scope.device = result[0];
        $scope.findInitData();
        DeviceStates.query({
            customerId: $scope.customer.id,
            deviceId: $scope.device.id,
            last: true
        }, function (result) {
            $scope.lastDeviceState = result[0];
        });
    }, function () {
        $scope.logout();
    });

    $scope.valuest = [];
    $scope.valuesh = [];
    $scope.labels = [];


    $scope.lastDeviceState = {temperature: 0, humidity: 0};

    /*
     * Odjava
     */
    $scope.logout = function () {
        Tokens.delete();
        $cookies.put("auth_token", "UNSECURED");
        $location.path("/guest");
    };

    /*
     * Kreiranje grafikona
     */
    $scope.initGraphs = function () {

        $scope.tchartConfig = {
            options: {
                chart: {
                    type: 'line'
                },
                tooltip: {
                    style: {
                        padding: 10,
                        fontWeight: 'bold'
                    }
                }
            },
            series: [{
                    data: $scope.valuest
                }],
            xAxis: {
                title: {text: 'values'},
                categories: $scope.labels
            },
            yAxis: {currentMin: 0, currentMax: 40, text:'temperatura'},
            legend: {enabled: false},
            size: {
                height: 250
            }
        };

        $scope.hchartConfig = {
            options: {
                chart: {
                    type: 'line'
                },
                tooltip: {
                    style: {
                        padding: 10,
                        fontWeight: 'bold'
                    }
                }
            },
            series: [{
                    data: $scope.valuesh
                }],
            xAxis: {
                title: {text: 'values'},
                categories: $scope.labels
            },
            yAxis: {currentMin: 50, currentMax: 100},
            legend: {enabled: false},
            size: {
                height: 250
            }
        };

    };

    /*
     * Dovlacenje pocetnih podataka za grafikone
     */
    $scope.findInitData = function () {
        DeviceStates.query({
            customerId: $scope.customer.id,
            deviceId: $scope.device.id,
            limit: 10
        },
        function (data) {
            var i;
            for (i = 0; i < data.length; i++) {
                $scope.addValueToArrayMax10($scope.valuest, Number(data[i].temperature));
                $scope.addValueToArrayMax10($scope.valuesh, Number(data[i].humidity));
                $scope.addValueToArrayMax10($scope.labels, data[i].createTime.substring(10, 16));
            }
            $scope.initGraphs();
        });
    };

    $scope.updateReadings = function () {

        DeviceStates.query({
            customerId: $scope.customer.id,
            deviceId: $scope.device.id,
            last: true
        },
        function (data) {
            $scope.lastDeviceState = data[0];
            $scope.addValueToArrayMax10($scope.valuest, Number(data[0].temperature));
            $scope.addValueToArrayMax10($scope.valuesh, Number(data[0].humidity));
            $scope.addValueToArrayMax10($scope.labels, data[0].createTime.substring(10, 16));
        });

    };

    $scope.dataupdate = $interval($scope.updateReadings, 3000);

    $scope.$on('$destroy', function () {
        $interval.cancel($scope.dataupdate);
    });


    $scope.addValueToArrayMax10 = function (arrVal, val) {
        if (arrVal.length > 9) {
            arrVal.shift();
        }
        arrVal.push.apply(arrVal, [val]);
    };

});

