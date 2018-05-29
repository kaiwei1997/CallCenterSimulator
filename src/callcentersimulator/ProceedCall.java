/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcentersimulator;

/**
 *
 * @author JoshTan
 */
public class ProceedCall {

    private final int duration;

    private final int number;

    private final int agent;

    public ProceedCall(int number, int duration, int agent) {
        this.number = number;
        this.duration = duration;
        this.agent = agent;
    }

    public int getDuration() {
        return duration;
    }

    public int getNumber() {
        return number;
    }

    public int getAgent() {
        return agent;
    }
}
