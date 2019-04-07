package com.ambarish.logs.logprocessor.service;

import com.ambarish.logs.logprocessor.model.LogLine;
import com.ambarish.logs.logprocessor.model.response.StatusResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StatusRetrivalService {

    public String getStatusForOrder(String oType, String oId,long ts){

        //call db to get rows for that ordertpye and orderid
        List<LogLine> logLinesForOrder = getMockOrders(oType,oId);

        Map<String,String> statuses = new HashMap<>();
        for (LogLine entry: logLinesForOrder ) {
            String msg = entry.getMsg();
            Map<String,String> entryStatus = getKeyValueFromJsonString(msg);
            entryStatus.forEach((k,v) -> statuses.put(k,v));
        }

        return StatusResponse.builder().oStatus(statuses).build().toString();
    }

    private Map<String, String> getKeyValueFromJsonString(String msg) {
        Map<String, String> entryStatus = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            entryStatus = mapper.readValue(msg, new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entryStatus;
    }

    private List<LogLine> getMockOrders(String oType, String oId){
        List<LogLine> mockObjs = new LinkedList<>();

        mockObjs.add(LogLine.builder().msg("{\"customer_name\":\"Jack\",\"customer_address\":\"Trade St.\",\"status\":\"unpaid\"}").build());
        mockObjs.add(LogLine.builder().msg("{\"status\":\"paid\",\"ship_date\":\"2017-01-18\",\"shipping_provider\":\"DHL\"}").build());

        return mockObjs;
    }
}
