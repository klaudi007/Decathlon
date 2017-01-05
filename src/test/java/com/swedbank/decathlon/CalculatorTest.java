package com.swedbank.decathlon;

import com.swedbank.decathlon.model.Athlete;
import com.swedbank.decathlon.services.AthleteService;
import com.swedbank.decathlon.staticData.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Edvis on 2017-01-03.
 */
public class CalculatorTest {

    private final static String TEST_INPUT_FILE = "src/test/resources/Decathlon_input_test.txt";

    private static final int Siim_Susi_Score = 4200;
    private static final int Beata_Kana_Score = 3199;
    private static final int Jaana_Lind_Score = 3494;

    private static final String Siim_Susi_Place = "1";
    private static final String Beata_Kana_Place = "3";
    private static final String Jaana_Lind_Place = "2";

    private List<Athlete> athletes;

    @Before
    public void initialise(){
        Reader reader = Reader.getReader();
        reader.setInputFileLocation(TEST_INPUT_FILE);

        AthleteService service = AthleteService.getService();
        athletes = service.getAthletesByScoreASC();
    }

    @Test
    public void calculateScoreTest(){
        assertEquals(athletes.get(0).getScore(), Beata_Kana_Score);
        assertEquals(athletes.get(1).getScore(), Jaana_Lind_Score);
        assertEquals(athletes.get(2).getScore(), Siim_Susi_Score);
    }

    @Test
    public void sortAndSetPlacesTest(){
        assertEquals(athletes.get(0).getPlace(), Beata_Kana_Place);
        assertEquals(athletes.get(1).getPlace(), Jaana_Lind_Place);
        assertEquals(athletes.get(2).getPlace(), Siim_Susi_Place);
    }
}
