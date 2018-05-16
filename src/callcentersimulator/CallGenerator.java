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
public class CallGenerator
        implements Runnable {

    private final SimpleDateFormat formatter;

    private final Random random;
    
    private final long execDuration;
    
    private ServiceAgent s;


    public CallGenerator(long duration) {
        random = new Random();
        formatter = new SimpleDateFormat("HH:mm:ss");
        execDuration = duration;
    }

    @Override
    public void run() {
        long ex = System.currentTimeMillis() + (execDuration * 30 * 1000);
        while (System.currentTimeMillis() < ex) {
            int duration = random.nextInt(16);
            if (duration > 2) {
                log("Creating a call with a duration of " + duration + " seconds");
                CallQueue.queueCall(duration);
                sleep();
            }
        }
        stop();
    }

    public void start() {
        new Thread(this).start();
    }

    public void stop() {
        log("Stop creating call");
    }

    public void log(String s) {
        System.out.println("[" + formatter.format(new Date()) + "][CallGenerator]" + s);
    }

    private void sleep() {
        try {
            int sleep = random.nextInt(2 * 60);
            Thread.sleep(sleep * 50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
