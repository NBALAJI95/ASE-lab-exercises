/**
 * Created by bnqm6 on 10/19/2016.
 */
var app=angular.module("calculate",[]);
app.controller("calculatectrl",function ($scope,$http) {
    $scope.currencyConvertor = function () {
        var usd=$scope.usd;
        var words = $http.get("http://localhost:8090/RESTExample/restexample/convertUSDToEUR/"+usd);
        words.success(function (data) {
            console.log(data);
            $scope.currency={"USD":usd,"INR":data.INR,"EUR":data.EURO};
        });
    }
});