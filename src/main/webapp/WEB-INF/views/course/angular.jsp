<!DOCTYPE html>
 <%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<script src="https://cdn.bootcss.com/angular.js/1.6.3/angular.min.js"></script>
	<!--script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script-->
	</head>
<body>
 
<div ng-app="myApp" ng-controller="customersCtrl"> 
 
<table>
  <tr ng-repeat="x in names">
    <td>{{ x.id }}</td>
    <td>{{ x.fullname }}</td>
    <td>{{ x.fromsource }}</td>
  </tr>
</table>
 
</div>
 
<script type="text/javascript">
var app = angular.module('myApp', []);  
app.controller('customersCtrl', function ($scope, $http) {
    $http.get("/course/getCourseList2")
    .then(function (result) {
        $scope.names = result.data;
    });
});
</script>
</body>
</html>