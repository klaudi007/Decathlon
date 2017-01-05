package com.swedbank.decathlon;


import com.swedbank.decathlon.model.Athlete;
import com.swedbank.decathlon.staticData.Event;

import java.util.*;

/**
 * Created by Edvis on 2016-12-29.
 */
public class Calculator {

    /**
     * The method returns list of athletes with scores.
     *
     * @param athletes list of athletes without scores and places.
     * @return list of not sorted athletes with scores.
     */
    public List<Athlete> getWithScoresAndPlaces(List<Athlete> athletes) {

        List<Athlete> athletesWithScore = new LinkedList<Athlete>();

        for (Athlete athlete : athletes) {

            Athlete athleteWithScore = calculateScore(athlete);
            athletesWithScore.add(athleteWithScore);
        }

        sortAndSetPlaces(athletesWithScore);

        return athletesWithScore;

    }

    /**
     * The method calculates score according to an event type.
     *
     * @param athlete athlete object without score.
     * @return athlete object with score.
     */
    public Athlete calculateScore(Athlete athlete) {

        int points = 0;

        for (Map.Entry<Event, Double> entry : athlete.getResults().entrySet()) {
            switch (entry.getKey().getEventType()) {
                case TRACK:
                    points += (int) (entry.getKey().getA_param() * Math.pow(entry.getKey().getB_param() -
                            entry.getValue(), entry.getKey().getC_param()));
                    break;
                case FIELD:
                    points += (int) (entry.getKey().getA_param() * Math.pow(entry.getValue() -
                            entry.getKey().getB_param(), entry.getKey().getC_param()));
                    break;
            }
        }

        athlete.setScore(points);

        return athlete;
    }

    /**
     * The method sorts the list of athletes in ascending order as well as assigns places of them.
     *
     * @param athletes list of not sorted athletes with scores.
     */
    private void sortAndSetPlaces(List<Athlete> athletes) {

        athletes.sort((o1, o2) -> o1.getScore() - o2.getScore());

        Set<Athlete> uniques = new HashSet<>();
        Map<Integer, String> scoresCount = new HashMap<>();

        int elementIndex = 0;
        int listSize = athletes.size();
        int newPosition = 1;

        for (Athlete t : athletes) {
            if (uniques.add(t)) {
                newPosition = listSize - elementIndex;
                scoresCount.put(t.getScore(), new Integer(newPosition).toString());
                elementIndex++;


            } else {
                scoresCount.put(t.getScore(), new Integer(listSize - elementIndex).toString() +
                        "-" + new Integer(newPosition).toString());
                elementIndex++;
            }
        }

        for (Athlete c : athletes) {
            scoresCount.entrySet().stream().filter(entry -> c.getScore() == entry.getKey()).forEach(entry -> {
                c.setPlace(entry.getValue());
            });
        }

    }

}
