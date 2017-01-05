package com.swedbank.decathlon.model;

import com.swedbank.decathlon.staticData.Event;
import com.swedbank.decathlon.Calculator;

import java.util.List;
import java.util.Map;

/**
 * Created by Edvis on 2016-12-29.
 */
public class Athlete {

    private String place;
    private Integer score;
    private String name;
    private Map<Event, Double> results;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Event, Double> getResults() {
        return results;
    }

    public void setResults(Map<Event, Double> results) {
        this.results = results;
    }


    /**
     * Overrides equality method. In this case the method is used by a List interface.
     *
     * @param arg0 Athlete object
     * @return true if scores is equals, false if not equals
     * @see Calculator#sortAndSetPlaces(List <Athlete>)
     */
    @Override
    public boolean equals(Object arg0) {
        Athlete obj = (Athlete) arg0;
        if (this.getScore() == (obj.getScore())) {
            return true;
        }
        return false;
    }

    /**
     * Overrides hashCode method. In this case the method is used by a HashSet interface.
     *
     * @return hash code of score.
     * @see Calculator#sortAndSetPlaces(List <Athlete>)
     */
    @Override
    public int hashCode() {
        return score.hashCode();
    }
}
