advApp.controller('CustomerStatController', function ($scope,$interval, Measurements) {

   // $scope.message = "Statistika:";

    $scope.updateReadings = function () {

        var arg = {
            limit: 100,
            desc: true
        };

        $scope.measurements = Measurements.query(arg);


    };

    $interval($scope.updateReadings, 30000);
    //$scope.updateReadings();
});
