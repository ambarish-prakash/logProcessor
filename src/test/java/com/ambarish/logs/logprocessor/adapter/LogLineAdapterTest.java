package com.ambarish.logs.logprocessor.adapter;

import com.ambarish.logs.logprocessor.model.LogLine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogLineAdapterTest {

    LogLineAdapter logLineAdapter;

    @Before
    public void setup(){
        logLineAdapter = new LogLineAdapter();
    }

    @Test
    public void adapt() {
        LogLine logLine = logLineAdapter.adapt("1,Order,1484730554,\"{\"customer_name\":\"Jack\",\"customer_address\":\"Trade St.\",\"status\":\"unpaid\"}\"");

        assertEquals("1", logLine.getOId());
        assertEquals("Order", logLine.getOType());
        assertEquals(1484730554, logLine.getTs());
        assertEquals("{\"customer_name\":\"Jack\",\"customer_address\":\"Trade St.\",\"status\":\"unpaid\"}", logLine.getMsg());
    }

    @Test
    public void adaptCase2() {
        LogLine logLine = logLineAdapter.adapt("1,Order,1484730554,\"{\\\"customer_name\\\":\\\"Jack\\\",\\\"customer_address\\\":\\\"Trade St.\\\",\\\"status\\\":\\\"unpaid\\\"}\"");

        assertEquals("1", logLine.getOId());
        assertEquals("Order", logLine.getOType());
        assertEquals(1484730554, logLine.getTs());
        assertEquals("{\"customer_name\":\"Jack\",\"customer_address\":\"Trade St.\",\"status\":\"unpaid\"}", logLine.getMsg());
    }

    @Test
    public void adaptIncorrectString() {
        LogLine logLine = logLineAdapter.adapt("1,1484730554");

        assertEquals(null, logLine);
    }
}