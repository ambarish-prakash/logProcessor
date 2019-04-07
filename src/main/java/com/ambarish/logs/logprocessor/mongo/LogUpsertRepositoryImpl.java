package com.ambarish.logs.logprocessor.mongo;

import com.ambarish.logs.logprocessor.model.LogLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


public class LogUpsertRepositoryImpl implements LogUpsertRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public boolean upsertLogLine(LogLine logLine) {

        Query query = new Query();
        query.addCriteria(Criteria.where("oType").is(logLine.getOType())
                .and("oId").is(logLine.getOId())
                .and("ts").is(logLine.getTs()));

        Update update = new Update();
        update.set("msg", logLine.getMsg());

        mongoTemplate.upsert(query,update,LogLine.class);

        return true;
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
