package org.gonzalez;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class CurrencyServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceTest.class);


    @Test
    public void somethingTest(){

        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");

        assertEquals(2, 2);
    }
}
