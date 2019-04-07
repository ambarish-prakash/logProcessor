package com.ambarish.logs.logprocessor.service;

import com.ambarish.logs.logprocessor.model.LogLine;
import com.ambarish.logs.logprocessor.model.response.StatusResponse;
import com.ambarish.logs.logprocessor.mongo.LogRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusRetrivalService {

    @Autowired
    LogRepository logRepository;

    public String getStatusForOrder(String oType, String oId,long ts){

        List<LogLine> logLinesForOrder = logRepository.findAllEntriesForTimestamp(oType,oId,ts);

        Map<String,String> statuses = new HashMap<>();
        for (LogLine entry: logLinesForOrder ) {
            String msg = entry.getMsg();
            Map<String,String> entryStatus = getKeyValueFromJsonString(msg);
            entryStatus.forEach(statuses::put);
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
}
