package callcentersimulator;

public class ProcessedCallByEachSA {
    private final int id;
    private final int processedCallTotal;

    public ProcessedCallByEachSA(int id, int processedCallTotal) {
        this.id = id;
        this.processedCallTotal = processedCallTotal;
    }

    public int getId() {
        return id;
    }

    public int getProcessedCallTotal() {
        return processedCallTotal;
    }
    
    
    
}
