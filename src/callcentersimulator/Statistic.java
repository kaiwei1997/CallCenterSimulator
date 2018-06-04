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
public class Statistic {
    public static int processed;
    public static int firstAttempt;
    public static int secondAttempt;
    public static int thirdAttempt;
    public static int totalInterarrival;
    public static int totalCallGenerate;

    public static int getprocessed() {
        return processed;
    }

    public static void setprocessed() {
        Statistic.processed += 1;
    }

    public static int getFirstAttempt() {
        return firstAttempt;
    }

    public static void setFirstAttempt() {
        Statistic.firstAttempt += 1;
    }

    public static int getSecondAttempt() {
        return secondAttempt;
    }

    public static void setSecondAttempt() {
        Statistic.secondAttempt += 1;
    }

    public static int getThirdAttempt() {
        return thirdAttempt;
    }

    public static void setThirdAttempt() {
        Statistic.thirdAttempt += 1;
    }

    public static int getTotalInterarrival() {
        return totalInterarrival;
    }

    public static void setTotalInterarrival(int totalInterarrival) {
        Statistic.totalInterarrival = totalInterarrival;
    }

    public static int getTotalCallGenerate() {
        return totalCallGenerate;
    }

    public static void setTotalCallGenerate() {
        Statistic.totalCallGenerate += 1;
    }
}
