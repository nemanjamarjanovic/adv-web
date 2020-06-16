advApp.controller('CustomerChartTemperatureController', function ($scope, $routeParams, $cookies, $location,
        DeviceStates, Tokens) {

    $scope.logout = function () {
        Tokens.delete();
        $cookies.put("auth_token", "UNSECURED");
        $location.path("/guest");
    };

    $scope.init = function () {
//        $('#container').highcharts({
//            chart: {zoomType: 'x'},
//            title: {text: 'Pregled istorije temperature'},
//            xAxis: {categories: $scope.dates},
//            yAxis: {title: {text: 'Temperatura'}},
//            legend: {enabled: false},
//            series: [{
//                    type: 'line',
//                    name: 'temperatura',
//                    data: $scope.values
//                }]
//        });

        $scope.chartConfig = {
//            options: {
//                chart: {
//                    type: 'line'
//                },
//                tooltip: {
//                    style: {
//                        padding: 10,
//                        fontWeight: 'bold'
//                    }
//                }
//            },
            chart: {zoomType: 'x'},
            title: {text: 'Pregled istorije temperature'},
            xAxis: {categories: $scope.dates},
            yAxis: {title: {text: 'Temperatura'}},
            legend: {enabled: false},
            series: [{
                    type: 'line',
                    name: 'temperatura',
                    data: $scope.values
                }],
            size: {
                height: 500
            }
        };
    };

    $scope.filter = function () {
        DeviceStates.query(
                {
                    customerId: $routeParams.customerId,
                    deviceId: $routeParams.deviceId,
                    before: $scope.before,
                    after: $scope.after
                },
        function (result) {
            var i;
            for (i = 0; i < result.length; i++) {
                $scope.values[i] = Number(result[i].temperature);
//                date = new Date(result[i].createTime);
//                dateLabel = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + " " +
//                        date.getDate() + "." + (date.getMonth() + 1) + "." + date.getFullYear();
                $scope.dates[i] = result[i].createTime;
            }
            $scope.init();
        });
    };

    $scope.values = [];
    $scope.dates = [];

    $scope.filter();


});