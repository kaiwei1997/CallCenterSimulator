/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcentersimulator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author JoshTan
 */
public class CallGenerator implements Runnable {

    private final SimpleDateFormat formatter;

    private final Random random;

    private boolean running = true;
    

    public CallGenerator() {
        random = new Random();
        formatter = new SimpleDateFormat("HH:mm:ss");

    }

    @Override
    public void run() {
        long end = Time.getEnd();
        while (running) {
            if (System.currentTimeMillis() < end) {
                int duration = random.nextInt(16);
                if (duration > 2) {
                    log("Creating a call with a duration of " + duration + " seconds");
                    CallQueue.queueCall(duration);
                    sleep();
                }
            } else{
                stop();
                System.out.println("Simulation End");
                printStatistic();
            }
        }
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
        log("Stop creating call");
    }

    private void printStatistic() {
        long runningTime = Time.getEnd() - Time.getStart();
        System.out.println("Total Running Time: " + runningTime);
        System.out.println("The total number of calls processed: " + Statistic.getProceed());
        System.out.println("");
    }

    public void log(String s) {
        System.out.println("[" + formatter.format(new Date()) + "][CallGenerator]" + s);
    }

    private void sleep() {
        try {
            int sleep = random.nextInt(3) + 3;
            Thread.sleep(sleep * 1000);
            log("Pause generate call for " + sleep + " second(s)");
        } catch (InterruptedException e) {
        }
    }
}
