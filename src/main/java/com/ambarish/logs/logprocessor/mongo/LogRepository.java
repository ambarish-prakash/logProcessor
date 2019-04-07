package com.ambarish.logs.logprocessor.mongo;

import com.ambarish.logs.logprocessor.model.LogLine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<LogLine, Long>, LogUpsertRepository {

}
