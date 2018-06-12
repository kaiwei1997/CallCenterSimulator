
package callcentersimulator;

import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Date;

public class CallQueue {

    private static CallQueue instance;
    private int counter;
    private final SimpleDateFormat formatter;
    private static LinkedBlockingQueue<Call> queue;

    public static void queueCall(int duration) 
    {
        try 
        {
             Call call = new Call(getInstance().counter++, duration,0);
            log("Queueing Call " + call.getNumber() + " with a duration of " + call.getDuration() + " seconds");
            getInstance().queue.put(call);
            PrintCallQ();
        } catch (InterruptedException e) {
            log("There was an error queueing the call");
        }
    }
    
    public static void enQueueCall(int id,int duration,int attempt) {
        try {
            Call call = new Call(id, duration,attempt);
            log("Enqueueing Call " + call.getNumber() + " with a duration of " + call.getDuration() + " seconds");
            getInstance().queue.put(call);
            PrintCallQ();
        } catch (InterruptedException e) {
            log("There was an error queueing the call");
        }
    }
    
    public static void PrintCallQ(){
        System.out.println("");
        System.out.println("Call In Queue:");
        System.out.println("**********************");
        System.out.println("Call ID   |   Duration");
        System.out.println("**********************");
        queue.forEach(f -> System.out.println("  " + f.getNumber() + "          "+ "\t" + f.getDuration()));
        System.out.println("");
    }
    

    public static Call retrieveCall() {
        Call call = getInstance().queue.poll();
        if (call != null) {
            log("Retrieving Call " + call.getNumber());
        }
        return call;
    }

    public static CallQueue getInstance() {
        if (instance == null) {
            instance = new CallQueue();
        }
        return instance;
    }

    private static void log(String s) {
        System.out.println("[" + getInstance().formatter.format(new Date()) + "][CallQueue] " + s);
    }

    private CallQueue() {
        this.queue = new LinkedBlockingQueue<Call>();
        this.counter = 1;
        this.formatter = new SimpleDateFormat("HH:mm:ss");
    }
}
