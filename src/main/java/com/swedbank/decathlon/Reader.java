package com.swedbank.decathlon;

import com.swedbank.decathlon.exceptions.ReadFileException;
import com.swedbank.decathlon.model.Athlete;
import com.swedbank.decathlon.staticData.Event;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Edvis on 2016-12-30.
 */
public class Reader {

    private static volatile Reader reader = null;
    private static final Object LOCK = new Object();
    private static String fileLocation;

    private final static String TIME_FORMAT = "mm.ss.SSS";

    private Reader() {
    }

    /**
     * Initialization of a singleton.
     *
     * @return Reader instance.
     */
    public static Reader getReader() {
        if (reader == null) {
            synchronized (LOCK) {
                if (reader == null) {
                    reader = new Reader();
                }
            }
        }
        return reader;
    }

    /**
     * The method reads input file and creates Athlete object.
     *
     * @return List of athletes without scores and places.
     */
    public List<Athlete> readFile() {

        ArrayList<Athlete> athleteList = new ArrayList<Athlete>();
        InputValidator validator = new InputValidator();

        BufferedReader br = null;
        String line;
        String separator = ";";

        DateFormat formatter = new SimpleDateFormat(TIME_FORMAT);
        Date reference;
        Date date;

        Athlete athlete;

        try {

            br = new BufferedReader(new FileReader(this.fileLocation));
            List<String> fileCopy = new LinkedList<>();

            while ((line = br.readLine()) != null) {
                fileCopy.add(line);
            }

            if (validator.validateFile(fileCopy)) {

                for (String l : fileCopy) {
                    Map<Event, Double> events = new HashMap<Event, Double>();
                    String[] data = l.split(separator);
                    String performance;
                    double parsedPerformance;

                    reference = formatter.parse("00.00.000");
                    athlete = new Athlete();

                    for (Event event : Event.values()) {
                        performance = data[Event.valueOf(event.name()).ordinal() + 1];
                        switch (event.getUnit()) {
                            case METERS:
                                parsedPerformance = Double.parseDouble(performance) * 100;
                                events.put(event, parsedPerformance);
                                break;
                            case CENTIMETERS:
                            case SECONDS:
                                parsedPerformance = Double.parseDouble(performance);
                                events.put(event, parsedPerformance);
                                break;
                            case MINUTES_SECONDS:
                                if (performance.split("\\.")[2].length() == 2) {
                                    date = formatter.parse(performance.concat("0"));
                                } else {
                                    date = formatter.parse(performance);
                                }
                                parsedPerformance = ((date.getTime() - reference.getTime()) / (double) 1000.0);
                                events.put(event, parsedPerformance);
                                break;
                        }
                    }

                    athlete.setName(data[0]);
                    athlete.setResults(events);
                    athleteList.add(athlete);

                }

            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        } catch (ReadFileException e) {
            System.err.println(e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return athleteList;
    }

    /**
     * Setter method encapsulates fileLocation field.
     *
     * @param fileLocation string path of output file.
     */
    public void setInputFileLocation(String fileLocation) {
            this.fileLocation = fileLocation;
    }
}
