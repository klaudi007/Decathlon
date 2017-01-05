package com.swedbank.decathlon;

import com.swedbank.decathlon.exceptions.ReadFileException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Edvis on 2017-01-03.
 */
public class InputValidatorTest {

    private final static List<String> Correct_Data = Arrays.asList(new String[]{
            "Siim Susi;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72",
            "Beata Kana;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6.50.76",
            "Jaana Lind;13.75;4.84;10.12;1.50;68.44;19.18;30.85;2.80;33.88;6.22.75"
    });

    private final static List<String> Wrong_Data = Arrays.asList(new String[]{
            "Siim Susi;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.7200",
            "Beata Kana;13.04;4.53;7.79;1.55;64.72;18.74;24f.20;2.40;28.20;6.50.76",
            "Jaana Lind;13.75;4.84;10.12;1.50;68.44;19.18;30.85;2.80;33.88;6.0022.75;12.58"
    });

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void validateFileTest() throws ReadFileException {
        InputValidator validator = new InputValidator();
        assertTrue(validator.validateFile(Correct_Data) == true);
    }

    @Test
    public void checkIncorrectFileTest() throws ReadFileException {
        InputValidator validator = new InputValidator();
        exception.expect(ReadFileException.class);
        validator.validateFile(Wrong_Data);
    }
}
