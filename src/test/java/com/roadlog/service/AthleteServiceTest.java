package com.roadlog.service;

import com.roadlog.cons.OrderBy;
import com.roadlog.model.Athlete;
import com.roadlog.model.Athletes;
import com.roadlog.repository.AthleteRepository;
import com.roadlog.repository.AthleteRepositoryImpl;
import com.roadlog.service.api.Decathlon;
import com.roadlog.service.impl.Roadlog;
import com.roadlog.util.Connector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.roadlog.cons.App.OUTPUT_PATH;
import static com.roadlog.cons.App.TEST_FILE_PATH;
import static org.junit.Assert.*;

public class AthleteServiceTest {

    private  Connector connector;

    private  Decathlon decathlon;

    private  AthleteRepository athleteRepository;

    private  AthleteService athleteService;

    private static final String John_Smith_Place = "1";
    private static final String Jane_Doe_Place = "3";
    private static final String Jaan_Lepp_Place = "2";
    private static final String Foo_Bar_Place = "4";

    private static final int John_Smith_Score = 4203;
    private static final int Jane_Doe_Score = 3201;
    private static final int Jaan_Lepp_Score = 3496;
    private static final int Foo_Bar_Score = 3099;

    @Before
    public void setUp() throws Exception {
        connector = new Connector();
        decathlon = new Roadlog();
        Connector.input = TEST_FILE_PATH;
        Connector.output = OUTPUT_PATH;
        athleteRepository = new AthleteRepositoryImpl(connector, decathlon);
        athleteService = new AthleteService(athleteRepository);
    }

    @Test
    public void getAllAthletes() {
        assertEquals(4,athleteService.getAllAthletes().size());
    }

    @Test
    public void testAthletesScore() {
        List<Athlete> athletes = athleteService.getAllAthletesOrderedByScore(OrderBy.DESC);
        assertEquals(athletes.get(0).getScore(), John_Smith_Score);
        assertEquals(athletes.get(1).getScore(), Jaan_Lepp_Score);
        assertEquals(athletes.get(2).getScore(), Jane_Doe_Score);
        assertEquals(athletes.get(3).getScore(), Foo_Bar_Score);
    }

    @Test
    public void testAthletesPlaces(){
        List<Athlete> athletes = athleteService.getAllAthletesOrderedByScore(OrderBy.DESC);
        assertEquals(athletes.get(0).getPlace(), John_Smith_Place);
        assertEquals(athletes.get(1).getPlace(), Jaan_Lepp_Place);
        assertEquals(athletes.get(2).getPlace(), Jane_Doe_Place);
        assertEquals(athletes.get(3).getPlace(), Foo_Bar_Place);
    }
}