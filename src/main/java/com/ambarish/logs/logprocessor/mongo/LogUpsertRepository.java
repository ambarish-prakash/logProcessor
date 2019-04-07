package com.ambarish.logs.logprocessor.mongo;

import com.ambarish.logs.logprocessor.model.LogLine;

public interface LogUpsertRepository {

    boolean upsertLogLine(LogLine logLine);

}
