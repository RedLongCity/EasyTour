'use strict';
 
App.controller('SettingsController', ['$scope','SettingsService',function($scope,SettingsService) {
        
        var self = this;
        self.delays=[];
        self.statuses=[];
        self.shortClasses=[];
        self.globalClasses=[];
        
        self.fetchStatuses = function(){
           SettingsService.getStatuses()
                   .then(
                       function(d) {
                           self.statuses = d;
                       },
                        function(errResponse){
                            console.error('Error while fetching statuses');
                        }
                    );
        };
        
        self.fetchDelays = function(){
            SettingsService.getDelays()
                   .then(
                       function(d) {
                           self.delays = d;
                       },
                        function(errResponse){
                            console.error('Error while fetching statuses');
                        }
                    );
        };
        
        self.isShortRun=function(){
            self.fetchStatuses();
            return self.statuses[0];
        };
        
        self.isGlobalRun=function(){
            self.fetchStatuses();
            return self.statuses[1];
        };
        
        self.getShortDelay=function(){
            self.fetchDelays();
            return self.statuses[0];
        };
        
        self.getGlobalDelay=function(){
            self.fetchDelays();
            return self.statuses[1];
        };
        
        self.setShortDelay=function(delay){
            SettingsService.setShortDelay(delay);
        };
        
        self.setGlobalDelay=function(delay){
            SettingsService.setGlobalDelay(delay);
        };
        
        self.stopShort=function(){
            SettingsService.stopShort();
        };
        
        self.stopGlobal=function(){
            SettingsService.stopGlobal();
        };
        
        self.resumeShort=function(){
            SettingsService.resumeShort();
        };

        self.resumeGlobal=function(){
            SettingsService.resumeGlobal();  
        };
        
        self.getShortStatus=function(){
            if(self.isShortRun=="TRUE"){
                self.shortClasses=["alert bg-success","Updating Running","fa fa-rocket fa-xl"];
                return self.shortClasses;
            }else{
                self.shortClasses=["alert bg-danger","Updating Stopped","fa fa-ban fa-xl"];
                return self.shortClasses;
            }
        };
        
        self.getGlobalStatus=function(){
            if(self.isGlobalRun=="TRUE"){
                self.shortClasses=["alert bg-success","Updating Running","fa fa-rocket fa-xl"];
                return self.shortClasses;
            }else{
                self.shortClasses=["alert bg-danger","Updating Stopped","fa fa-ban fa-xl"];
                return self.shortClasses;
            }
        };
        
        self.fetchStatuses();
        self.fetchDelays();
}]);