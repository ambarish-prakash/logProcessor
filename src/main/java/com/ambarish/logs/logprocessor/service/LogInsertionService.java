package com.ambarish.logs.logprocessor.service;

import com.ambarish.logs.logprocessor.adapter.LogLineAdapter;
import com.ambarish.logs.logprocessor.model.LogLine;
import com.ambarish.logs.logprocessor.mongo.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogInsertionService {

    int batchSize;

    public LogInsertionService(int batchSize){
        this.batchSize = batchSize;
    }

    @Autowired
    LogRepository logRepository;

    @Autowired
    LogLineAdapter logLineAdapter;

    public boolean insertLines(List<String> logLineStrings){

        //adapt them to List<LogLine>
        List<LogLine> logLines = logLineStrings.stream().map(line -> logLineAdapter.adapt(line)).collect(Collectors.toList());

        return logLines.stream().map(log -> logRepository.upsertLogLine(log)).reduce(true, (x,y)-> x && y);
    }

    public Boolean storeLogFile(MultipartFile file)
    {
        BufferedReader br;
        Boolean uploadSuccessful = true;
        List<String> lines = new LinkedList<>();
        try {

            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                lines.add(line);
                if(lines.size() == batchSize){
                    uploadSuccessful &= insertLines(lines);
                    lines.clear();
                }
            }
            if(lines.size()!= 0 )
                uploadSuccessful = uploadSuccessful && insertLines(lines);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return uploadSuccessful;
    }

    public boolean insertMockLines(){

        //adapt them to List<LogLine>
        LogLine log = LogLine.builder()
                .oId("1")
                .oType("Order")
                .ts(1484730554)
                .msg("{\"customer_name\":\"Jack\",\"customer_address\":\"Trade St.\",\"status\":\"unpaid\"}")
                .build();

        return logRepository.upsertLogLine(log);
    }
}
