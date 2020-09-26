package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import sun.awt.windows.ThemeReader;

import java.io.IOException;

import static java.net.InetAddress.getLocalHost;

public class ApplicationController {
    private Main main;

    @FXML
    private Label mailsentOn;

    @FXML
    private Label mailsentAt;

    @FXML
    private Label state;

    @FXML
    private Label currentIP;

    @FXML
    private Label timeInterval;

    @FXML
    private Hyperlink developer;

    @FXML
    private Hyperlink help;

    private String from,to,password,interval;
    private Thread t;

    private boolean loopCondition = true;
    CheckerThread checkerThread;
    void setMain(Main main) {
        this.main = main;
    }

    public void init(String from, String to, String password, String interval){
        this.from = from;
        this.to = to;
        this.password = password;
        this.interval = interval;
        timeInterval.setText(interval+" seconds");
    }

    public void shutdown() {
        // cleanup code here...
//        System.out.println("Stop");
        loopCondition = false;
        checkerThread.interrupt();
        // note that typically (i.e. if Platform.isImplicitExit() is true, which is the default)
        // closing the last open window will invoke Platform.exit() anyway
//        Platform.exit();
    }

    public void startApplication(){
        String currentIpAddress = IPAddressChecker.getCurrentIPAddress();
        currentIP.setText(currentIpAddress);
        checkerThread = new CheckerThread(currentIpAddress, interval);
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            loopCondition = false;
            checkerThread.interrupt();
            main.showHomePage();
        } catch (IOException e) {
            main.showExceptionPage(e);
        }
    }

    @FXML
    void helpClicked(ActionEvent event) throws IOException {
//        loopCondition = false;
        main.showHelp();
    }

    @FXML
    void developerClicked(ActionEvent event) throws IOException {
//        loopCondition = false;
        main.showDeveloper();
    }

    class CheckerThread implements Runnable {
        Thread t;
        String previousIpAddress;
        String sleepInterval;
        CheckerThread(String previousIP, String sleepInterval){
            this.t = new Thread(this);
            this.previousIpAddress = previousIP;
            this.sleepInterval = sleepInterval;
            this.t.start();
        }
        public void interrupt(){
            t.interrupt();
        }
        @Override
        public void run() {
            while (loopCondition){
                try {
                    String newIpAddress = IPAddressChecker.getCurrentIPAddress();
                    if(!newIpAddress.equals(previousIpAddress)){
                        currentIP.setText(newIpAddress);
                        System.out.println("System IP Address: "+newIpAddress + " Previous: "+previousIpAddress);
                        Platform.runLater(()->{
                            state.setText("Sending Mail");
                            if(SendMailSSL.send(from,password,to,"IP Checking App","Current IP: " + newIpAddress)){
                                mailsentOn.setText(java.time.LocalDate.now().toString());
                                mailsentAt.setText(java.time.LocalTime.now().toString().substring(0,5));
                            }
                            state.setText("Running");
                        });
                        previousIpAddress = newIpAddress;
                    }
                    Thread.sleep(1000*Integer.parseInt(sleepInterval));
//                    System.out.println("Woke up");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
