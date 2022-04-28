package uk.co.renbinden.libdnd.race.phb;

import uk.co.renbinden.libdnd.distance.Distance;
import uk.co.renbinden.libdnd.race.Race;

import static uk.co.renbinden.libdnd.distance.DistanceUnit.FEET;

public class Human extends Race {
    public Human() {
        super(
                "Human",
                18,
                100,
                new Distance(5, FEET),
                new Distance(7, FEET),
                new Distance(30, FEET),
                new Distance(0, FEET)
        );
    }
}
