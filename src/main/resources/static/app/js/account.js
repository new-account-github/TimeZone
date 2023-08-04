const account = angular.module('account',[]);
account.controller('account-ctrl',function($scope,$http){
    // account ctrl
    $scope.account = {};
    
    
    $scope.initialize = function() {
        $http.get(`/rest/account`).then(resp=>{
            $scope.account = resp.data;
        });
    }
    
    $scope.initialize();

    $scope.updateAccount= function(){
        var account = angular.copy($scope.account);
        console.log(account);
        $http.put(`/rest/account/${account.username}`, account).then(resp=>{
            $scope.account = angular.copy(account);
            alert("Update thanh cong");
        }).catch(err=>{
            alert("Update that bai");
            console.log(err);
        })
    }

    $scope.delete = function(accountD){
        $http.delete(`/rest/account/${accountD.username}`).then(resp=>{
            alert("Delete access");
            location.href = "/security/logoff";
        }).catch(err => {
            alert('Delete error');
            console.log(err);
        })
    }
});