package com.roadlog.service.impl;

import com.roadlog.cons.Event;
import com.roadlog.model.Athlete;
import com.roadlog.service.api.Decathlon;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.roadlog.cons.App.CUSTOM_TIME;
import static com.roadlog.cons.App.TIME_FORMAT;

public class Roadlog implements Decathlon{

    @Override
    public List<Athlete> getGameFinalResult(List<Athlete> athletes) {

        List<Athlete> athletesWithScore = new LinkedList<>();

        athletes.forEach(athlete -> athletesWithScore.add(getScore(athlete)));

        return sortAndMarkPlaces(athletesWithScore);
    }

    @Override
    public Athlete getScore(Athlete athlete) {

        int points = 0;

        for (Map.Entry<Event, Double> entry : athlete.getResults().entrySet()) {
            switch (entry.getKey().getType()) {
                case TRACK:
                    points += (int) (entry.getKey().getA() * Math.pow(entry.getKey().getB() -
                            entry.getValue(), entry.getKey().getC()));
                    break;
                case FIELD:
                    points += (int) (entry.getKey().getA() * Math.pow(entry.getValue() -
                            entry.getKey().getB(), entry.getKey().getC()));
                    break;
            }
        }

        athlete.setScore(points);

        return athlete;
    }

    @Override
    public List<Athlete> getAthletesPerformanceByEvent(List<String[]> results) {

        DateFormat formatter = new SimpleDateFormat(TIME_FORMAT);
        Date reference;
        Date date;
        List<Athlete> athletes = new ArrayList<>();

        Athlete athlete ;

        try {
            reference = formatter.parse(CUSTOM_TIME);

            for(String[] data: results) {
                Map<Event, Double> events = new HashMap<>();
                double parsedPerformance;
                String performance;
                athlete = new Athlete();
                for (Event event : Event.values()) {
                    performance = data[Event.valueOf(event.name()).ordinal() + 1];
                    switch (event.getUnit()) {
                        case METER:
                            parsedPerformance = Double.parseDouble(performance) * 100;
                            events.put(event, parsedPerformance);
                            break;
                        case CENTIMETER:
                        case SECOND:
                            parsedPerformance = Double.parseDouble(performance);
                            events.put(event, parsedPerformance);
                            break;
                        case MINUTE:
                            if (performance.split("\\.")[2].length() == 2) {
                                date = formatter.parse(performance.concat("0"));
                            } else {
                                date = formatter.parse(performance);
                            }
                            parsedPerformance = ((date.getTime() - reference.getTime()) / 1000.0);
                            events.put(event, parsedPerformance);
                            break;
                    }
                }
                athlete.setName(data[0]);
                athlete.setResults(events);
                athletes.add(athlete);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return athletes;
    }


    private List<Athlete> sortAndMarkPlaces(List<Athlete> athletes) {

        athletes.sort(Comparator.comparingInt(Athlete::getScore));

        Set<Athlete> uniques = new HashSet<>();
        List<Athlete> response = new ArrayList<>();

        int index = 0;
        int listSize = athletes.size();
        int newPlace = 1;

        for (Athlete athlete : athletes) {
            String place;
            if (uniques.add(athlete)) {
                newPlace = listSize - index;
                place = String.valueOf(newPlace);
            } else {
                place = String.valueOf(listSize - index) + "-" + String.valueOf(newPlace);
            }
            index++;
            athlete.setPlace(place);
            response.add(athlete);
        }
        return response;

    }

}
