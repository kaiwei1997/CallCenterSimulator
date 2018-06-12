/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcentersimulator;

public class Call{
    private final int duration;
    
    private final int number;
    
    private final int attempt;

    public Call(int number, int duration,int attempt){
        this.number = number;
        this.duration = duration;
        this.attempt = attempt;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public int getNumber(){
        return number;
    }

    public int getAttempt() {
        return attempt;
    }
    
    
}
