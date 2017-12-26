package com.dwkegu.spiderlibTest;

import com.dwkegu.spiderlib.BaseFrame.AddressBase;
import com.dwkegu.spiderlib.NYtimesUrlItemI;
import com.dwkegu.spiderlib.URLHub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddressBase {

    @Test
    public void testHub(){
        assertEquals(120, new URLHub<NYtimesUrlItemI>(AddressBase.CrawlDirection.horizontal).getItem());
    }
}
