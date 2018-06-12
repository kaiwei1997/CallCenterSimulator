
package callcentersimulator;

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
