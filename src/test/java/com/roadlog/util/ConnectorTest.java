package com.roadlog.util;

import org.junit.Before;
import org.junit.Test;

import static com.roadlog.cons.App.OUTPUT_PATH;
import static com.roadlog.cons.App.TEST_FILE_PATH;
import static org.junit.Assert.*;

/**
 * @author MusaAl
 * @date 12/7/2018 : 5:08 PM
 */
public class ConnectorTest {

    private Connector connector;

    @Before
    public void setUp() throws Exception {
        connector = new Connector();
        Connector.input = TEST_FILE_PATH;
        Connector.output = OUTPUT_PATH;
    }

    @Test
    public void readStream() {
        connector.readStream().forEach(x->{
            for (String s : x) {
                System.out.print(s+" ;");
            }
            System.out.println("");
        });
    }
}