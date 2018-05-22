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
public class CallGenerator {

    private final SimpleDateFormat formatter;

    private final Random random;

    private final long execDuration;
    
    private final long ex;

    private ServiceAgent sa;

    public CallGenerator(long duration) {
        random = new Random();
        formatter = new SimpleDateFormat("HH:mm:ss");
        execDuration = duration;
        ex=System.currentTimeMillis()+(execDuration*30*1000);
    }

    public void timer() {
        Timer timer = new Timer("CallGenerator");
        while (System.currentTimeMillis() < ex) {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    int duration = random.nextInt(16);
                    if (duration > 2) {
                        log("Creating a call with a duration of " + duration + " seconds");
                        CallQueue.queueCall(duration);
                        sleep();
                    }
                }
            },0,1);
        }
        timer.cancel();
        stop();
    }

    public void stop() {
        log("Stop creating call");
    }

    public void log(String s) {
        System.out.println("[" + formatter.format(new Date()) + "][CallGenerator]" + s);
    }

    private void sleep() {
        try {
            int sleep = random.nextInt(3) + 3;
            Thread.sleep(sleep * 1000);
            log("Pause generate call " + sleep + " second(s)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
