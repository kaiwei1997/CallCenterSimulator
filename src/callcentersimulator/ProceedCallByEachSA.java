package callcentersimulator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JoshTan
 */
public class ProceedCallByEachSA {
    private final int id;
    private final int proceedCallTotal;

    public ProceedCallByEachSA(int id, int proceedCallTotal) {
        this.id = id;
        this.proceedCallTotal = proceedCallTotal;
    }

    public int getId() {
        return id;
    }

    public int getProceedCallTotal() {
        return proceedCallTotal;
    }
    
    
    
}
