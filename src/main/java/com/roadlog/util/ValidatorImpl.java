package com.roadlog.util;

import com.roadlog.exception.DecathlonException;

import java.util.List;

import static com.roadlog.cons.App.MSG_2;

/**
 * @author MusaAl
 * @date 12/7/2018 : 11:08 AM
 */
public class ValidatorImpl implements Validator {
    @Override
    public boolean validateInput(List<String[]> parsed) throws DecathlonException {
        for(int i=0; i<parsed.size(); i++){
            if(parsed.get(i).length != 11)
                throw new DecathlonException(MSG_2+(i+1));
        }
        return true;
    }
}
