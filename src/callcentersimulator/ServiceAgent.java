/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcentersimulator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author JoshTan
 */
public class ServiceAgent
        implements Runnable {

    private long callExpiration;

    private long maxService;

    private final SimpleDateFormat formatter;

    private final int id;

    private static boolean running;

    private ServiceAgentStatus status;

    private Call call;

    private CallGenerator cg;

    private Time time;
    
    Timer timer;

    public ServiceAgent(int id) {
        this.id = id;
        this.status = ServiceAgentStatus.FREE;
        formatter = new SimpleDateFormat("HH:mm:ss");
        timer = new Timer();  //At this line a new Thread will be created
        timer.schedule(new stopService(), Time.getDuration()); //delay in milliseconds
    }
    @Override
    public void run() {
        while (running) {
            int proceed = Statistic.getProceed();
            if (status == ServiceAgentStatus.FREE) {
                call = CallQueue.retrieveCall();
                if (call != null) {
                    log("Answering call " + call.getNumber());
                    callExpiration = System.currentTimeMillis() + (call.getDuration() * 1000);
                    maxService = System.currentTimeMillis() + (7000);
                    status = ServiceAgentStatus.IN_A_CALL;
                }
            } else {
                if (System.currentTimeMillis() > maxService) {
                    if (call.getDuration() - 7 == 0) {
                        Statistic.setProceed(proceed + 1);
                        log("Call End: Id " + call.getNumber());
                        status = ServiceAgentStatus.FREE;
                    } else if (call.getDuration() - 7 > 0) {
                        log("Call " + call.getNumber() + " on hold");
                        int a = (call.getDuration()) - 7;
                        CallQueue.enQueueCall(call.getNumber(), a);
                        status = ServiceAgentStatus.FREE;
                    }
                } else if (System.currentTimeMillis() > callExpiration) {
                    Statistic.setProceed(proceed + 1);
                    log("Call End: Id " + call.getNumber());
                    status = ServiceAgentStatus.FREE;
                }
            }
            sleep();
        }
    }

    public void start() {
        running = true;
        new Thread(this).start();

    }

    class stopService extends TimerTask {

        @Override
        public void run() {
            running = false;
            timer.cancel();
            log("Service Agent Stop");
        }
    }

    private void log(String s) {
        System.out.println("[" + formatter.format(new Date()) + "][ServiceAgent][Agent" + id + "]" + s);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
