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
    public static int proceed;
    public static int avgArrivalRate;
    public static int firstAttempt;
    public static int secondAttempt;
    public static int thirdAttempt;
    public static int totalInterarrival;
    public static int totalCallGenerate;

    public static int getProceed() {
        return proceed;
    }

    public static void setProceed(int proceed) {
        Statistic.proceed = proceed;
    }

    public static int getAvgArrivalRate() {
        return avgArrivalRate;
    }

    public static void setAvgArrivalRate(int avgArrivalRate) {
        Statistic.avgArrivalRate = avgArrivalRate;
    }

    public static int getFirstAttempt() {
        return firstAttempt;
    }

    public static void setFirstAttempt(int firstAttempt) {
        Statistic.firstAttempt = firstAttempt + 1;
    }

    public static int getSecondAttempt() {
        return secondAttempt;
    }

    public static void setSecondAttempt(int secondAttempt) {
        Statistic.secondAttempt = secondAttempt + 1;
    }

    public static int getThirdAttempt() {
        return thirdAttempt;
    }

    public static void setThirdAttempt(int thirdAttempt) {
        Statistic.thirdAttempt = thirdAttempt + 1;
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

    public static void setTotalCallGenerate(int totalCallGenerate) {
        Statistic.totalCallGenerate = totalCallGenerate;
    }
    
    
}
