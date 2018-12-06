package com.roadlog.service.impl;

import com.roadlog.model.Athlete;
import com.roadlog.model.Athletes;
import com.roadlog.util.Connector;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

import static com.roadlog.cons.App.OUTPUT_PATH;
import static com.roadlog.cons.App.TEST_FILE_PATH;

/**
 * @author MusaAl
 * @date 12/7/2018 : 5:12 PM
 */
public class RoadlogTest {

    private static final Logger log = LoggerFactory.getLogger(RoadlogTest.class);

    private Roadlog roadlog;

    private Connector connector;

    @Before
    public void setUp() throws Exception {

        roadlog = new Roadlog();
        Connector.input = TEST_FILE_PATH;
        Connector.output = OUTPUT_PATH;
        connector = new Connector();

    }

    @Test
    public void getAthletesPerformanceByEvent() {
        List<Athlete> athletes = roadlog.getGameFinalResult(roadlog.getAthletesPerformanceByEvent(connector.readStream()));
        athletes.sort(Comparator.comparing(Athlete::getPlace));
        athletes.forEach(x -> {
            log.info("place: {}, score: {}, name: {}",x.getPlace(), x.getScore(), x.getName());
        });

        Athletes xml = new Athletes();
        xml.setAthletes(athletes);

        connector.writeStrem(xml);

    }
}