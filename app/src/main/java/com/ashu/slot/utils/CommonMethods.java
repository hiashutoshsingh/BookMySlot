package com.ashu.slot.utils;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.ashu.slot.model.Slot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class CommonMethods {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Map<String, List<Slot>> groupDates(List<Slot> slots) {
        Map<String, List<Slot>> groupDates =
                slots.stream().collect(
                        Collectors.groupingBy(slot -> slot.getStartTime().split(" ")[0])
                );
        return groupDates;
    }

    public static List<String> getDates(Map<String, List<Slot>> groupDates) {
        List<String> dates = new ArrayList<>();
        for (Map.Entry<String, List<Slot>> stringListMap : groupDates.entrySet())
            dates.add(stringListMap.getKey());
        return dates;
    }

    public static List<Slot> getSlotPerDate(Map<String, List<Slot>> groupDates, String date) {
        List<Slot> slots = new ArrayList<>();
        for (Map.Entry<String, List<Slot>> stringListMap : groupDates.entrySet())
            if (date.equals(stringListMap.getKey()))
                slots.addAll(stringListMap.getValue());
        return slots;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Map<String, List<Slot>> groupByShift(List<Slot> slots) {
        Map<String, List<Slot>> groupByShift =
                slots.stream().collect(
                        Collectors.groupingBy(slot -> {
                            String greeting = "";
                            try {
                                String time = getTime(slot.getStartTime()).split(" ")[1];
                                if (time.equals("PM"))
                                    greeting = "Evening";
                                else greeting = "Morning";
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return greeting;
                        }));
        return groupByShift;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<String> getShifts(List<Slot> slots) {
        List<String> shifts = new ArrayList<>();
        for (Map.Entry<String, List<Slot>> groupByShift : CommonMethods.groupByShift(slots).entrySet())
            shifts.add(groupByShift.getKey());
        return shifts;
    }

    public static List<Slot> getSlotsPerShift(Map<String, List<Slot>> groupByShift, String shift) {
        List<Slot> slots = new ArrayList<>();
        for (Map.Entry<String, List<Slot>> stringListEntry : groupByShift.entrySet())
            if (shift.equals(stringListEntry.getKey()))
                slots.addAll(stringListEntry.getValue());
        return slots;
    }

    public static String getTime(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ssz");
        format.setTimeZone(TimeZone.getTimeZone("ISO"));
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        Date d = format.parse(date);
        String formattedTime = output.format(d);
        return formattedTime.toUpperCase();
    }

}
