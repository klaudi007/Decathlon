package com.swedbank.decathlon.repositories;

import com.swedbank.decathlon.model.Athlete;

import java.util.List;

/**
 * Created by Edvis on 2016-12-29.
 */
public interface AthleteRepository {

    /**
     * Returns list of athletes without scores and places.
     *
     * @return list of athletes.
     */
    List<Athlete> getAthletesFromFile();

    /**
     * Returns sorted list of athletes in ascending order with scores and places.
     *
     * @return list of athletes.
     */
    List<Athlete> getAthletesByScoreASC();

    /**
     * Returns sorted list of athletes in descending order with scores and places.
     *
     * @return list of athletes.
     */
    List<Athlete> getAthletesByScoreDESC();

    /**
     * Returns one athlete by name with score and place.
     *
     * @param name of athlete in String.
     * @return athlete.
     */
    Athlete getAthleteByName(String name);

    /**
     * Writes list of athletes to file in XML format.
     *
     * @param athletes list of athletes.
     */
    void saveListOfAthletesToXML(List<Athlete> athletes);

}
