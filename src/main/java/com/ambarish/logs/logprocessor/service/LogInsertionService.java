package com.ambarish.logs.logprocessor.service;

import com.ambarish.logs.logprocessor.adapter.LogLineAdapter;
import com.ambarish.logs.logprocessor.model.LogLine;
import com.ambarish.logs.logprocessor.mongo.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class LogInsertionService {

    Logger logger = LoggerFactory.getLogger(LogInsertionService.class);

    ExecutorService executorService;
    int batchSize;

    public LogInsertionService(ExecutorService executorService,int batchSize){
        this.executorService = executorService;
        this.batchSize = batchSize;
    }

    @Autowired
    LogRepository logRepository;

    @Autowired
    LogLineAdapter logLineAdapter;

    public Boolean storeLogFile(MultipartFile file)
    {
        Boolean uploadSuccessful = true;
        List<String> lines = new LinkedList<>();
        List<Future<Boolean>> taskResults = new LinkedList<>();
        try {
            String line;
            InputStream is = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            for(int i=0;i<(lines.size()/batchSize)+1;i++)
            {
                List<String> batch = lines.subList(i*batchSize,Math.min((i+1)*batchSize,lines.size()));
                taskResults.add(executorService.submit(()-> insertLines(batch)));
            }

            for (Future fut: taskResults ) {
                uploadSuccessful &= (Boolean)fut.get();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return uploadSuccessful;
    }

    public boolean insertLines(List<String> logLineStrings){

        logger.info("Processing log string batch of size " + logLineStrings.size());

        //adapt them to List<LogLine>
        List<LogLine> logLines = logLineStrings.stream()
                .map(line -> logLineAdapter.adapt(line))
                .collect(Collectors.toList());

        return logRepository.upsertLogLines(logLines);
    }
}
