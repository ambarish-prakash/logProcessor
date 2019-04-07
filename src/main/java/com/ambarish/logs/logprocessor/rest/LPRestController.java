package com.ambarish.logs.logprocessor.rest;

import com.ambarish.logs.logprocessor.service.StatusRetrivalService;
import org.ietf.jgss.Oid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LPRestController {

    @Autowired
    StatusRetrivalService statusRetrivalService;

    @RequestMapping( method = RequestMethod.GET, value = "/status/{oType}/{oId}/{ts}")
    public String getStatus(@PathVariable("oType") String oType, @PathVariable("oId") String oId, @PathVariable("ts") long ts) {
        return statusRetrivalService.getStatusForOrder(oType,oId,ts);
    }

}
