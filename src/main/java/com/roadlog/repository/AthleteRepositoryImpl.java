package com.roadlog.repository;

import com.roadlog.cons.OrderBy;
import com.roadlog.model.Athlete;
import com.roadlog.model.Athletes;
import com.roadlog.service.api.Decathlon;
import com.roadlog.util.Connector;

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
        return decathlon.getAthletesPerformanceByEvent(connector.readStream());
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
