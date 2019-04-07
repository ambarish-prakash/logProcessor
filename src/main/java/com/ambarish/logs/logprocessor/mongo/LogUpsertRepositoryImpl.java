package com.ambarish.logs.logprocessor.mongo;

import com.ambarish.logs.logprocessor.model.LogLine;
import com.mongodb.bulk.BulkWriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.stream.Collectors;


public class LogUpsertRepositoryImpl implements LogUpsertRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public boolean upsertLogLines(List<LogLine> logLines) {

        List<Pair<Query,Update>> upserts = logLines.stream()
                .map(logLine -> buildUpdateQuery(logLine))
                .collect(Collectors.toList());

        BulkWriteResult result = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED,LogLine.class)
                .upsert(upserts).execute();

        return result.wasAcknowledged();
    }

    private Pair<Query, Update> buildUpdateQuery(LogLine logLine) {
        Query query = new Query();
        query.addCriteria(Criteria.where("oType").is(logLine.getOType())
                .and("oId").is(logLine.getOId())
                .and("ts").is(logLine.getTs()));

        Update update = new Update();
        update.set("msg", logLine.getMsg());

        return Pair.of(query,update);
    }

    @Override
    public List<LogLine> findAllEntriesForTimestamp(String oType, String oId, Long ts) {

        Query query = new Query();
        query.addCriteria(Criteria.where("oType").is(oType)
                .and("oId").is(oId)
                .and("ts").lte(ts));

        return mongoTemplate.find(query,LogLine.class);
    }
}
