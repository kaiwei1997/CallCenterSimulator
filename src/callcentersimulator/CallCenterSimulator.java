/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcentersimulator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
    public static void main(String... args) throws InterruptedException {

        int serviceAgentQty;

        int minutes;

        ServiceAgent sa = null;

        CallGenerator cg;
        
        SimpleDateFormat formatter;
        
        try (Scanner reader = new Scanner(System.in)) {
            System.out.print("Please enter the number of service agent: ");
            serviceAgentQty = reader.nextInt();
            System.out.print("Please enter simulation time(In minutes): ");
            minutes = reader.nextInt();
        }
        
        Time.setStart(System.currentTimeMillis());
        Time.setEnd(Time.getStart() + (minutes*60*1000));
        
        formatter = new SimpleDateFormat("HH:mm:ss");
        
        Timestamp ts = new Timestamp(Time.getStart());
        
        System.out.println("Simulation Start Time: " + formatter.format(ts));
        
        for (int i = 1; i <= serviceAgentQty; i++) {
            sa = new ServiceAgent(i);
            sa.start();
        }

        cg = new CallGenerator();
        cg.start();
    }
}
