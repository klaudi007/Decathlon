package com.swedbank.decathlon;

import com.swedbank.decathlon.exceptions.ReadFileException;
import com.swedbank.decathlon.exceptions.WriteFileException;
import com.swedbank.decathlon.model.Athlete;
import com.swedbank.decathlon.services.AthleteService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

/**
 * Created by Edvis on 2017-01-03.
 */
public class WriterTest {

    public final static String WRONG_OUTPUT_FILE_PATH = "wrong/file/path";
    public final static String TEST_INPUT_FILE = "src/test/resources/Decathlon_input_test.txt";

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void initialise(){
        Reader.getReader().setInputFileLocation(TEST_INPUT_FILE);
        Writer.getWriter().setOutputFileLocation(WRONG_OUTPUT_FILE_PATH);
    }

    @Test
    public void writeFileAsXMLTest() throws WriteFileException {
        exception.expect(WriteFileException.class);
        List<Athlete> athletes = AthleteService.getService().getAthletesByScoreASC();
        Writer.getWriter().writeFileAsXML(athletes);
    }
}
