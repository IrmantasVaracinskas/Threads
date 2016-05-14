package sample;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    public TextField fibonacci;
    @FXML
    public Label piCounter;
    @FXML
    public Label fibonacciCounter;
    public void buttonOne() {
        //System.out.println(piCounter.getText());
        /*Thread t = new Thread(new CalculatePi("rez.txt"));
        t.start();
        updateLabel(CalculatePi.counter);*/

        Service<Void> backgroundThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        updateMessage("" + CalculatePi.counter);
                        new CalculatePi("rez.txt").run();
                        updateMessage("" + CalculatePi.counter);
                        return null;
                    }
                };
            }
        };

        piCounter.textProperty().bind(backgroundThread.messageProperty());

        backgroundThread.restart();

    }

    public void buttonTwo() {
        Service<Void> backgroundThread = null;
        try {
            final int I = Integer.parseInt(fibonacci.getText());
            //Thread t = new Thread(new CalculateFibonacci(i, "rez.txt"));
            //t.start();
            backgroundThread = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            updateMessage("" + CalculateFibonacci.counter);
                            new CalculateFibonacci(I ,"rez.txt").run();
                            updateMessage("" + CalculateFibonacci.counter);
                            return null;
                        }
                    };
                }
            };
        } catch(NumberFormatException e) {
            System.out.println("not a valid number");
        }

        fibonacciCounter.textProperty().bind(backgroundThread.messageProperty());

        backgroundThread.restart();


    }
}
