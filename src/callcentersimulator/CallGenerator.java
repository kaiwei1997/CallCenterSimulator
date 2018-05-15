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

    private boolean running;

    public CallGenerator() {
        random = new Random();
        formatter = new SimpleDateFormat("hh:mm:ss");
    }

    @Override
    public void run() {
        while (running) {
            int duration = random.nextInt(17);
            if (duration > 1) {
                log("creating a call with a duration of " + duration + " seconds");
                CallQueue.queueCall(duration);
                sleep();
            }
        }
    }

    public void start() {
            running = true;
            new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    public void log(String s) {
        System.out.println("[" + formatter.format(new Date()) + "][CallGenerator]" + s);
    }

    private void sleep() {
        try {
            int sleep = random.nextInt(2 * 60);
            Thread.sleep(sleep * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
