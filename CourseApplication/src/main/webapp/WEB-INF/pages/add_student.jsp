<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<style>

h1, h2, h3, h4, h5, h6{
color: mediumpurple !important;
}

.w3-ripple{
color: deepskyblue !important;
}

form{
border: 2px solid rgba(30, 167, 233, 0.22) !important;
margin-top:10px !important;
}

.displaySuccessMessageClass{
text-align: center !important;
font-weight: bold !important;
color: rgba(222, 30, 255, 0.78) !important;
}

.w3-border{
border : 1px solid rgba(222, 30, 255, 0.78) !important;
}

.w3-green, .w3-hover-green:hover{
background-color: rgba(156, 39, 176, 0.58) !important;
color: #9c27b0 !important;
}

</style>


    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Course Application - Add Students</title>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>


</head>
<body ng-app="myApp" ng-controller="userCtrl">

<div class="w3-container">

<h2>Course Application - Add Students</h2>

<a href="<c:url value="/view_all_students" />" class="w3-btn w3-ripple homeAlign">&#10004;View All Students</a>

<h3  ng-show="displayAddMessage" class="displaySuccessMessageClass">{{addMessageResult}}</h4>		

<form>
  <h4><b>Add New Student</b></h4>
    <label>Student Name:</label>
    <input class="w3-input w3-border" type="text" ng-model="stuName" placeholder="Enter student name to be added">
  <br>
    <label>Student Age:</label>
    <input class="w3-input w3-border" type="number" ng-model="stuAge"  placeholder="Enter student age to be added">
  <br>
  <br>
    <label>Student Course:</label>
    <input class="w3-input w3-border" type="text" ng-model="stuCourse"  placeholder="Enter student course to be added">
  <br>
  
  <br>
    <label>Student University Name: </label>
    <input class="w3-input w3-border" type="text" ng-model="stuUniName"  placeholder="Enter student university name">
  <br>
  
  <br>
    <label>Student University Country: </label>
    <input class="w3-input w3-border" type="text" ng-model="stuUniCountry"  placeholder="Enter student university vounty">
  <br>    
  
  <button class="w3-btn w3-green w3-ripple" ng-click="createNewStudent()" ng-disabled="incomplete">&#10004; Save Changes</button> 

</form>

</div>

<script type="text/javascript">
	
	var welcomeHome = angular.module("myApp", []);

	welcomeHome.controller('userCtrl', ['$scope', '$http', function($scope,$http) {
		
	
	$scope.stuName='';
	$scope.stuAge='';
	$scope.stuCourse='';
	
	$scope.stuUniName='';
	$scope.stuUniCountry='';
	
	$scope.addMessageResult;
	$scope.displayAddMessage = false;
	

	$scope.$watch('stuName',function() {$scope.test();});
	$scope.$watch('stuAge',function() {$scope.test();});
	$scope.$watch('stuCourse',function() {$scope.test();});
	$scope.$watch('stuUniName',function() {$scope.test();});
	$scope.$watch('stuUniCountry',function() {$scope.test();});
	

	$scope.test = function() {
	  $scope.incomplete = false;
	  if (($scope.stuName.length==0) || ($scope.stuAge==0) || ($scope.stuCourse.length==0) || ($scope.stuUniName.length==0) || ($scope.stuUniCountry.length==0)) {
	     $scope.incomplete = true;
	  }
	};
	
	$scope.createNewStudent = function() {
		var url = "/add/students";
		
		var dataObj= {
			studentName : $scope.stuName,
			studentAge : $scope.stuAge,
			studentCourse : $scope.stuCourse,
			universityName: $scope.stuUniName,
            universityCountry: $scope.stuUniCountry
		};		
		
		var res = $http.post(url,dataObj);
				  
		res.success(function(data,status,headers,config){
			$scope.displayAddMessage = true;
			$scope.addMessageResult = "Student "+$scope.stuName+" added successfully";
		});
		res.error(function(data,status,headers,config){
			$scope.displayAddMessage = true;
			$scope.addMessageResult = "Some issue in adding the Student "+$scope.stuName+" ";
		});            		
		
	}
	}]);


</script>

</body>
</html>
