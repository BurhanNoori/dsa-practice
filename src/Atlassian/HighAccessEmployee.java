package Atlassian;
/*
You are given a 2D 0-indexed array of strings, access_times, with size n. For each i where 0 <= i <= n - 1,
access_times[i][0] represents the name of an employee, and access_times[i][1] represents the access time
of that employee. All entries in access_times are within the same day.
The access time is represented as four digits using a 24-hour time format, for example, "0800" or "2250".
An employee is said to be high-access if he has accessed the system three or more times within a one-hour period.
Times with exactly one hour of difference are not considered part of the same one-hour period.
For example, "0815" and "0915" are not part of the same one-hour period.
Access times at the start and end of the day are not counted within the same one-hour period.
For example, "0005" and "2350" are not part of the same one-hour period.
Return a list that contains the names of high-access employees with any order you want.
 */

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HighAccessEmployee {

    /*
    This is my approach it has several complexity which increases the runtime
    For eg. Sorting on String values, LocalTime.parse()
     */
    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        Map<String, List<String>> accessInfo = new HashMap<>();
        List<String> ans = new ArrayList<String>();

        for (List<String> data: access_times) {
            String name = data.get(0);
            String time = data.get(1);
            accessInfo
                    .computeIfAbsent(name, k -> new ArrayList<String>())
                    .add(time);
        }


        accessInfo.forEach((k, v) -> {
            Collections.sort(v, (a, b) -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
                LocalTime t1 = LocalTime.parse(a);
                LocalTime t2 = LocalTime.parse(b);
                return t1.compareTo(t2);
            });

            for (int i = 0; i < v.size() - 2; i++) {
                String time = v.get(i);
                String oneHourPeriod = v.get(i+2);
                String nextOneHour = nextOneHour(time);
                if (compareTime(oneHourPeriod, nextOneHour)) {
                    ans.add(k);
                    break;
                }
            }
        });

        return ans;
    }

    /*
    In the optimized solution we have converted the time "hhmm" into minutes (integer)
    By doing is we have avoided heavy localTime.parse() and String sorting
     */
    public List<String> findHighAccessEmployeesOpt(List<List<String>> access_times) {
        Map<String, List<Integer>> accessInfo = new HashMap<>();
        List<String> ans = new ArrayList<String>();

        for (List<String> data: access_times) {
            String name = data.get(0);
            String time = data.get(1);
            accessInfo
                    .computeIfAbsent(name, k -> new ArrayList<Integer>())
                    .add(toMinute(time));
        }

        accessInfo.forEach((k,v)->{
            Collections.sort(v);
            for (int i=2; i < v.size(); i++) {
                if (v.get(i) - v.get(i-2) < 60) {
                    ans.add(k);
                    break;
                }
            }
        });

        return ans;
    }

    private int toMinute(String hhmm) {
        return Integer.parseInt(hhmm.substring(0,2)) * 60 + Integer.parseInt(hhmm.substring(2));
    }

    private String nextOneHour(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime parsedTime = LocalTime.parse(time, formatter);
        LocalTime nextOneHourTime = parsedTime.plusHours(1);

        if (nextOneHourTime.isBefore(parsedTime)) {
            return "2359";
        }

        return nextOneHourTime.format(formatter);
    }

    private boolean compareTime(String time1, String time2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime t1 = LocalTime.parse(time1, formatter);
        LocalTime t2 = LocalTime.parse(time2, formatter);

        return t1.isBefore(t2);

    }
}

