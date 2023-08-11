const app = angular.module('staff-app', []);
app.controller('staff-ctrl', function($scope, $http) {
	$scope.staffs = [];
	$scope.form = {};

	$scope.initialize = function() {
		$http.get(`/rest/staff`).then(resp => {
			$scope.staffs = resp.data;
		});

	}

	$scope.initialize();

	$scope.reset = function() {
		$scope.form = {}
	}

	$scope.edit = function(staff) {
		$scope.form = angular.copy(staff);
		$(".nav-pills a:eq(1)").tab('show');
	}
	
	
	$scope.staff = {
		role:"STAFF",
		getAccount(){
			return $scope.staffs.map(staff =>{
				return {
					username: form[0],
					fullname: form[1],
					email:form[2],
					phone:form[3],
					role: "STAFF"
				}
			})
		},
		 purchase(){
            var staff = angular.copy(this);
            $http.post(`/rest/staff`,staff).then(resp=>{
				$scope.staffs.push(resp.data);
                $scope.reset();
            }).catch(error=>{
                alert("Error")
                console.log(error);
            })
        }
	}

	


	$scope.pager = {
		page: 0,
		size: 10,
		get staffs() {
			var start = this.page * this.size;
			return $scope.staffs.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.staffs.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
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

const xhr = new XMLHttpRequest();

// Kiểm tra xem đối tượng `post` có được định nghĩa hay không
if (post) {
  // Kiểm tra xem đối tượng `post` có thuộc tính `data` hay không
  if (post.data) {
    // Gửi đối tượng `post` đến máy chủ
    xhr.send(post.data);
  } else {
    console.log("Đối tượng post không có thuộc tính data");
  }
} else {
  console.log("Đối tượng post không được định nghĩa");
}

})