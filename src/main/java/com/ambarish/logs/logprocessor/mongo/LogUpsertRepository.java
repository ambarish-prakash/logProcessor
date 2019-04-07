package com.ambarish.logs.logprocessor.mongo;

import com.ambarish.logs.logprocessor.model.LogLine;

import java.util.List;

public interface LogUpsertRepository {

    boolean upsertLogLines(List<LogLine> logLines);

    List<LogLine> findAllEntriesForTimestamp(String oType, String oId, Long ts);

}
