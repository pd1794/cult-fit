package controller;

import lombok.AllArgsConstructor;
import models.Slot;
import models.enums.DaySequence;
import models.enums.Location;
import models.enums.WeekDay;
import models.enums.Workout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class WorkoutController {
    Map<String, List<Slot>> workoutDetails;
    Map<Slot, Integer> attendees;
    Map<Slot, Boolean> isSlotAvailaible;

    public List<Slot> viewSlots(String userId, String day) {
        List<Slot> slots = workoutDetails.get(userId);
        if(slots == null) {
            return Collections.emptyList();
        }
        return slots.stream()
                .filter(slot -> slot.getDay().getWeekDay().equals(day))
                .collect(Collectors.toList());
    }

    public String bookSlot(String userId, String location, Integer timings, String daySequence, String day, String workout) {

        Slot slot = Slot.builder()
                .day(WeekDay.valueOf(day.toUpperCase()))
                .location(Location.valueOf(location.toUpperCase()))
                .sequence(DaySequence.valueOf(daySequence.toUpperCase()))
                .timing(timings)
                .workout(Workout.valueOf(workout.toUpperCase()))
                .build();

        if(!checkAvailaibility(slot)) {
            return "Booking closed for given slot";
        }

        if (workoutDetails.containsKey(userId)) {
            List<Slot> slots = workoutDetails.get(userId);
            if(slots.contains(slot)) {
                return String.format("Slot already booked by user {}", userId);
            }
            slots.add(slot);
        } else {
            List<Slot> slots = new ArrayList<>();
            slots.add(slot);
            workoutDetails.put(userId, slots);
        }
        return String.format("Slot booked for user {}", userId);
    }

    public void cancelSlot(String location, Integer timings, String daySequence, String day, String workout) {
        Slot slot = Slot.builder()
                .timing(timings)
                .sequence(DaySequence.valueOf(daySequence.toUpperCase()))
                .location(Location.valueOf(location.toUpperCase()))
                .day(WeekDay.valueOf(day.toUpperCase()))
                .workout(Workout.valueOf(workout.toUpperCase()))
                .build();

        isSlotAvailaible.put(slot, false);
        System.out.println("Slot closed");
    }

    private boolean checkAvailaibility(Slot slot) {
        if(attendees.containsKey(slot)) {
            int bookings = attendees.get(slot);
            if (bookings == slot.getMaxAttendees()) {
                return false;
            }
        } else if(isSlotAvailaible.containsKey(slot)) {
            return false;
        }
        attendees.put(slot, 1);
        return true;
    }
}
