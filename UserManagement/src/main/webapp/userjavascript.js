

var app = angular.module("manage", []);

//Controller Part
app.controller("UserController", function($scope, $http) {


	$scope.users = [];
	$scope.userForm = {
			userid : -1,
			username : "",
			password : "",
			status : ""
	};

	$scope.checkAll = function () {
	};

//	_refreshuserData();



	$scope.createUser = function() {
		var method = "";
		var url = "";
		method = "POST";
		url = 'http://localhost:8080/user';

		$http({
			method : method,
			url : url,
			data : angular.toJson($scope.userForm),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then( _success, _error );

//		alert("Operation  completed successfully");
	};


	$scope.showAll = function() {

		_refreshuserData();

	};

	$scope.updateUser = function() {
		var method = "";
		var url = "";

		method = "PUT";
		url = 'http://localhost:8080/user';
//		alert("updateUser");

		$http({
			method : method,
			url : url,
			data : angular.toJson($scope.userForm),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function successCallback(response) {
			$scope.userForm = response.data;
			_refreshuserData();
//			_clearFormData();
		}, function errorCallback(response) {
			alert(JSON.stringify(response));
			console.log(response.statusText);
		});

	};


	$scope.findUser = function() {
		var method = "";
		var url = "";

		method = "GET";

		url = 'http://localhost:8080/user?username=' + $scope.userForm.username;
//		alert("findUser");

		$http({
			method : method,
			url : url,
			data : angular.toJson($scope.userForm),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function successCallback(response) {
			$scope.userForm = response.data;
		}, function errorCallback(response) {
			alert(JSON.stringify(response));
			console.log(response.statusText);
		});

	};

	
	$scope.sortByName = function() {
		var method = "";
		var url = "";

		method = "GET";
		url = 'http://localhost:8080/user';

		$http({
			method : method,
			url : url,
			data : angular.toJson($scope.userForm),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function successCallback(response) {
			$scope.users = response.data;
		}, function errorCallback(response) {
			alert(JSON.stringify(response));
			console.log(response.statusText);
		});

	};


	$scope.deleteUser = function(user) {

		$scope.userForm.username = user.username;
		$scope.userForm.password = user.password;
		$scope.userForm.status = user.status;
		alert("deleteUser");

		$http({
			method : 'DELETE',
			url : 'http://localhost:8080/user',
			data : angular.toJson($scope.userForm),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(_success, _error);
	};

	// In case of edit, populate form fields 
	$scope.editUser = function(user) {

		$scope.userForm.username = user.username;
		$scope.userForm.password = user.password;
		$scope.userForm.status = user.status;

		alert("Please edit above and click on Update button. Please don't edit Name..");
	};



	function _refreshuserData() {
//		alert("_refreshuserData");
		$http({
			method : 'GET',
			url : 'http://localhost:8080/user?username=' + $scope.userForm.username
		}).then(function successCallback(response) {
			$scope.users = response.data;
		}, function errorCallback(response) {
			alert(JSON.stringify(response));
			console.log(response.statusText);
		});
	}

	function _success(response) {
		_refreshuserData();
		_clearFormData()
	}

	function _error(response) {
		alert(JSON.stringify(response));
		console.log(response.statusText);
	}

	//Clear the form
	function _clearFormData() {
		$scope.userForm.userid = -1;
		$scope.userForm.username = "";
		$scope.userForm.password = "";
		$scope.userForm.status = "";

	};

});
