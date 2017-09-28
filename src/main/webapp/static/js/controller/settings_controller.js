'use strict';
 
App.controller('SettingsController', ['$scope','SettingsService',function($scope,SettingsService) {
        
        var self = this;
        self.shortStatus="true";
        self.globalStatus="true";
        self.shortDelay=12;
        self.globalDelay=new String();
        self.globalDelay=1;
    
        self.globalDelaysArray_Human=[1,10,15,30,60,120,180,300];
        self.shortDelaysArray=[1000,2000,5000,10000,12000,20000,30000];
        
        self.fetchShortStatus = function(){
           SettingsService.getShort_Status()
                   .then(
                       function(d) {
                           self.shortStatus = d;
                       },
                        function(errResponse){
                            console.error('Error while fetching short status');
                        }
                    );
        };
        
        self.fetchGlobalStatus = function(){
           SettingsService.getGlobal_Status()
                   .then(
                       function(d) {
                           self.globalStatus = d;
                       },
                        function(errResponse){
                            console.error('Error while fetching global status');
                        }
                    );
        };
        
        self.fetchShortDelay = function(){
            SettingsService.getShort_Delay()
                   .then(
                       function(d) {
                           self.shortDelay = d;
                       },
                        function(errResponse){
                            console.error('Error while fetching short delay');
                        }
                    );
        };
        
        self.fetchGlobalDelay = function(){
            SettingsService.getGlobal_Delay()
                   .then(
                       function(d) {
                           self.globalDelay = d;
                       },
                        function(errResponse){
                            console.error('Error while fetching global delay');
                        }
                    );
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
        
        
       self.fetchShortStatus();
       self.fetchGlobalStatus();
       self.fetchShortDelay();
       self.fetchGlobalDelay();
}]);