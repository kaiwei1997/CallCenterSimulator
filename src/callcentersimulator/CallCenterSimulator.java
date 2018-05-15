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

        Scanner reader = new Scanner(System.in);

        int serviceAgentQty;
        int minutes;

        long runExpiration;

        System.out.print("Enter the number of service agent: ");
        serviceAgentQty = reader.nextInt();
        System.out.print("Please enter the time for simulator run (In minutes): ");
        minutes = reader.nextInt();
        runExpiration = System.currentTimeMillis() + (minutes * 60 * 1000);

        reader.close();

        for (int i = 1; i <= serviceAgentQty; i++) {
            new ServiceAgent(i).start();
        }

        System.out.println(runExpiration);
        new CallGenerator().start();

    }
}
