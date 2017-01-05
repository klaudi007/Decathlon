package com.swedbank.decathlon.staticData;

/**
 * The enumeration holds static information about events.
 *
 * Created by Edvis on 2016-12-28.
 */
public enum Event {
    SPRINT_100M(EventType.TRACK, Unit.SECONDS, 25.4347, 18.0, 1.81),
    LONG_JUMP(EventType.FIELD, Unit.METERS, 0.14354, 220.0, 1.4),
    SHOT_PUT(EventType.FIELD, Unit.CENTIMETERS, 51.39, 1.5, 1.05),
    HIGH_JUMP(EventType.FIELD, Unit.METERS, 0.8465, 75.0, 1.42),
    SPRINT_400M(EventType.TRACK, Unit.SECONDS, 1.53775, 82.0, 1.81),
    HURDLES_110M(EventType.TRACK, Unit.SECONDS, 5.74352, 28.5, 1.92),
    DISCUS_THROW(EventType.FIELD, Unit.CENTIMETERS, 12.91, 4.0, 1.1),
    POLE_VAULT(EventType.FIELD, Unit.METERS, 0.2797, 100.0, 1.35),
    JAVELIN_THROW(EventType.FIELD, Unit.CENTIMETERS, 10.14, 7.0, 1.08),
    RACE_1500M(EventType.TRACK, Unit.MINUTES_SECONDS, 0.03768, 480.0, 1.85);

    private EventType eventType;
    private Unit unit;
    private final double A_param, B_param, C_param;

    /**
     * Constructor.
     *
     * @param eventType type of an event TRACK or FIELD. It is used by calculating scores.
     * @param unit it's used by validating and parsing String results.
     * @param A_param parameter A of a scoring formula.
     * @param B_param parameter B of a scoring formula.
     * @param C_param parameter C of a scoring formula.
     * @see com.swedbank.decathlon.Reader
     * @see com.swedbank.decathlon.InputValidator
     */
    Event(EventType eventType, Unit unit, double A_param, double B_param, double C_param) {
        this.eventType = eventType;
        this.unit = unit;
        this.A_param = A_param;
        this.B_param = B_param;
        this.C_param = C_param;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Unit getUnit() {
        return unit;
    }

    public double getA_param() {
        return A_param;
    }

    public double getB_param() {
        return B_param;
    }

    public double getC_param() {
        return C_param;
    }
}
