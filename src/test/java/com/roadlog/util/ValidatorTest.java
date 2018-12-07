package com.roadlog.util;

import com.roadlog.exception.DecathlonException;
import org.junit.Before;
import org.junit.Test;

import static com.roadlog.cons.App.WRONG_TEST_FILE_PATH;
import static org.junit.Assert.*;

/**
 * @author MusaAl
 * @date 12/7/2018 : 11:43 AM
 */
public class ValidatorTest {

    private Validator validator;

    private Connector connector;

    @Before
    public void setUp(){
        validator = new ValidatorImpl();
        connector = new Connector();
    }

    @Test
    public void validateInput() {
    }

    @Test(expected = DecathlonException.class)
    public void expectException(){
        Connector.input = WRONG_TEST_FILE_PATH;
        validator.validateInput(connector.readStream());
    }
}