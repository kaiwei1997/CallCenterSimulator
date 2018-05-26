/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcentersimulator;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public ServiceAgent(int id) {
        this.id = id;
        this.status = ServiceAgentStatus.FREE;
        formatter = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void run() {
        long end = Time.getEnd();
        while (running) {
            if (System.currentTimeMillis() < end) {
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
                            log("Call End");
                            status = ServiceAgentStatus.FREE;
                        } else if (call.getDuration() - 7 > 0) {
                            log("Call " + call.getNumber() + " on hold");
                            int a = (call.getDuration()) - 7;
                            CallQueue.enQueueCall(call.getNumber(), a);
                            status = ServiceAgentStatus.FREE;
                        }
                    } else if (System.currentTimeMillis() > callExpiration) {
                        int proceed = Statistic.getProceed();
                        Statistic.setProceed(proceed + 1);
                        log("Call End: Id " + call.getNumber());
                        status = ServiceAgentStatus.FREE;
                    }
                }
                sleep();
            } else{
                stop();
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
