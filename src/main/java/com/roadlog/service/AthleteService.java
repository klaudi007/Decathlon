package com.roadlog.service;

import com.roadlog.cons.OrderBy;
import com.roadlog.model.Athlete;
import com.roadlog.model.Athletes;
import com.roadlog.repository.AthleteRepository;

import java.util.List;

public class AthleteService {

    private final AthleteRepository athleteRepository;

    public AthleteService(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    /**
     * Retrieve athlete's raw results from storage
     *
     * @return list of athletes.
     * */
    public List<Athlete> getAllAthletes(){
        return athleteRepository.getAll();
    }


    /**
     * Retrieve athlete's results by score ascending or descending order
     *
     * @return list of athletes.
     * */
    public List<Athlete> getAllAthletesOrderedByScore(OrderBy orderBy){
        return athleteRepository.getAllOrderByScore(orderBy);
    }


    /**
     * Persist final Game result on storage (xml file in our example)
     * */
    public void saveAthletesWithFinalResult(Athletes athletes){
        athleteRepository.save(athletes);
    }
}
