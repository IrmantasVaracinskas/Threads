package sample;

/**
 * Created by irmis on 2016.05.12.
 */
public class CalculateFibonacci implements Runnable {
    private int fibonacciNumbertoFind;
    private String filename;
    public static volatile int counter = 0;
    public CalculateFibonacci(int number, String filename) {
        fibonacciNumbertoFind = number;
        this.filename = filename;
    }
    @Override
    public void run() {
        String prefix = "Fibonacci number nr. " + fibonacciNumbertoFind + " is ";
        Main.print(filename, prefix + Long.toString(fibonacciRecusion(fibonacciNumbertoFind)));
        addToCounter();
    }

    private long fibonacciRecusion(int number){
        if(number == 1 || number == 2){
            return 1;
        }
        return fibonacciRecusion(number-1) + fibonacciRecusion(number -2);
    }

    private static synchronized void addToCounter() {
        counter++;
    }
}
