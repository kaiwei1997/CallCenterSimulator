/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcentersimulator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author JoshTan
 */
public class CallCenterSimulator {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) {

        int serviceAgentQty;
        int minutes;

        ServiceAgent sa = null;
        CallGenerator cg;
        SimpleDateFormat formatter;
        
        Scanner reader = new Scanner(System.in);

        do {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("                 >>> ATM QUEUE SIMULATION SYSTEM <<<                    ");
            System.out.println("------------------------------------------------------------------------"); 
            System.out.print("Please enter the positive number of service agent: ");
            while (!reader.hasNextInt()) {
                System.out.println("Please enter integer only");
                System.out.print("Please enter the number of service agent: ");
                reader.nextLine();
            }
            serviceAgentQty = reader.nextInt();

        } while (serviceAgentQty < 0);

        do {
            System.out.print("Please enter simulation time(In minutes(positive)): ");
            while (!reader.hasNextInt()) {
                System.out.println("Please enter integer only");
                System.out.print("Please enter simulation time(In minutes): ");
                reader.nextLine();
            }
            minutes = reader.nextInt();
        } while (minutes < 0);

        Time.setStart(System.currentTimeMillis());
        Time.setDuration(minutes * 60 * 1000);
        formatter = new SimpleDateFormat("HH:mm:ss");
        Timestamp ts = new Timestamp(Time.getStart());

        System.out.println("");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("                       >>> SIMULATION START <<<                         ");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Simulation Start Time: " + formatter.format(ts));

        for (int i = 1; i <= serviceAgentQty; i++) {
            sa = new ServiceAgent(i);
            sa.start();
        }

        cg = new CallGenerator();
        cg.start();
    }
}
