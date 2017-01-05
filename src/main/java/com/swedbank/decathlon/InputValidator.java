package com.swedbank.decathlon;

import com.swedbank.decathlon.exceptions.ReadFileException;
import com.swedbank.decathlon.staticData.Event;

import java.util.List;

/**
 * Created by Edvis on 2016-12-29.
 */
public class InputValidator {

    /**
     * The method validates list of lines from source file. Each line should contain
     * 11 columns separated with ';' symbol. The method calls #validateResult method to validate each result.
     *
     * @param fileCopy list of file lines in String.
     * @return true if file no exceptions throws.
     * @throws ReadFileException throws an exception if at leas one line has less or more than 11 columns, or
     *                           #validateResult method returns false.
     * @see InputValidator#validateResult(Event event, String result).
     */
    public boolean validateFile(List<String> fileCopy) throws ReadFileException {

        String separator = ";";

        for (String line : fileCopy) {

            String[] data = line.split(separator);

            if (data.length != 11) {

                throw new ReadFileException("More than 11 columns was found at line: " + (fileCopy.indexOf(line) + 1));

            }

            for (Event event : Event.values()) {
                String performance = data[Event.valueOf(event.name()).ordinal() + 1];
                if (!validateResult(event, performance)) {
                    throw new ReadFileException("Bad result format in source file at line: " + (fileCopy.indexOf(line) + 1));
                }
            }
        }
        return true;
    }

    /**
     * The method validates each result depends on unit of event.
     *
     * @param event  event of decathlon.
     * @param result event result.
     * @return true if format is valid, false if format is inappropriate.
     */
    public boolean validateResult(Event event, String result) {

        switch (event.getUnit()) {

            case MINUTES_SECONDS:
                if (result.length() == 7 || result.length() == 8) {
                    if (result.matches("^[0-9]*\\.?[0-9]*\\.?[0-9]*$"))
                        if ((result.split("\\.")[0].length() == 1 ||
                                result.split("\\.")[0].length() == 2) &&
                                result.split("\\.")[1].length() == 2 &&
                                (result.split("\\.")[2].length() == 2 ||
                                        result.split("\\.")[2].length() == 3)) {
                            return true;
                        } else {
                            return false;
                        }
                } else {
                    return false;
                }
                break;

            case METERS:
            case CENTIMETERS:
            case SECONDS:
                if (result.length() >= 4 && result.length() <= 6) {
                    if (result.matches("^[0-9]*\\.?[0-9]*$"))
                        if ((result.split("\\.")[0].length() >= 1 &&
                                result.split("\\.")[0].length() <= 3) &&
                                (result.split("\\.")[1].length() >= 1 &&
                                        result.split("\\.")[1].length() <= 3)) {
                            return true;
                        } else {
                            return false;
                        }
                } else {
                    return false;
                }
                break;
        }
        return false;
    }


}
