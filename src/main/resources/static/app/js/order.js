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
		var orderToUpdate = $scope.listOrders.find(function(order){
			return order.id === orderID;
		})
		if (orderToUpdate){
			var updateOrder = angular.copy(orderToUpdate);
			updateOrder.orderStatus.id = 2;
			$http.put(`/rest/order/${orderToUpdate.id}`, updateOrder).then(resp => {
				$scope.initialize();
				alert('Update success');
			}).catch(err => {
				alert('Error updating');
			})
		}
	}

	$scope.updateOrder = {

		confirmOrder(orderID){
			var orderToUpdate = $scope.listOrders.find(function(order){
				return order.id === orderID;
			})
			if (orderToUpdate){
				var updateOrder = angular.copy(orderToUpdate);
				updateOrder.orderStatus.id = 2;
				$http.put(`/rest/order/${orderToUpdate.id}`, updateOrder).then(resp => {
					$scope.initialize();
					alert('Update success');
					$(".nav-pills a:eq(1)").tab('show');
				}).catch(err => {
					alert('Error updating');
				})
			}
		},
		cancelOrder(orderID){
			var orderToUpdate = $scope.listOrdersConfirmed.find(function(order){
				return order.id === orderID;			
			})
			if(orderToUpdate){
				var orderCancel =  angular.copy(orderToUpdate)
				orderCancel.orderStatus.id = 3;
				$http.put(`/rest/order/cancel/${orderCancel.id}`,orderCancel).then(resp=>{
					$scope.initialize();
					alert('Cancel order success');
					$(".nav-pills a:eq(2)").tab('show');
				}).catch(err => {
					alert('Error cancelling order');
				})
			}
		},
		resetOrder(orderID){
			var orderToUpdate = $scope.listOrdersCancelled.find(function(order){
				return order.id === orderID;			
			})
			if(orderToUpdate){
				var orderReset =  angular.copy(orderToUpdate)
				orderReset.orderStatus.id = 2;
				$http.put(`/rest/order/reset/${orderReset.id}`,orderReset).then(resp=>{
					$scope.initialize();
					alert('Reset order success');
					$(".nav-pills a:eq(1)").tab('show');
				}).catch(err => {
					alert('Error reset order');
				})
			}
		},
		successOrder(orderID){
			var orderToUpdate = $scope.listOrdersConfirmed.find(function(order){
				return order.id === orderID;			
			})
			if(orderToUpdate){
				var orderSuccess =  angular.copy(orderToUpdate)
				orderSuccess.orderStatus.id = 4;
				$http.put(`/rest/order/success/${orderSuccess.id}`,orderSuccess).then(resp=>{
					$scope.initialize();
					alert('Order Success');
					$(".nav-pills a:eq(3)").tab('show');
				}).catch(err => {
					alert('Error');
				})
			}
		}
	};

	

    
});