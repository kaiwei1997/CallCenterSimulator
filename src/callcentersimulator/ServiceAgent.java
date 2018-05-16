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

    private SimpleDateFormat formatter;

    private int id;

    private static boolean running;

    private ServiceAgentStatus status;

    private Call call;

    public ServiceAgent(int id) {
        this.id = id;
        this.status = ServiceAgentStatus.FREE;
        formatter = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void run() {
        while (running) {
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
                    log("Call "+call.getNumber() + " on hold");
                    int a = (call.getDuration()) - 7;
                    CallQueue.enQueueCall(call.getNumber(), a);
                    status = ServiceAgentStatus.FREE;
                } else if (System.currentTimeMillis() > callExpiration) {
                    log("Call End");
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

    public void stop() {
        running = false;
        log("All service agent stop");
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
