
package callcentersimulator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CallGenerator implements Runnable {

    private final SimpleDateFormat formatter;
    private final Random random;
    private boolean running = true;
    private final Timer timer;

    public CallGenerator() {

        random = new Random();
        formatter = new SimpleDateFormat("HH:mm:ss");
        timer = new Timer();  //At this line a new Thread will be created
        timer.schedule(new stopGenerate(), Time.getDuration()); //delay in milliseconds
    }

    @Override
    public void run() {
        sleep(random.nextInt(4) + 2);
        while (running) {
            int duration = random.nextInt(14) + 3;
            int interarrival = Statistic.getTotalInterarrival();
            int sleepTime = random.nextInt(4) + 2;
            log("Creating a call with a duration of " + duration + " seconds");
            Statistic.setTotalCallGenerate();
            CallQueue.queueCall(duration);
            sleep(sleepTime);
            Statistic.setTotalInterarrival(interarrival + sleepTime);

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
            log("Stop creating calls\n");
            timer.cancel();
            printStatistic();
        }
    }

    public void log(String s) {
        System.out.println("[" + formatter.format(new Date()) + "][CallGenerator] " + s);
    }

    private void sleep(int interarrival) {
        try {
            log("Pause call generating for " + interarrival + " second(s)");
            Thread.sleep(interarrival * 1000);
        } catch (InterruptedException e) {
        }
    }

    static void printStatistic() {
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");;
        long runningTime = (System.currentTimeMillis() - Time.getStart()) / 60 / 1000;
        long duration = Time.getDuration() / 60 / 1000;
        int processed = Statistic.getprocessed();
        double avgNumCalls;
        // Catch ArithmeticException if simulation time = 0
        try {
            avgNumCalls = processed / duration;
        } catch (ArithmeticException e) {
            avgNumCalls = 0;
        }
        int totalCallGenerate = Statistic.getTotalCallGenerate();
        int totalInterarrival = Statistic.getTotalInterarrival();
        double meanInterarrival = ((double) totalInterarrival) / (totalCallGenerate);
        double avgArrivalRate = ((int) Math.ceil(60 / meanInterarrival));
        /**
         * To calculate average arrival rate: 
         *      1. Calculate mean of inter arrival:
         *              inter arrival = the duration between two calls
         *              mean inter arrival = total inter arrival / total calls generated
         *      2. The average arrival rate is the total calls generated per unit of time 
         *              let 60 second = one unit of time
         *              so, average arrival rate = 60 / mean inter arrival
         */
        System.out.println("------------------------------------------------------------------------");
        System.out.println("                        >>> SIMULATION END <<<                          ");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Simulation End Time: " + formatter.format(System.currentTimeMillis()));
        System.out.println("");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("                      >>> SIMULATION SUMMARY <<<                        ");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Running time: " + runningTime + " minute(s)");
        System.out.println("Total calls generated: " + totalCallGenerate);
        System.out.println("Total interarrival time : " + totalInterarrival + " second(s)");
        System.out.println("Total calls processed: " + processed);
        System.out.println("Average number of calls processed per minute: " + avgNumCalls);
        System.out.println("Average arrival rate per minute: " + avgArrivalRate);
        System.out.println("Number of calls processed on first attempt: " + Statistic.getFirstAttempt());
        System.out.println("Number of calls requeued once: " + Statistic.getSecondAttempt());
        System.out.println("Number of calls requeued twice: " + Statistic.getThirdAttempt());
        System.out.println("------------------------------------------------------------------------");
    }
}
