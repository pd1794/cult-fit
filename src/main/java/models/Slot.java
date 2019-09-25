package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.enums.DaySequence;
import models.enums.Location;
import models.enums.WeekDay;
import models.enums.Workout;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Slot {

    private int timing;

    private DaySequence sequence;

    private WeekDay day;

    private Location location;

    private Workout workout;

    private final Integer maxAttendees = 20;
}
