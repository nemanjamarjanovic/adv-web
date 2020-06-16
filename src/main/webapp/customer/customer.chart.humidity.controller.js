advApp.controller('CustomerChartHumidityController', function ($scope, $routeParams,$cookies,$location, DeviceHumidities,Tokens) {

    $scope.values = [];
    $scope.dates = [];

    $scope.customerId = $routeParams.customerId;
    $scope.deviceId = $routeParams.deviceId;

    $scope.init = function () {
        $('#container').highcharts({
            chart: {zoomType: 'x'},
            title: {text: 'Pregled istorije vlaznosti vazduha'},
            xAxis: {categories: $scope.dates},
            yAxis: {title: {text: 'Vlaznost vazduha'}},
            legend: {enabled: false},
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },
            series: [{
                    type: 'line',
                    name: 'vlaznost vazduha',
                    data: $scope.values
                }]
        });

    };

    $scope.filter = function () {
        DeviceHumidities.query(
                {
                    customerId: $scope.customerId,
                    deviceId: $scope.deviceId,
                    before: $scope.before,
                    after: $scope.after
                },
        function (result) {
            var i, date, dateLabel;
            for (i = 0; i < result.length; i++) {
                $scope.values[i] = Number(result[i].humidity);
                date = new Date(result[i].createTime);
                dateLabel = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + " " +
                        date.getDate() + "." + (date.getMonth() + 1) + "." + date.getFullYear();
                $scope.dates[i] = dateLabel;
            }
            $scope.init();
        });
    };

    $scope.filter();


    $scope.logout = function () {
        Tokens.delete(
        function () {
            $cookies.put("auth_token", "UNSECURED");
            $location.path("/guest");
        }, function () {
            $cookies.put("auth_token", "UNSECURED");
            $location.path("/guest");
        });
    };
});