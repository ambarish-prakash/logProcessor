package com.ambarish.logs.logprocessor.service;

import com.ambarish.logs.logprocessor.adapter.LogLineAdapter;
import com.ambarish.logs.logprocessor.model.LogLine;
import com.ambarish.logs.logprocessor.mongo.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class LogInsertionService {

    @Autowired
    LogRepository logRepository;

    @Autowired
    LogLineAdapter logLineAdapter;

    public boolean insertLines(List<String> logLineStrings){

        //adapt them to List<LogLine>
        List<LogLine> logLines = logLineStrings.stream().map(line -> logLineAdapter.adapt(line)).collect(Collectors.toList());

        return logLines.stream().map(log -> logRepository.upsertLogLine(log)).reduce(true, (x,y)-> x && y);
    }

    public boolean insertMockLines(){

        //adapt them to List<LogLine>
        LogLine log = LogLine.builder()
                .oId("1")
                .oType("Order")
                .ts(1484730554)
                .msg("{\"customer_name\":\"Jack\",\"customer_address\":\"Trade St.\",\"status\":\"unpaid\"}")
                .build();

        return logRepository.upsertLogLine(log);
    }
}
