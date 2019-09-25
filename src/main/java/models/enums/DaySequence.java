package models.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DaySequence {
    MORNING("morning"),
    EVENING("evening");

    private String daySequence;

    public String getDaySequence() {
        return this.daySequence;
    }
}
