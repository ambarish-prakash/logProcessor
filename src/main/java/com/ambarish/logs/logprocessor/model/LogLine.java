package com.ambarish.logs.logprocessor.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "logs")
@CompoundIndexes({
        @CompoundIndex(name = "type_id", def = "{'oType' : 1, 'oId': 1, 'ts': 1}")
})
public class LogLine {

    private String oType;
    private String oId;
    private long ts;
    private String msg;

}
