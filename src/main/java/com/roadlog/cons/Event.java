package com.roadlog.cons;

public enum Event {

    SPRINT_100M(EventType.TRACK, EventUnit.SECOND, 25.4347, 18.0, 1.81),
    LONG_JUMP(EventType.FIELD, EventUnit.METER, 0.14354, 220.0, 1.4),
    SHOT_PUT_THROW(EventType.FIELD, EventUnit.CENTIMETER, 51.39, 1.5, 1.05),
    HIGH_JUMP(EventType.FIELD, EventUnit.METER, 0.8465, 75.0, 1.42),
    SPRINT_400M(EventType.TRACK, EventUnit.SECOND, 1.53775, 82.0, 1.81),
    RUN_110M_HURDLES(EventType.TRACK, EventUnit.SECOND, 5.74352, 28.5, 1.92),
    DISCUS_THROW(EventType.FIELD, EventUnit.CENTIMETER, 12.91, 4.0, 1.1),
    POLE_VAULT_JUMP(EventType.FIELD, EventUnit.METER, 0.2797, 100.0, 1.35),
    JAVELIN_THROW(EventType.FIELD, EventUnit.CENTIMETER, 10.14, 7.0, 1.08),
    RUN_1500M(EventType.TRACK, EventUnit.MINUTE, 0.03768, 480.0, 1.85);

    private final EventType type;
    private final EventUnit unit;
    private final double a, b, c;


    Event(EventType type, EventUnit unit, double a, double b, double c) {
        this.type = type;
        this.unit = unit;
        this.a = a;
        this.b = b;
        this.c = c;
    }


    public EventType getType() {
        return type;
    }

    public EventUnit getUnit() {
        return unit;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
