advApp.controller('CustomerInfoController', function($scope, $interval, Measurements){

    $scope.message = "Status uredjaja:";
    $scope.measurements = [];
    $scope.measurement = {};
    $scope.measurement.temperature = 22.1;
    $scope.measurement.humidity = 77.5;

//    $scope.updateReadings = function(){
//
//        var arg = {
//            desc: true,
//            limit: 1
//        };
//
//
//        // Measurements.get({id: 1});
//        // Measurements.get();
////        $scope.measurements = Measurements.query(arg, function (data) {
////            $scope.measurement = data[0];
////        });
//
//        // Measurements.get(arg2);
//        //  Measurements.get(arg3);
//
//    };

   // $interval($scope.updateReadings, 30000);
   // $scope.updateReadings();
});