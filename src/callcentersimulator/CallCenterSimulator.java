/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcentersimulator;

import java.util.Scanner;

/**
 *
 * @author JoshTan
 */
public class CallCenterSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String... args) {

        int serviceAgentQty;
        
        int minutes;
        
        ServiceAgent sa;
        
        try (Scanner reader = new Scanner(System.in)) {
            System.out.print("Please enter the number of service agent: ");
            serviceAgentQty = reader.nextInt();
            System.out.print("Please enter simulation time(In minutes): ");
            minutes = reader.nextInt();
        }

        for (int i = 1; i <= serviceAgentQty; i++) {
            sa = new ServiceAgent(i);
            sa.start();
        }

        new CallGenerator(minutes).start();
    }
}
