package com.ambarish.logs.logprocessor.rest;

import com.ambarish.logs.logprocessor.service.LogInsertionService;
import com.ambarish.logs.logprocessor.service.StatusRetrivalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class LPRestController {

    Logger logger = LoggerFactory.getLogger(LPRestController.class);

    @Autowired
    StatusRetrivalService statusRetrivalService;

    @Autowired
    LogInsertionService logInsertionService;

    @RequestMapping( method = RequestMethod.GET, value = "/status/{oType}/{oId}/{ts}")
    public String getStatus(@PathVariable("oType") String oType, @PathVariable("oId") String oId, @PathVariable("ts") long ts) {
        logger.info("Received status request for type: " + oType + ", id: " + oId + ", ts: " + ts );
        return statusRetrivalService.getStatusForOrder(oType,oId,ts);
    }

    @PostMapping("/uploadFile")
    public Boolean uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("Received request to load file");
        return logInsertionService.storeLogFile(file);
    }
}
