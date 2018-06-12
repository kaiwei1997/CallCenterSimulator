
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
