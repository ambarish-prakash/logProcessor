package com.ambarish.logs.logprocessor.adapter;

import com.ambarish.logs.logprocessor.model.LogLine;

public class LogLineAdapter {

    public LogLine adapt(String log){

        String[] logSplit = log.split(",",4);

        if(logSplit.length != 4)
            return null;

        return LogLine.builder().oId(logSplit[0])
                .oType(logSplit[1])
                .ts(Long.parseLong(logSplit[2]))
                .msg(logSplit[3])
                .build();
    }
}
