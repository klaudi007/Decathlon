package com.roadlog.service.api;

import com.roadlog.model.Athlete;

import java.util.List;

public interface Decathlon {

    /**
     * Calculate Athletes performance by Decathlon events
     *
     * @input param raw result of Athletes
     * @return list of Athletes with performance by event
     * */
    List<Athlete> getAthletesPerformanceByEvent(List<String[]> results);

    /**
     * Calculate Athlete score by event types one by one
     *
     * @input param Athlete date with performance by event
     * @return Athlete with score
     * */
    Athlete getScore(Athlete athlete);

    /**
     * Calculates game final result with sorting and marking Athletes places
     *
     * @input param athletes with final scores
     * @return Athletes with score and marked places.
     * */
    List<Athlete> getGameFinalResult(List<Athlete> athletes);
}
