advApp.controller('GuestController', function ($scope, $cookies, $location,
        Tokens) {

    $scope.dataLoading = false;
    $scope.error = null;
    $cookies.put("auth_token", "UNSECURED");

    $scope.login = function () {

        $scope.dataLoading = true;
        Tokens.save($scope.user,
                function (response) {
                    $scope.error = null;
                    $cookies.put("auth_token", response.auth_token);
                    $scope.dataLoading = false;

                    if ($scope.user.username === "admin") {
                        $location.path("/admin");
                    }
                    else {
                        $location.path("/customer/" + response.customerId);
                    }

                },
                function () {
                    $scope.error = "Pogrsno korisnicko ime ili lozinka!";
                    $scope.dataLoading = false;
                });
    };

});

