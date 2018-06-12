
package callcentersimulator;

import static callcentersimulator.CallGenerator.printStatistic;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CallCenterSimulator {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) {

        int serviceAgentQty = -1;
        int minutes = -1;

        ServiceAgent sa = null;
        CallGenerator cg;
        SimpleDateFormat formatter;
        
        Scanner reader = new Scanner(System.in);

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("             >>> CALL CENTER QUEUE SIMULATION SYSTEM <<<                    ");
        System.out.println("----------------------------------------------------------------------------"); 
        
        System.out.print("Please enter number of service agents: ");      
        while (serviceAgentQty < 0) {
            try {
                serviceAgentQty = Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive integer!\n");
                System.out.print("Please enter number of service agents: ");
                continue;
            }        
            if (serviceAgentQty < 0){
                System.out.println("Please enter a positive integer!\n");
                System.out.print("Please enter number of service agents: ");
            }            
        }
        
        System.out.print("Please enter simulation time (in minutes): ");
        while (minutes < 0) {
            try {
                minutes = Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive integer!\n");
                System.out.print("Please enter simulation time (in minutes): ");
                continue;
            }        
            if (minutes < 0){
                System.out.println("Please enter a positive integer!\n");
                System.out.print("Please enter simulation time (in minutes): ");
            }            
        }

        Time.setStart(System.currentTimeMillis());
        Time.setDuration(minutes * 60 * 1000);
        formatter = new SimpleDateFormat("HH:mm:ss");
        Timestamp ts = new Timestamp(Time.getStart());

        System.out.println("");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("                       >>> SIMULATION START <<<                         ");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Simulation Start Time: " + formatter.format(ts));

        if (Time.getDuration()!=0){
            for (int i = 1; i <= serviceAgentQty; i++) {
                sa = new ServiceAgent(i);
                sa.start();
            }
            cg = new CallGenerator();
            cg.start();
        }
        else { // directly prints statistics if simulation time = 0
            printStatistic();
            for (int i = 1; i <= serviceAgentQty; i++) {
                sa = new ServiceAgent(i);
                sa.timer.cancel();
                sa.print();
            }
        }
        
    }
}
