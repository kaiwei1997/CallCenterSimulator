/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcentersimulator;

/**
 *
 * @author JoshTan
 */
public class Time {
    public static long start;
    public static long duration;

    public static void setStart(long start) {
        Time.start = start;
    }

    public static void setDuration(long duration) {
        Time.duration = duration;
    }

    public static long getStart() {
        return start;
    }

    public static long getDuration() {
        return duration;
    }
}
