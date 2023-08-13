const app = angular.module('order_app', []);
app.controller('order_ctrl', function($scope, $http) {
    $scope.listOrders = [];
    $scope.listOrdersConfirmed = [];
    $scope.listOrdersCancelled = [];
    $scope.listOrdersSuccessful = [];
	
	$scope.initialize = function() {
		$http.get(`/rest/order`).then(resp => {
			$scope.listOrders = resp.data;
		});

        $http.get(`/rest/order/confirm`).then(resp => {
			$scope.listOrdersConfirmed = resp.data;
		});

        $http.get(`/rest/order/cancelled`).then(resp => {
			$scope.listOrdersCancelled = resp.data;
		});

        $http.get(`/rest/order/successful`).then(resp => {
			$scope.listOrdersSuccessful = resp.data;
		});
	}

    $scope.initialize();


	$scope.updateOrder = function(orderID) {
		let orderToUpdate = $scope.listOrders.find(function(order){
			return order.id === orderID;
		})
		if (orderToUpdate){
			let updateOrder = angular.copy(orderToUpdate);
			updateOrder.orderStatus.id = 2;
			$http.put(`/rest/order/${orderToUpdate.id}`, updateOrder).then(resp => {
				$scope.initialize();
			}).catch(err => {
			})
		}
	}

	$scope.updateOrder = {

		confirmOrder(orderID){
			let orderToUpdate = $scope.listOrders.find(function(order){
				return order.id === orderID;
			})
			if (orderToUpdate){
				let updateOrder = angular.copy(orderToUpdate);
				updateOrder.orderStatus.id = 2;
				$http.put(`/rest/order/${orderToUpdate.id}`, updateOrder).then(resp => {
					$scope.initialize();
					$(".nav-pills a:eq(1)").tab('show');
				}).catch(err => {
				})
			}
		},
		cancelOrder(orderID){
			let orderToUpdateWhenOrder = $scope.listOrdersConfirmed.find(function(order){
				return order.id === orderID;			
			})

			let orderUpdateWhenCofirm = $scope.listOrders.find(function(order){
				return order.id === orderID;			
			})

			if(orderToUpdateWhenOrder){
				let orderCancel =  angular.copy(orderToUpdateWhenOrder)
				orderCancel.orderStatus.id = 3;
				$http.put(`/rest/order/cancel/${orderCancel.id}`,orderCancel).then(resp=>{
					$scope.initialize();
					$(".nav-pills a:eq(2)").tab('show');
				}).catch(err => {
				})
				
			} else if(orderUpdateWhenCofirm){
				let orderCancel =  angular.copy(orderUpdateWhenCofirm)
				orderCancel.orderStatus.id = 3;
				$http.put(`/rest/order/cancel/${orderCancel.id}`,orderCancel).then(resp=>{
					$scope.initialize();
					$(".nav-pills a:eq(2)").tab('show');
				}).catch(err => {
				})
			}
		},
		resetOrder(orderID){
			let orderToUpdate = $scope.listOrdersCancelled.find(function(order){
				return order.id === orderID;			
			})
			if(orderToUpdate){
				let orderReset =  angular.copy(orderToUpdate)
				orderReset.orderStatus.id = 2;
				$http.put(`/rest/order/reset/${orderReset.id}`,orderReset).then(resp=>{
					$scope.initialize();
					$(".nav-pills a:eq(1)").tab('show');
				}).catch(err => {
				})
			}
		},
		successOrder(orderID){
			let orderToUpdate = $scope.listOrdersConfirmed.find(function(order){
				return order.id === orderID;			
			})
			if(orderToUpdate){
				let orderSuccess =  angular.copy(orderToUpdate)
				orderSuccess.orderStatus.id = 4;
				$http.put(`/rest/order/success/${orderSuccess.id}`,orderSuccess).then(resp=>{
					$scope.initialize();
					$(".nav-pills a:eq(3)").tab('show');
				}).catch(err => {
				})
			}
		}
	};

		

    
});