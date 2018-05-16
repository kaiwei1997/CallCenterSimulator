/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcentersimulator;

import java.io.Serializable;

/**
 *
 * @author JoshTan
 */
public class Call
        implements Serializable 
{
    private final int duration;
    
    private final int number;
    
    public Call(int number, int duration){
        this.number = number;
        this.duration = duration;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public int getNumber(){
        return number;
    }
}
