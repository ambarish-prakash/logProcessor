package com.ambarish.logs.logprocessor.model.response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StatusResponseTest {

    @Test
    public void toStringForEmptyStatus() {
        StatusResponse sr = StatusResponse.builder().build();
        Assert.assertEquals("{} # Object Didn't Exist at that time.",sr.toString());
    }

    @Test
    public void toStringForStatusWithValues() {

        Map<String,String> status = new HashMap<>();
        status.put("customer_name","Jack");
        status.put("status","unpaid");
        status.put("customer_address","Trade St.");
        status.put("status","paid");

        StatusResponse sr = StatusResponse.builder().oStatus(status).build();
        Assert.assertEquals("{\"customer_address\"=>\"Trade St.\", \"customer_name\"=>\"Jack\", \"status\"=>\"paid\"}",sr.toString());
    }
}