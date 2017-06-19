<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>

<style>
table, th, td {
	border: 2px solid rgba(30, 167, 233, 0.22) !important;
}

h1, h2, h3, h4, h5, h6 {
	color: mediumpurple !important;
}

.w3-table td, .w3-table th, .w3-table-all td, .w3-table-all th {
	color: #9c27b0;
}

.w3-ripple {
	color: deepskyblue !important;
}

.displaySuccessMessageClass {
	text-align: center !important;
	font-weight: bold !important;
	color: rgba(222, 30, 255, 0.78) !important;
}

.w3-border {
	border: 1px solid rgba(222, 30, 255, 0.78) !important;
}

th {
	background-color: rgba(30, 167, 233, 0.22) !important;
}

.w3-green, .w3-hover-green:hover {
	background-color: rgba(156, 39, 176, 0.58) !important;
	color: #9c27b0 !important;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Application - View All Students</title>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>


</head>
<body ng-app="myApp" ng-controller="userCtrl">

	<div class="w3-container">


<h4  ng-show="displayAddMessage" class="displaySuccessMessageClass">{{displaySuccessMessage}}</h4>

		<h2>Course Application - View All Students</h2>
		<br></br> <a href="<c:url value="/display_student_form" />"
			class="w3-btn w3-ripple homeAlign">&#10004;Add New Student</a> <br></br>

		<table class="w3-table w3-bordered w3-striped">
			<tr>
				<th>Sr No</th>
				<th>Student Name</th>
				<th>Student Age</th>
				<th>Student Course</th>
				<th>University Name</th>
				<th>University Country</th>
				<th>Actions</th>
			</tr>
			<tr ng-repeat="student in studentsJson">
				<td>{{ $index + 1 }}</td>
				<td>{{ student.studentName }}</td>
				<td>{{ student.studentAge }}</td>
				<td>{{ student.studentCourse }}</td>
				<td>{{ student.universityName }}</td>
				<td>{{ student.universityCountry }}</td>
				<td>
<%-- 				<a
					href="<c:url value="/deleteStudentInfoById?stuId={{student.studentId}}" />"
					class="w3-btn w3-ripple">&#10004;Delete</a> <a
					href="<c:url value="/getUniversityDetailsById?stuId={{student.studentId}}" />"
					class="w3-btn w3-ripple">&#10004;University Details</a> --%>

					<button class="w3-btn w3-green w3-ripple"
						ng-click="deleteStudent(student.studentId)">&#10004; Delete</button>

					<button class="w3-btn w3-green w3-ripple"
						ng-click="getUniversityDetail(student.studentId)">&#10004; University Details</button></td>
			</tr>
		</table>
		<br>

	</div>

	<script type="text/javascript">
		var welcomeHome = angular.module("myApp", []);
		welcomeHome.controller('userCtrl', [ '$scope', '$http',
				function($scope, $http) {
			
			
					$scope.displaySuccessMessage='';
					$scope.displayAddMessage=false;

					$scope.studentsJson;

					var studentAllUrl = "/getAllStudents";
					$http.get(studentAllUrl).success(function(response) {
						$scope.studentsJson = response;

					});

					
					$scope.deleteStudent = function(studentId) {
						var deleteUrl = "/deleteStudentInfoById?stuId="+studentId;
										
							$http.get(deleteUrl).success(function(response) {
								$scope.studentsJson = response;
								$scope.displayAddMessage=true;
								$scope.displaySuccessMessage = "User successfully deleted";

							});
						
					};
					

					$scope.getUniversityDetail = function(studentId) {
						var getUniDetailurl = "/getUniversityDetailsById?stuId="+studentId;

					
							$http.get(getUniDetailurl).success(function(response) {
								alert("University Details::"+ JSON.stringify(response));

							});         		
						
					}
					
				} ]);
	</script>

</body>
</html>
