

var app = angular.module("manage", []);

//Controller Part
app.controller("UserController", function($scope, $http) {


	$scope.users = [];
	$scope.userForm = {
			userid : -1,
			username : "",
			password : "",
			status : "",
			result : ""
	};

	var basrurl = '/user';

	$scope.checkAll = function () {
	};

//	_refreshuserData();

	$scope.createUser = function() {
		var method = "";
		method = "POST";
		$scope.userForm.status = "Activated";

		$http({
			method : method,
			url : basrurl,
			data : angular.toJson($scope.userForm),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function successCallback(response) {
			_refreshuserData();
			$scope.userForm.response = "User created successfully..";
		}, function errorCallback(response) {
			$scope.userForm.response = "ERROR: Unable to create user..";
			alert(JSON.stringify(response));
			console.log(response.statusText);
		});

	};


	$scope.showAll = function() {

		_refreshuserData();

	};

	$scope.updateUser = function() {
		var method = "";

		method = "PUT";

		$http({
			method : method,
			url : basrurl,
			data : angular.toJson($scope.userForm),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function successCallback(response) {
			_refreshuserData();
			$scope.userForm.response = "User updated successfully..";
		}, function errorCallback(response) {
			$scope.userForm.response = "ERROR: Unable to update user..";
			alert(JSON.stringify(response));
			console.log(response.statusText);
		});

	};


	$scope.findUser = function() {
		var method = "";

		method = "GET";

		findurl = basrurl + '?username=' + $scope.userForm.username;

		$http({
			method : method,
			url : findurl,
			data : angular.toJson($scope.userForm),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function successCallback(response) {
			$scope.userForm = response.data;
			$scope.userForm.response = "Found user..";
		}, function errorCallback(response) {
			$scope.userForm.response = "ERROR: Unable to find user..";
			alert(JSON.stringify(response));
			console.log(response.statusText);
		});

	};

	$scope.deleteUser = function() {

		$http({
			method : 'DELETE',
			url : basrurl + '?userid=' + $scope.userForm.userid,
			data : angular.toJson($scope.userForm),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function successCallback(response) {
			$scope.users = response.data;
			$scope.userForm.response = "Deleted User successfully..";
		}, function errorCallback(response) {
			$scope.userForm.response = "ERROR: Unable to delete user..";
			alert(JSON.stringify(response));
			console.log(response.statusText);
		});
	};

	function _refreshuserData() {
		$http({
			method : 'GET',
			url : basrurl + '?username=' + $scope.userForm.username
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
		$scope.userForm.response = "";

	}

});
