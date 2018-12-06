package com.roadlog;

import com.roadlog.cons.OrderBy;
import com.roadlog.model.Athlete;
import com.roadlog.model.Athletes;
import com.roadlog.repository.AthleteRepository;
import com.roadlog.repository.AthleteRepositoryImpl;
import com.roadlog.service.AthleteService;
import com.roadlog.service.api.Decathlon;
import com.roadlog.service.impl.Roadlog;
import com.roadlog.util.Connector;

import java.util.List;

import static com.roadlog.cons.App.MSG_1;
import static com.roadlog.cons.App.OUTPUT_PATH;
import static com.roadlog.cons.App.TEST_FILE_PATH;

public class Application {

    private final Connector connector;

    private final Decathlon decathlon;

    private final AthleteRepository athleteRepository;

    private final AthleteService athleteService;

    /**
     *  Application starting point(Constructor).
     *  Injection dependencies with initial data
     *  and calculate score depending on input and persist result
     *  on output.
     *
     *  @param input file path and output result path
     * */
    public Application(String input, String output){

        /**
         *  Injection of application params
         * */
        connector = new Connector();
        decathlon = new Roadlog();
        Connector.input = input;
        Connector.output = output;
        athleteRepository = new AthleteRepositoryImpl(connector, decathlon);
        athleteService = new AthleteService(athleteRepository);


        List<Athlete> athletes = athleteService.getAllAthletesOrderedByScore(OrderBy.DESC);

        if(!athletes.isEmpty()){
                Athletes xml = new Athletes();
                xml.setAthletes(athletes);
                athleteService.saveAthletesWithFinalResult(xml);
        }
        System.out.println("Game result calculated successfully, please check path => "+output);

    }

    /**
     * Please provide with initial data
     *
     * e.g: "src/test/resources/results.csv" "/Volumes/Macintosh HD/Training/athletes.xml"
     *
     * */
    public static final void main(String[] args){
        String input, output;
        try{
            input = args[0].length() > 0 ? args[0] : TEST_FILE_PATH;
            output = args[1].length() > 0 ? args[1] : OUTPUT_PATH;
            new Application(input, output);
        }catch (Exception e){
            System.err.println(MSG_1);
        }
    }
}
