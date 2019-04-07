package com.ambarish.logs.logprocessor.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogLine {

    private String oType;
    private String oId;
    private long ts;
    private String msg;

}
