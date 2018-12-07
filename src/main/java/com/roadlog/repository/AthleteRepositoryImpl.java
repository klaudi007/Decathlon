package com.roadlog.repository;

import com.roadlog.cons.OrderBy;
import com.roadlog.model.Athlete;
import com.roadlog.model.Athletes;
import com.roadlog.service.api.Decathlon;
import com.roadlog.util.Connector;
import com.roadlog.util.Validator;
import com.roadlog.util.ValidatorImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AthleteRepositoryImpl implements AthleteRepository{

    private final Connector connector;

    private final Decathlon decathlon;

    public AthleteRepositoryImpl(Connector connector, Decathlon decathlon) {
        this.connector = connector;
        this.decathlon = decathlon;
    }


    @Override
    public List<Athlete> getAll() {
        List<String[]> results = connector.readStream();
        Validator validator = new ValidatorImpl();
        List<Athlete> athletes = new ArrayList<>();
        if(validator.validateInput(results)){
            athletes = decathlon.getAthletesPerformanceByEvent(results);
        }
        return athletes;
    }

    @Override
    public List<Athlete> getAllOrderByScore(OrderBy orderBy) {
        List<Athlete> athletes = decathlon.getGameFinalResult(getAll());
        if(orderBy == OrderBy.DESC){
            Collections.reverse(athletes); // mutates data.
            return athletes;
        }
        return athletes; // by default ascending order
    }

    @Override
    public void save(Athletes athletes) {
        connector.writeStrem(athletes);
    }
}
