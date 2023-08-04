const app = angular.module('product-app', []);
app.controller('product_ctrl', function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};
	$scope.form.name = '';
	$scope.form.price = '';
	$scope.form.createDate = '';


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
		$(".nav-pills a:eq(1)").tab('show');
		

	}
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/products`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate);
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Them thanh cong");
		}).catch(err => {
			alert('message');

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
		$http.post(`/rest/upload/img/gallery`, data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(err => {
			alert('Error');
			console.log(err);
		})
	}
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (prev < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}
	function isNonNegative(price) {
		return number >= 0;
	}


	/*	$scope.submitForm = function() {
			// Validate the form data
			if (!$scope.form.name) {
				alert('Xin hãy nhập tên.');
				return;
			}
			if (!$scope.form.price) {
				alert('Xin hãy nhập giá.');
				return;
			}
	
			if (!$scope.form.category) {
				alert('Xin hãy nhập loại sản phẩm.');
				return;
			}
	
			if (!$scope.form.createDate) {
				alert('Xin hãy nhập ngày tạo.');
				return;
			}
				if (!$scope.form.available) {
				alert('Xin hãy nhập Trạng Thái.');
				return;
			}
	
	
			// The form is valid, submit it
			console.log($scope.form.name);
			console.log($scope.form.price);
			console.log($scope.form.category.id);
			console.log($scope.form.createDate);
			console.log($scope.form.available);
		};*/


})