const app = angular.module('product-app', []);
app.controller('product_ctrl', function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};

	$scope.initialize = function() {
		$http.get(`/rest/products`).then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		});
		//Load Category

		$http.get(`/rest/categories`).then(resp => {
			$scope.cates = resp.data;
		});
	}

	$scope.initialize();

	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: 'icloud-upload.png',
			available: true,
		}
	}

	$scope.edit = function(item) {
		$scope.form = angular.copy(item)
		/*	$(".nav-pills a:eq(1)").nav('show')*/

	}
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/products`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate);
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Them thanh cong");
		}).catch(err => {
			alert("Them that bai");
			console.log(err);
		})
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Update thanh cong");
		}).catch(err => {
			alert('Update that bai');
			console.log(err);
		})
	}

	$scope.delete = function(item) {
		$http.delete(`/rest/products/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xoa thanh cong")
		}).catch(err => {
			alert("Xoa that bai");
			console.log(err);
		})
	}

	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post(`/rest/upload/images/`, data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(err => {
			console.log(err);
		})
	}


})