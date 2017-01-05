package com.swedbank.decathlon;

import com.swedbank.decathlon.model.Athlete;
import com.swedbank.decathlon.services.AthleteService;


import java.util.List;

/**
 * Created by Edvis on 2016-12-28.
 */
public class Application {

    private AthleteService service = AthleteService.getService();
    private Reader reader = Reader.getReader();
    private Writer writer = Writer.getWriter();

    /**
     * Main method.
     *
     * @param args parameters to the Java application at the startup.
     */
    public static void main(String[] args) {

        new Application(args);

    }

    /**
     * Constructor of the Application class.
     *
     * @param args parameters to the Java application at the startup.
     */
    public Application(String[] args) {

        try {
            reader.setInputFileLocation(args[0]);
            writer.setOutputFileLocation(args[1]);

            List<Athlete> sortedAthletes = service.getAthletesByScoreASC();

            if (!sortedAthletes.isEmpty()) {
                service.saveAthletesToXML(sortedAthletes);
            }

            System.out.println("The calculations of athletes was completed successfully. Pleas check the file: " + args[1]);

        } catch (IndexOutOfBoundsException e) {
            System.err.println("No arguments was found. At least two arguments should be used");
        }
    }
}
