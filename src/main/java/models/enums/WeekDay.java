package models.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WeekDay {
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday"),
    SUNDAY("sunday");

    private String weekDay;

    public String getWeekDay() {
        return this.weekDay;
    }
}
