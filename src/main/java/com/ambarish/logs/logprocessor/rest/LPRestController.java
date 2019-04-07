package com.ambarish.logs.logprocessor.rest;

import com.ambarish.logs.logprocessor.service.LogInsertionService;
import com.ambarish.logs.logprocessor.service.StatusRetrivalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/***
 * Rest controller for the endpoints
 */
@RestController
public class LPRestController {

    Logger logger = LoggerFactory.getLogger(LPRestController.class);

    @Autowired
    StatusRetrivalService statusRetrivalService;

    @Autowired
    LogInsertionService logInsertionService;

    /***
     * Finds the status for given order type and id at a particular timestamp
     * @param oType type of object
     * @param oId id of object
     * @param ts timestamp of object change
     * @return status of object at given timeframe
     */
    @RequestMapping( method = RequestMethod.GET, value = "/status/{oType}/{oId}/{ts}")
    public String getStatus(@PathVariable("oType") String oType, @PathVariable("oId") String oId, @PathVariable("ts") long ts) {
        logger.info("Received status request for type: " + oType + ", id: " + oId + ", ts: " + ts );
        return statusRetrivalService.getStatusForOrder(oType,oId,ts);
    }

    /***
     * Post Endpoint to persist csv log file
     * @param file
     * @return boolean representing successful or not
     */
    @PostMapping("/uploadFile")
    public Boolean uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("Received request to load file");
        long startTime = System.currentTimeMillis();
        Boolean uploadSuccessful = logInsertionService.storeLogFile(file);
        logger.info("Processed file in " + (System.currentTimeMillis() - startTime) + "ms" );
        return uploadSuccessful;
    }
}
