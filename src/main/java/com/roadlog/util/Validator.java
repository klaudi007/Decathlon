package com.roadlog.util;

import com.roadlog.exception.DecathlonException;

import java.util.List;

/**
 * @author MusaAl
 * @date 12/7/2018 : 10:42 AM
 */
public interface Validator {

    /**
     * Method designed for validate input file
     * in case of length inconsistence it will throw
     * @throws DecathlonException
     * @param parsed list of event result
     * */
    boolean validateInput(List<String[]> parsed) throws DecathlonException;

}
