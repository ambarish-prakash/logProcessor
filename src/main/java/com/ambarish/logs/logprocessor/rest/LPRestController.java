package com.ambarish.logs.logprocessor.rest;

import com.ambarish.logs.logprocessor.service.LogInsertionService;
import com.ambarish.logs.logprocessor.service.StatusRetrivalService;
import org.ietf.jgss.Oid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LPRestController {

    @Autowired
    StatusRetrivalService statusRetrivalService;

    @Autowired
    LogInsertionService logInsertionService;

    @RequestMapping( method = RequestMethod.GET, value = "/status/{oType}/{oId}/{ts}")
    public String getStatus(@PathVariable("oType") String oType, @PathVariable("oId") String oId, @PathVariable("ts") long ts) {
        return statusRetrivalService.getStatusForOrder(oType,oId,ts);
    }

    @RequestMapping( method = RequestMethod.GET, value = "/testInsert")
    public void testInsert() {
        logInsertionService.insertMockLines();
    }

    @RequestMapping( method = RequestMethod.POST, value = "/insert")
    public void insertBulk(@RequestBody List<String> logs) {
        logInsertionService.insertLines(logs);
    }

}
