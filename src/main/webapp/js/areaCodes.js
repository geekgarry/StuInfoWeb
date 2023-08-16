/**
 * Created by dell on 2017/10/18.
 */
(function () {
    var app = angular.module('areaApp', []);
    app.controller('GetAreaCodeController', GetAreaCodeController);
    function GetAreaCodeController($scope, $http) {
        var DISTRICTS;
        $http.get('districts.json').then(function (res) {
            /*下面就是获取到的数据*/
            $scope.DISTRICTS = res.data;
        });
        $scope.areaObj = {
            'addressProvince' : undefined,
            'addressCity' : undefined,
            'addressDistrict' : undefined,
            /** 获取最终地区代码（具体到区或不设区的县级市） 参数：city为该城市的地区码，district为第三级的地区代码**/
            'getAreaCode': function (province, city, district) {
                var areaCode;
                if(province && $scope.DISTRICTS[province]) {
                    if (city && !$scope.DISTRICTS[city])
                        areaCode = city;
                    else if (district)
                        areaCode = district;
                    return areaCode;
                }else{
                    return province;
                }
            },

            /**根据地区代码获取地址名的object**/
            'getAreaName': function (areaCode) {
                areaCode = parseInt(areaCode);
                var provinceCode = parseInt(areaCode / 10000) * 10000;
                var provinceName = $scope.DISTRICTS['100000'][provinceCode];
                var cityCode = parseInt(areaCode / 100) * 100;
                var districtName = '';
                var cityName = '';
                if($scope.DISTRICTS[provinceCode]) {
                    if ($scope.DISTRICTS[cityCode]) {
                        districtName = $scope.DISTRICTS[cityCode][areaCode];
                        cityName = $scope.DISTRICTS[provinceCode][cityCode];
                    } else
                        cityName = $scope.DISTRICTS[provinceCode][areaCode];
                }
                var areaObj = {
                    "areaProvince": provinceName,
                    "areaMunicipality": cityName,
                    "areaDistrict": districtName
                };
                return areaObj;
            }
        };
    }
})();