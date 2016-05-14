package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Created by irmis on 2016.05.12.
 */
public class CalculatePi implements Runnable {
    private String filename;
    public static StringProperty property = new SimpleStringProperty();
    public static volatile int counter = 0;
    public CalculatePi(String filename) {
        this.filename = filename;
    }

    @Override
    public void run(){
        double sum = 0;
        for(double i = 1; i < 100000000; i++) {
            if(i%2 == 0)
                sum += -1 / ( 2 * i - 1);
            else
                sum += 1 / (2 * i - 1);
        }
        String prefix = "Pi is approximately ";
        Main.print(filename, prefix + Double.toString(sum * 4));
        addToCounter();
        //con.updateLabel(counter);
    }

    private synchronized void addToCounter() {
        counter++;
    }
}
