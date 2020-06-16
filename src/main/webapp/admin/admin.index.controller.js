advApp.controller('AdminIndexController', function($scope, $cookies, $location,
        Customers, Devices, Tokens, WorkingModes){

    $scope.error = null;

    $scope.token = $cookies.get("auth_token");
    if (!$scope.token || $scope.token === "UNSECURED") {
        $location.path("/guest");
    }

    $scope.logout = function(){
        $scope.dataLoading = true;
        Tokens.delete(
        function(){
            $cookies.put("auth_token", "UNSECURED");
            $location.path("/guest");
        }, function(){
            $cookies.put("auth_token", "UNSECURED");
            $location.path("/guest");
        });
    };

    $scope.devices = Devices.query();
    $scope.customers = Customers.query();
    $scope.workingModes = WorkingModes.query();

    $scope.customerEdit = false;
    $scope.deviceEdit = false;



    $scope.showCreateCustomer = function(customer){
        if (customer === null) {
            $scope.customer = {};
        }
        else {
            $scope.customer = customer;
        }
        $scope.customerEdit = true;
    };

    $scope.createCustomer = function(){
        Customers.save(
                $scope.customer,
                function(){
                    $scope.devices = Devices.query();
                    $scope.customers = Customers.query();
                    $scope.error = null;
                },
                function(response){
                    $scope.showCreateError(response.data);
                });
        $scope.customer = {};
        $scope.customerEdit = false;
    };

    $scope.showCreateDevice = function(device){
        if (device === null) {
            $scope.device = {};
        }
        else {
            $scope.device = device;
        }
        $scope.deviceEdit = true;
    };

    $scope.createDevice = function(){
        Devices.save(
                $scope.device,
                function(){
                    $scope.devices = Devices.query();
                    $scope.customers = Customers.query();
                },
                function(response){
                    $scope.showCreateError(response.data);
                });
        $scope.device = {};
        $scope.deviceEdit = false;
    };

    $scope.showCreateError = function(response){
        if (response && response.parameterViolations && response.parameterViolations[0]) {
            $scope.error = response.parameterViolations[0].message + ": "
                    + response.parameterViolations[0].path + " - " + response.parameterViolations[0].value;
        }
    };

});

