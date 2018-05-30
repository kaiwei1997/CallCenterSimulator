/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcentersimulator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author JoshTan
 */
public class CallGenerator implements Runnable {

    private final SimpleDateFormat formatter;

    private final Random random;

    private boolean running = true;

    Timer timer;

    public CallGenerator() {

        random = new Random();
        formatter = new SimpleDateFormat("HH:mm:ss");
        timer = new Timer();  //At this line a new Thread will be created
        timer.schedule(new stopGenerate(), Time.getDuration()); //delay in milliseconds
    }

    @Override
    public void run() {
        sleep(random.nextInt(4)+2);
        while (running) {
            int duration = random.nextInt(16);
            int totalGenerate = Statistic.getTotalCallGenerate();
            int interarrival = Statistic.getTotalInterarrival();
            int sleepTime = random.nextInt(4) + 2;
            if (duration > 2) {
                log("Creating a call with a duration of " + duration + " seconds");
                Statistic.setTotalCallGenerate(totalGenerate + 1);
                CallQueue.queueCall(duration);
                sleep(sleepTime);
                Statistic.setTotalInterarrival(interarrival+sleepTime);
            }
        }
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    class stopGenerate extends TimerTask {

        @Override
        public void run() {
            running = false;
            timer.cancel();
            log("Stop creating call");
            printStatistic();
        }
    }

    private void printStatistic() {
        long runningTime  = (System.currentTimeMillis()-Time.getStart())/60/1000;
        long duration = Time.getDuration()/60/1000;
        int proceed = Statistic.getProceed();
        double avgNumCalls = proceed / duration;
        int totalCallGenerate = Statistic.getTotalCallGenerate();
        int totalInterarrival  = Statistic.getTotalInterarrival();
        double meanInterarrival = ((double)totalInterarrival)/(totalCallGenerate);
        double avgArrivalRate = ((int) Math.ceil(60/meanInterarrival));
        System.out.println("Simulation End: " + formatter.format(System.currentTimeMillis()));
        System.out.println("Running time: " + runningTime + " minute(s)");
        System.out.println("Total call generate: " + totalCallGenerate);
        System.out.println("Total interarrival: " + totalInterarrival);
        System.out.println("The total number of calls processed: " + proceed);
        System.out.println("Average number of calls processed per minute: " + avgNumCalls);
        System.out.println("Average arrival rate per minute: " + avgArrivalRate);
    }

    public void log(String s) {
        System.out.println("[" + formatter.format(new Date()) + "][CallGenerator]" + s);
    }

    private void sleep(int interarrival) {
        try {
            log("Pause generate call for " + interarrival + " second(s)");
            Thread.sleep(interarrival * 1000);
        } catch (InterruptedException e) {
        }
    }
}
