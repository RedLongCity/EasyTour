package com.smitsworks.easytour.utils;

import java.util.Arrays;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 28/09/2017
 * class for convertation global delays data
 */

@Service
public class GlobalUpdateDelayUtils {
    
    private String[] machineData = {"* 0/1 * * * ?","* 0/10 * * * ?","* 0/15 * * * ?",
        "* 0/30 * * * ?","* * 0/1 * * ?","* * 0/2 * * ?",
        "* * 0/3 * * ?","* * 0/5 * * ?"};
    
    private Integer[] humanData = {1,10,15,30,60,120,180,300};
    
    public String getMachineData(Integer Data){
        return machineData[Arrays.asList(humanData).indexOf(Data)];
    }
    
    public Integer getHumanData(String machineData){
        return humanData[machineData.indexOf(machineData)];
    }
    
    public String getMachineDataFromIndex(Integer index){
        return machineData[index];
    }
    
    public Integer getHumanDataFromIndex(Integer index){
        return humanData[index];
    }
}
