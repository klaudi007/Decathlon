package com.swedbank.decathlon.services;

import com.swedbank.decathlon.Calculator;
import com.swedbank.decathlon.model.Athlete;
import com.swedbank.decathlon.repositories.AthleteRepository;
import com.swedbank.decathlon.repositories.IAthleteRepository;

import java.util.List;

/**
 * Created by Edvis on 2016-12-29.
 */
public class AthleteService {

    private AthleteRepository repository = IAthleteRepository.getRepository();

    private static volatile AthleteService service = null;
    private static final Object LOCK = new Object();

    private AthleteService() {
    }

    /**
     * Initialization of a singleton.
     *
     * @return AthleteService instance.
     */
    public static AthleteService getService() {
        if (service == null) {
            synchronized (LOCK) {
                if (service == null) {
                    service = new AthleteService();
                }
            }
        }
        return service;
    }

    /**
     * Returns list of athletes without scores and places.
     *
     * @return list of athletes.
     */
    public List<Athlete> getAthletes() {
        return repository.getAthletesFromFile();
    }

    /**
     * Returns sorted list of athletes in ascending order with scores and places.
     *
     * @return list of athletes.
     */
    public List<Athlete> getAthletesByScoreASC() {
        return repository.getAthletesByScoreASC();
    }

    /**
     * Returns sorted list of athletes in descending order with scores and places.
     *
     * @return list of athletes.
     */
    public List<Athlete> getAthletesByScoreDESC() {
        return repository.getAthletesByScoreDESC();
    }

    /**
     * Returns one athlete by name with score and place.
     *
     * @param name of athlete in String.
     * @return athlete.
     */
    public Athlete getAthleteByName(String name) {
        return repository.getAthleteByName(name);
    }

    /**
     * Writes list of athletes to file in XML format.
     *
     * @param athletes list of athletes.
     */
    public void saveAthletesToXML(List<Athlete> athletes) {
        repository.saveListOfAthletesToXML(athletes);
    }


}
