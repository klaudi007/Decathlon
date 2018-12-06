package com.roadlog.repository;

import com.roadlog.cons.OrderBy;
import com.roadlog.model.Athlete;
import com.roadlog.model.Athletes;

import java.util.List;

public interface AthleteRepository {

    /**
     * Returns athlete's raw results
     *
     * @return list of athletes.
     * */
     List<Athlete> getAll();

    /**
     * Returns raw athlete's results ordered by score
     *
     * @return list of athletes.
     * */
    List<Athlete> getAllOrderByScore(OrderBy orderBy);

    /**
     * Persist result on XML file using JAXB
     *
     * @param athletes parent node for list of athletes
     * */
    void save(Athletes athletes);

}
