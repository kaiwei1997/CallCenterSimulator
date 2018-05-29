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
        sleep();
        while (running) {
            int duration = random.nextInt(16);
            int attempt = duration / 7;
            if (duration > 2) {
                log("Creating a call with a duration of " + duration + " seconds");
                CallQueue.queueCall(duration);
                sleep();
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
            System.out.println("Simulation End");
            printStatistic();
        }
    }

    private void printStatistic() {
        long runningTime  = (System.currentTimeMillis()-Time.getStart())/60/1000;
        long duration = Time.getDuration()/60/1000;
        int proceed = Statistic.getProceed();
        double avgNumCalls = proceed / duration;
        System.out.println("Running Time:" + runningTime + " minute(s)");
        System.out.println("The total number of calls processed: " + proceed);
        System.out.println("Average number of calls processed per minute: " + avgNumCalls);
    }

    public void log(String s) {
        System.out.println("[" + formatter.format(new Date()) + "][CallGenerator]" + s);
    }

    private void sleep() {
        try {
            int sleep = random.nextInt(4) + 2;
            log("Pause generate call for " + sleep + " second(s)");
            Thread.sleep(sleep * 1000);
        } catch (InterruptedException e) {
        }
    }
}
