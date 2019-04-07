package com.ambarish.logs.logprocessor.adapter;

import com.ambarish.logs.logprocessor.model.LogLine;

public class LogLineAdapter {

    public LogLine adapt(String log){

        String[] logSplit = log.split(",",4);

        if(logSplit.length != 4)
            return null;

        String msg = logSplit[3];
        if(msg.startsWith("\"")){
            msg = msg.substring(1);
        }
        if(msg.endsWith("\"")){
            msg = msg.substring(0,msg.length()-1);
        }
        msg = msg.replaceAll("\\\\","");

        return LogLine.builder().oId(logSplit[0])
                .oType(logSplit[1])
                .ts(Long.parseLong(logSplit[2]))
                .msg(msg)
                .build();
    }
}
