package com.ambarish.logs.logprocessor.mongo;

import com.ambarish.logs.logprocessor.model.LogLine;

import java.util.List;

public interface LogUpsertRepository {

    boolean upsertLogLine(LogLine logLine);

    List<LogLine> findAllEntriesForTimestamp(String oType, String oId, Long ts);

}
