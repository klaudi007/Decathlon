package com.swedbank.decathlon;

import com.swedbank.decathlon.model.Athlete;
import com.swedbank.decathlon.staticData.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * The class checks if reader correct reads event results and converts it to double format.
 *
 * Created by Edvis on 2017-01-03.
 */
public class ReaderTest {

    private final static String TEST_INPUT_FILE = "src/test/resources/Decathlon_input_test.txt";

    private static final double[] Siim_Susi_Events = {12.61, 500.0, 9.22, 150.0, 60.39, 16.43, 21.6, 260.0, 35.81, 325.72};
    private static final double[] Beata_Kana_Events = {13.04, 453.0, 7.79, 155.0, 64.72, 18.74, 24.2, 240.0, 28.2, 410.76};
    private static final double[] Jaana_Lind_Events = {13.75, 484.0, 10.12, 150.0, 68.44, 19.18, 30.85, 280.0, 33.88, 382.7500};

    private List<Athlete> athletesMock;
    private List<Athlete> athletesFromTestFile;

    @Before
    public void initialise(){
        Reader reader = Reader.getReader();
        reader.setInputFileLocation(TEST_INPUT_FILE);
        athletesFromTestFile = reader.readFile();
        athletesMock = new LinkedList<>();

        //Mock of test Athlete 1
        Athlete athlete1 = new Athlete();
        Map<Event, Double> eventsAthlete1 = new HashMap<Event, Double>();
        for(Event event : Event.values()){
            eventsAthlete1.put(event, Siim_Susi_Events[Event.valueOf(event.name()).ordinal()]);
        }
        athlete1.setName("Siim Susi");
        athlete1.setResults(eventsAthlete1);

        //Mock of test Athlete 2
        Athlete athlete2 = new Athlete();
        Map<Event, Double> eventsAthlete2 = new HashMap<Event, Double>();
        for(Event event : Event.values()){
            eventsAthlete2.put(event, Beata_Kana_Events[Event.valueOf(event.name()).ordinal()]);
        }
        athlete2.setName("Beata Kana");
        athlete2.setResults(eventsAthlete2);

        //Mock of test Athlete 3
        Athlete athlete3 = new Athlete();
        Map<Event, Double> eventsAthlete3 = new HashMap<Event, Double>();
        for(Event event : Event.values()){
            eventsAthlete3.put(event, Jaana_Lind_Events[Event.valueOf(event.name()).ordinal()]);
        }
        athlete3.setName("Jaana Lind");
        athlete3.setResults(eventsAthlete3);


        athletesMock.add(athlete1);
        athletesMock.add(athlete2);
        athletesMock.add(athlete3);
    }

    @Test
    public void readFileTest() {

        assertTrue(athletesMock.get(0).getResults().equals(athletesFromTestFile.get(0).getResults()));
        assertTrue(athletesMock.get(1).getResults().equals(athletesFromTestFile.get(1).getResults()));
        assertTrue(athletesMock.get(2).getResults().equals(athletesFromTestFile.get(2).getResults()));

    }

}
