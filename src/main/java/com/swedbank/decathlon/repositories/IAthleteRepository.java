package com.swedbank.decathlon.repositories;

import com.swedbank.decathlon.Calculator;
import com.swedbank.decathlon.Reader;
import com.swedbank.decathlon.Writer;
import com.swedbank.decathlon.exceptions.WriteFileException;
import com.swedbank.decathlon.model.Athlete;

import java.util.*;

/**
 * Created by Edvis on 2016-12-29.
 */
public class IAthleteRepository implements AthleteRepository {

    Reader reader = Reader.getReader();
    Writer writer = Writer.getWriter();

    private static volatile IAthleteRepository repository = null;
    private static final Object LOCK = new Object();

    private IAthleteRepository() {
    }

    /**
     * Initialization of a singleton.
     *
     * @return IAthleteRepository instance.
     */
    public static IAthleteRepository getRepository() {
        if (repository == null) {
            synchronized (LOCK) {
                if (repository == null) {
                    repository = new IAthleteRepository();
                }
            }
        }
        return repository;
    }

    /**
     * Returns list of athletes without scores and places.
     *
     * @return list of athletes.
     */
    public List<Athlete> getAthletesFromFile() {
        List<Athlete> athletes = reader.readFile();
        return athletes;
    }

    /**
     * Returns sorted list of athletes in ascending order with scores and places.
     *
     * @return list of athletes.
     */
    public List<Athlete> getAthletesByScoreASC() {
        List<Athlete> athletes = reader.readFile();
        Calculator calculator = new Calculator();
        return calculator.getWithScoresAndPlaces(athletes);
    }

    /**
     * Returns sorted list of athletes in descending order with scores and places.
     *
     * @return list of athletes.
     */
    public List<Athlete> getAthletesByScoreDESC() {
        List<Athlete> athletes = reader.readFile();
        Calculator calculator = new Calculator();
        athletes = calculator.getWithScoresAndPlaces(athletes);
        Collections.reverse(athletes);
        return athletes;
    }

    /**
     * Returns one athlete by name with score and place.
     *
     * @param name of athlete in String.
     * @return athlete.
     */
    public Athlete getAthleteByName(String name) {
        List<Athlete> athletes = reader.readFile();
        Calculator calculator = new Calculator();
        athletes = calculator.getWithScoresAndPlaces(athletes);
        Athlete athlete = null;
        for (Athlete a : athletes) {
            if (a.getName().equals(name)) {
                athlete = a;
            }
        }
        return athlete;
    }

    /**
     * Writes list of athletes to file in XML format.
     *
     * @param athletes list of athletes.
     */
    public void saveListOfAthletesToXML(List<Athlete> athletes) {
        try {
            writer.writeFileAsXML(athletes);
        } catch (WriteFileException e) {
            System.err.println("Write file exception: " + e.getMessage());
        }
    }
}
